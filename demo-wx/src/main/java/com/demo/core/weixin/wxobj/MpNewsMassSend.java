package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 图文高级群发接口对象
 *
 * @author hst on 2017/03/07
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MpNewsMassSend {

    @JSONField(name = "touser")
    @JsonProperty("touser")
    private List<String> toUser;

    @JSONField(name = "mpnews")
    @JsonProperty("mpnews")
    private MpNews mpNews;

    @JSONField(name = "msgtype")
    @JsonProperty("msgtype")
    private String msgType;

    public MpNewsMassSend() {
        this.msgType = "mpnews";
    }

    @Getter
    @Setter
    public static class MpNews {

        @JSONField(name = "media_id")
        @JsonProperty("media_id")
        private String mediaId;

    }

}
