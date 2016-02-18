package com.scyy.weixin.message.resp;

/**
 * Created by mengyun on 2016/2/16.
 */
public class LinkMessage extends BaseMessage {
    private String Title;

    private String Description;

    private String PicUrl;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
