package com.demo.core.weixin.constant;

/**
 * 菜单的点击事件
 *
 * @author hst on 2016/12/13
 */
public enum KeyButtonType {
    // 按钮点击
    CLICK("click"),
    // 链接
    VIEW("view"),
    // 扫码
    SCANCODE_PUSH("scancode_push"),
    // 扫码等待消息
    SCANCODE_WAITMSG("scancode_waitmsg"),
    // 拍照
    PIC_SYSPHOTO("pic_sysphoto"),
    // 拍照或相册发图
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),
    // 微信相册发图
    PIC_WEIXIN("pic_weixin"),
    // 选择地理位置
    LOCATION_SELECT("location_select");

    private String name;

    KeyButtonType(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public static KeyButtonType getByName(String name) {
        for (KeyButtonType type : KeyButtonType.values()) {
            if (type.getName().equals(name))
                return type;
        }
        return null;
    }
}
