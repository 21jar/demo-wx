package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 微信汽车卡
 *
 * @author hst on 2017/04/28
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CarCard {

    private Card card;

    @Data
    public static class Card {

        @JsonProperty("card_type")
        @JSONField(name = "card_type")
        private String cardType = "GENERAL_CARD";

        @JsonProperty("general_card")
        @JSONField(name = "general_card")
        private GeneralCard generalCard;

        public Card(GeneralCard generalCard) {
            this.generalCard = generalCard;
        }
    }

    @Data
    public static class GeneralCard {

        @JsonProperty("sub_card_type")
        @JSONField(name = "sub_card_type")
        private String subCardType = "CAR_CARD";

        @JsonProperty("base_info")
        @JSONField(name = "base_info")
        private BaseInfo baseInfo;

        @JsonProperty("supply_bonus")
        @JSONField(name = "supply_bonus")
        private Boolean supplyBonus;

        @JsonProperty("supply_balance")
        @JSONField(name = "supply_balance")
        private Boolean supplyBalance;

        @JsonProperty("activate_url")
        @JSONField(name = "activate_url")
        private String activateUrl;

        @JsonProperty("background_pic_url")
        @JSONField(name = "background_pic_url")
        private String backgroundPicUrl;

        @JsonProperty("custom_field1")
        @JSONField(name = "custom_field1")
        private CustomField customField1;

        @JsonProperty("custom_field2")
        @JSONField(name = "custom_field2")
        private CustomField customField2;

        @JsonProperty("custom_field3")
        @JSONField(name = "custom_field3")
        private CustomField customField3;

        @JsonProperty("custom_cell1")
        @JSONField(name = "custom_cell1")
        private CustomCell customCell1;

        @JsonProperty("custom_cell2")
        @JSONField(name = "custom_cell2")
        private CustomCell customCell2;
    }

    @Data
    public static class BaseInfo {

        @JsonProperty("logo_url")
        @JSONField(name = "logo_url")
        private String logoUrl;

        @JsonProperty("brand_name")
        @JSONField(name = "brand_name")
        private String brandName;

        @JsonProperty("code_type")
        @JSONField(name = "code_type")
        private String codeType;

        private String title;

        private String color;

        private String notice;

        @JsonProperty("service_phone")
        @JSONField(name = "service_phone")
        private String servicePhone;

        private String description;

        @JsonProperty("date_info")
        @JSONField(name = "date_info")
        private DateInfo dateInfo;

        private Sku sku;

        @JsonProperty("get_limit")
        @JSONField(name = "get_limit")
        private Integer getLimit;

        @JsonProperty("location_id_list")
        @JSONField(name = "location_id_list")
        private List<String> locationIdList;

        @JsonProperty("center_url")
        @JSONField(name = "center_url")
        private String centerUrl;

        @JsonProperty("center_title")
        @JSONField(name = "center_title")
        private String centerTitle;

        @JsonProperty("center_sub_title")
        @JSONField(name = "center_sub_title")
        private String centerSubTitle;

        @JsonProperty("custom_url")
        @JSONField(name = "custom_url")
        private String customUrl;

        @JsonProperty("custom_url_name")
        @JSONField(name = "custom_url_name")
        private String customUrlName;

        @JsonProperty("custom_url_sub_title")
        @JSONField(name = "custom_url_sub_title")
        private String customUrlSubTitle;

        @JsonProperty("promotion_url")
        @JSONField(name = "promotion_url")
        private String promotionUrl;

        @JsonProperty("promotion_url_name")
        @JSONField(name = "promotion_url_name")
        private String promotionUrlName;

        @JsonProperty("promotion_url_sub_title")
        @JSONField(name = "promotion_url_sub_title")
        private String promotionUrlSubTitle;

        @JsonProperty("need_push_on_view")
        @JSONField(name = "need_push_on_view")
        private Boolean needPushOnView;

        @JsonProperty("use_dynamic_code")
        @JSONField(name = "use_dynamic_code")
        private Boolean useDynamicCode;

        @JsonProperty("use_custom_code")
        @JSONField(name = "use_custom_code")
        private Boolean useCustomCode;

        @JsonProperty("can_give_friend")
        @JSONField(name = "can_give_friend")
        private Boolean canGiveFriend;
    }

    @Data
    public static class DateInfo {

        private String type;

        @JsonProperty("begin_timestamp")
        @JSONField(name = "begin_timestamp")
        private String beginTimestamp;

        @JsonProperty("end_timestamp")
        @JSONField(name = "end_timestamp")
        private String endTimestamp;

        @JsonProperty("fixed_term")
        @JSONField(name = "fixed_term")
        private Integer fixedTerm;

        @JsonProperty("fixed_begin_term")
        @JSONField(name = "fixed_begin_term")
        private Integer fixedBeginTerm;
    }

    @Data
    @AllArgsConstructor
    public static class Sku {

        private Integer quantity;
    }

    @Data
    public static class CustomField {

        @JsonProperty("name_type")
        @JSONField(name = "name_type")
        private String nameType;

        private String name;

        private String url;
    }

    @Data
    @AllArgsConstructor
    public static class CustomCell {

        private String name;

        private String tips;

        private String url;
    }

    /*
    * 获取自定义的customField
    * */
    public static CustomField getSelfCustomField(String name, String url) {
        CustomField c = new CustomField();
        c.setName(name);
        c.setUrl(url);
        return c;
    }

    /*
    * 获取自定义的customField
    * */
    public static CustomField getWxCustomField(String nameType, String url) {
        CustomField c = new CustomField();
        c.setNameType(nameType);
        c.setUrl(url);
        return c;
    }
}
