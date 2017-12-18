package com.demo.service.wx.impl;

import com.demo.core.annotation.TargetDatabase;
import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.tool.ResultCheck;
import com.demo.core.weixin.wxobj.BatchUserInfoObj;
import com.demo.core.weixin.wxobj.MpNewsMassSend;
import com.demo.core.weixin.wxobj.OpenIdList;
import com.demo.core.weixin.wxobj.OpenUser;
import com.demo.core.weixin.wxobj.OpenUserList;
import com.demo.core.weixin.wxobj.result.MassSendResult;
import com.demo.domain.wx.HdFans;
import com.demo.mapper.wx.HdFansMapper;
import com.demo.service.wx.WxFansService;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 微信粉丝
 *
 * @author hst on 2017/03/06
 **/
@Service
@Slf4j
public class WxFansServiceImpl implements WxFansService {

	@Autowired
	private WxApi wxApi;

	@Autowired
	private HdFansMapper hdFansMapper;

	@Override
	@Transactional
	@TargetDatabase(name = TargetDatabase.master)
	public String pullOpenIdList() {
		int totalSize = 0;
		OpenIdList openIdList;
		String nextOpenId = null;
		try {
			do {
				openIdList = wxApi.getUserOpenIdList(nextOpenId);
				if (Objects.nonNull(openIdList)
						&& ResultCheck.isSuccess(openIdList)) {
					nextOpenId = openIdList.getNextOpenid();
					totalSize += hdFansMapper.saveOpenIdList(openIdList
							.getOpenId());
					log.info("总共拉取用户：{}，已拉取：{}", openIdList.getTotal(),
							totalSize);
				}
			} while (Objects.nonNull(openIdList)
					&& openIdList.getCount() == OpenIdList.MAX_RECODE
					&& openIdList.getTotal() > OpenIdList.MAX_RECODE);

			return "总共获取用户数：" + totalSize;
		} catch (Exception e) {
			log.error(e.getMessage());
			return "error: " + e.getMessage();
		}
	}

	@Override
	@Transactional
	@TargetDatabase(name = TargetDatabase.master)
	public String updateByOpenIds(List<String> openIds) {
		// 总的需要更新的粉丝
		int fansSize = openIds.size();
		// 失败个数
		int failedTotalSize = 0;
		log.info("该次更新粉丝数量：{}", fansSize);
		String runningState = "";

		for (String openId : openIds) {
			try {
				if (StringUtils.isBlank(openId)) {
					failedTotalSize++;
					continue;
				}
				OpenUser openUser = wxApi.getUserInfo(openId);

				if (!ResultCheck.isSuccess(openUser)) {
					hdFansMapper.saveErrorOpenId(openId);
					log.error("用户获取信息失败，openId：{}", openId);
					failedTotalSize++;
					continue;
				}

				if (openUser.getSubscribe() == 1) {
					hdFansMapper.saveOrUpdateOpenUser(openUser);
				} else if (openUser.getSubscribe() == 0) {
					hdFansMapper.saveUnsubscribeOpenUser(openUser);
				}
				
				//删除更新后的错误openid
				//hdFansMapper.deleteByOpenId(openId);
			} catch (Exception e) {
				hdFansMapper.saveErrorOpenId(openId);
				e.printStackTrace();
				log.error("拉取用户信息错误：{}", e.getMessage());
				failedTotalSize++;
			}
		}
		runningState = "公众号该次更新粉丝完成, 总共更新成功：" + (fansSize - failedTotalSize)
				+ ", 更新失败个数: " + failedTotalSize;
		log.info(runningState);
		return runningState;
	}

	@Override
	@Transactional
	@TargetDatabase(name = TargetDatabase.master)
	public int supplyFansInfo(List<String> users, Integer outBegin,
			Integer total, boolean isFromWx) {
		int failedCount = 0, begin = 0, end = 0, size = users.size();
		boolean pullStatus = false;
		while (end < size) {

			pullStatus = false;
			begin = end;
			end = (end + BatchUserInfoObj.MAX_NUM) > size ? size
					: (end + BatchUserInfoObj.MAX_NUM);
			log.info("拉取数据: 从 {} 到 {}, 总共 {} 条数据", (outBegin + begin),
					(outBegin + end), total);

			List<String> subIncomplete = users.subList(begin, end);
			try {
				List<BatchUserInfoObj.BatchObj> userBatchInfo = new ArrayList<>();
				for (String user : subIncomplete) {
					BatchUserInfoObj.BatchObj obj = new BatchUserInfoObj.BatchObj();
					obj.setOpenId(user);
					obj.setLang("zh-CN");
					userBatchInfo.add(obj);
				}
				// 批量获取用户信息
				BatchUserInfoObj batObj = new BatchUserInfoObj(userBatchInfo);
				OpenUserList userList = wxApi.getUserInfoList(batObj);

				// 获取成功保存用户信息，并继续获取下一批
				if (Objects.nonNull(userList)
						&& ResultCheck.isSuccess(userList)) {
					// 拉取用户信息成功时处理
					// 根据openid查询，有就更新，没有就插入
					int newUser = hdFansMapper.saveOpenUserList(userList
							.getUserInfoList());
					pullStatus = true;
					// log.info("插入新用户： {}", newUser);
				} else if (isFromWx) {
					// 获取失败，且从微信端获取openId，保存拉取的openId列表
					hdFansMapper.saveErrorOpenIdList(subIncomplete);
					log.error("用户获取信息失败，总数：{}", subIncomplete.size());
				}
			} catch (Exception e) {
				hdFansMapper.saveErrorOpenIdList(subIncomplete);
				e.printStackTrace();
				log.error("拉取用户信息错误：{}", e.getMessage());
				log.error("openidList:{}",
						Arrays.toString(users.subList(begin, end).toArray()));
			}
			if (!pullStatus) {
				failedCount += (end - begin);
			}
		}
		return failedCount;
	}

	@Override
	@TargetDatabase(name = TargetDatabase.slave)
	public int getInCompleteFansSize() {
		return hdFansMapper.getInCompleteFansSize();
	}

	@Override
	@TargetDatabase(name = TargetDatabase.slave)
	public List<String> queryIncompleteInfoOpenUserList(Integer begin,
			Integer pageSize) {
		PageHelper.offsetPage(begin, pageSize, false);
		return hdFansMapper.queryIncompleteInfoOpenUserList();
	}

	@Override
	@TargetDatabase(name = TargetDatabase.master)
	public int deletePullDownOpenId() {
		return hdFansMapper.deletePullDownOpenId();
	}

	

	@Override
	@TargetDatabase(name = TargetDatabase.slave)
	public List<MassSendResult> mpNewsMassSend(int totalSend, int userOffset,
			int perSendSize, String mediaId, String userSource) {
		if (perSendSize > 10000) {
			perSendSize = 10000;
		}

		if (totalSend < perSendSize) {
			perSendSize = totalSend;
		}

		List<MassSendResult> massSendResults = new ArrayList<>();

		do {
			PageHelper.offsetPage(userOffset, perSendSize, false);

			List<String> massSendUser = "fans".equals(userSource) ? hdFansMapper
					.queryMassSendUser() : hdFansMapper.queryMassSendOpenId();

			MpNewsMassSend mpNewsMassSend = new MpNewsMassSend();
			MpNewsMassSend.MpNews mpNews = new MpNewsMassSend.MpNews();

			mpNews.setMediaId(mediaId);
			mpNewsMassSend.setToUser(massSendUser);
			mpNewsMassSend.setMpNews(mpNews);

			MassSendResult result = wxApi.mpNewsMassSend(mpNewsMassSend);
			massSendResults.add(result);
			// 如果用户数小于单次发送量则已发送完成
			if (massSendUser.size() < perSendSize) {
				break;
			}
			// 往前移动查找
			userOffset += perSendSize;
		} while ((totalSend -= perSendSize) > 0);

		log.info("群发发送结果：{}", Arrays.toString(massSendResults.toArray()));
		return massSendResults;
	}

}
