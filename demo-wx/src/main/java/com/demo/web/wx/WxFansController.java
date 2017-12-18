package com.demo.web.wx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.utils.RedisUtil;
import com.demo.core.utils.ResponseUtil;
import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.wxobj.OpenIdList;
import com.demo.service.wx.WxFansService;

/**
 * 服务号粉丝管理
 *
 * @author hst on 2017/03/06
 **/
@Slf4j
@RestController
@RequestMapping("wx/fans")
public class WxFansController {

	private final String CLASS_NAME = WxFansController.class.getName();

	private final Object lock = new Object();

	private Random random = new Random(System.currentTimeMillis());

	private Long jobId;

	private WxFansService wxFansService;

	private WxApi wxApi;

	private RedisUtil redisUtil;

	@Autowired
	public WxFansController(WxFansService wxFansService, WxApi wxApi) {
		Assert.notNull(wxFansService, "wxFansService is required!");
		Assert.notNull(wxFansService, "wxApi is required!");
		this.wxFansService = wxFansService;
		this.wxApi = wxApi;
	}

	@Autowired
	public void setRedisUtil(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}


	/*
	 * 获取全部粉丝
	 */
	@RequestMapping("pullAllFans")
	public String pullAllFans() {

		// 判断是否开始拉取
		String isPull = isPull();
		if (Objects.nonNull(isPull)) {
			return ResponseUtil.successToClient(isPull);
		}

		jobId = random.nextLong();

		new Thread(new PullFansJob()).start();

		return ResponseUtil.successToClient("任务已提交，jobId：" + jobId);
	}

	/*
	 * 根据用户openid获取详情更新
	 */
	@RequestMapping(value="updateByOpenIds",method=RequestMethod.POST)
	public String updateByOpenIds(String openIds) {

		// 判断是否开始拉取
		String isPull = isPull();
		if (Objects.nonNull(isPull)) {
			return ResponseUtil.successToClient(isPull);
		}

		if (StringUtils.isBlank(openIds)) {
			release();
			return ResponseUtil.successToClient("传入的openId字符串不能为空");
		}

		String[] openIdArr = openIds.split(",");
		List<String> openIdsList = Arrays.asList(openIdArr);
		
		jobId = random.nextLong();
		
		new Thread(new UpdateByOpenIdsJob(openIdsList)).start();

		return ResponseUtil.successToClient("任务已提交，jobId：" + jobId);
	}

	/*
	 * 获取全部openid
	 */
	@RequestMapping("pullAllOpenIds")
	public String pullAllOpenIds() {

		// 判断是否开始拉取
		String isPull = isPull();
		if (Objects.nonNull(isPull)) {
			return ResponseUtil.successToClient(isPull);
		}

		jobId = random.nextLong();

		new Thread(new PullOpenIdListJob()).start();

		return ResponseUtil.successToClient("任务已提交，jobId：" + jobId);
	}

	@RequestMapping("supplyUserInfo")
	public String supplyUserInfo() {

		// 判断是否开始拉取
		String isPull = isPull();
		if (Objects.nonNull(isPull)) {
			return ResponseUtil.successToClient(isPull);
		}

		jobId = random.nextLong();

		new Thread(new SupplyUserInfoJob()).start();

		return ResponseUtil.successToClient("任务已提交，jobId：" + jobId);
	}

	@RequestMapping("jobStatus")
	public String jobStatus(@RequestParam Long jobId) {
		return ResponseUtil.successToClient(redisUtil.get(CLASS_NAME + "."
				+ jobId));
	}

	private String isPull() {
		// 判断是否开始拉取
		synchronized (lock) {
			String message = (String) redisUtil.get(CLASS_NAME + ".lock");
			if (Objects.nonNull(message)) {
				return message;
			} else {
				redisUtil.set(CLASS_NAME + ".lock", "lock");
				return null;
			}
		}
	}

	private void release() {
		// 解除限制
		redisUtil.remove(CLASS_NAME + ".lock");
	}

	private void updateJobStatus(String status) {
		redisUtil.set(CLASS_NAME + "." + jobId, status, 3600);
	}

	/**
	 * 拉取用户信息任务
	 */
	class PullFansJob implements Runnable {

		@Override
		public void run() {
			long beginTime = System.currentTimeMillis();
			updateJobStatus("任务进行中");

			// 输出当前状态
			String runningState;
			OpenIdList openIdList;
			String nextOpenId = null;
			// 拉取总数，失败总数
			int pullTotal = 0, failedPull = 0;
			do {
				// 第一次获取列表
				openIdList = wxApi.getUserOpenIdList(nextOpenId);
				// 存在用户
				if (Objects.nonNull(openIdList)) {
					failedPull += wxFansService.supplyFansInfo(
							openIdList.getOpenId(), pullTotal,
							openIdList.getTotal(), true);
					// 下一个openId
					nextOpenId = openIdList.getNextOpenid();
					pullTotal += openIdList.getCount();
					long runningTime = System.currentTimeMillis();
					log.info("已经运行了："
							+ (((runningTime - beginTime) / 1000) / 60) + "分钟");
					runningState = "总数：" + openIdList.getTotal() + ", 已拉取： "
							+ pullTotal + ", 失败数： " + failedPull;
				} else {
					runningState = "获取用户列表失败";
				}
				log.info(runningState);
				log.error("最后一次拉取最后用户的openId:{}", nextOpenId);
				updateJobStatus(runningState);

			}// 当获取的用户列表不为null，且拿到的用户数等于单次可获取最大数,并且总数大于单次可获取最大数
			while (Objects.nonNull(openIdList)
					&& openIdList.getCount() == OpenIdList.MAX_RECODE
					&& openIdList.getTotal() > OpenIdList.MAX_RECODE);

			long endTime = System.currentTimeMillis();
			updateJobStatus(redisUtil.get(CLASS_NAME + "." + jobId) + ", 时间："
					+ (((endTime - beginTime) / 1000) / 60) + "min");
			// 解除限制
			release();
		}
	}

	class PullOpenIdListJob implements Runnable {

		@Override
		public void run() {

			String result = wxFansService.pullOpenIdList();
			updateJobStatus(result);
			// 解除限制
			release();
		}
	}

	class SupplyUserInfoJob implements Runnable {

		@Override
		public void run() {
			// 总的需要补充信息的粉丝
			int fansSize = wxFansService.getInCompleteFansSize();
			// 失败个数
			int failedTotalSize = 0;
			log.info("公众号该次拉取粉丝数量：{}", fansSize);
			String runningState;
			int begin, end = 0, incSize = 1000;
			while (end < fansSize) {
				begin = end;
				end += incSize;

				log.info("分次拉取数据: 从 {} 到 {}, 总共 {} 条数据", begin, end, fansSize);
				List<String> users = wxFansService
						.queryIncompleteInfoOpenUserList(begin, incSize);
				failedTotalSize += wxFansService.supplyFansInfo(users, begin,
						fansSize, false);
				// 输出当前状态
				runningState = "总数：" + fansSize + ", 已拉取： " + begin + ", 失败数： "
						+ failedTotalSize;
				log.info(runningState);
				redisUtil.set(CLASS_NAME + "." + jobId, runningState);
			}
			runningState = "公众号该次拉取粉丝完成, 总共拉取成功："
					+ (fansSize - failedTotalSize) + ", 拉取失败个数: "
					+ failedTotalSize;
			log.info(runningState);
			// 删除已拉取
			wxFansService.deletePullDownOpenId();

			updateJobStatus(runningState);
			// 解除限制
			release();
		}
	}

	class UpdateByOpenIdsJob implements Runnable {
		List<String> openIds = new ArrayList<String>();

		public UpdateByOpenIdsJob(List<String> openIds) {
			this.openIds = openIds;
		}

		@Override
		public void run() {
			String runningState = wxFansService.updateByOpenIds(openIds);
			updateJobStatus(runningState);
			// 解除限制
			release();
		}

	}

}
