package com.scyy.weixin.message.req;

/**
 * Created by mengyun on 2016/2/16.
 */
public class VideoMessage extends BaseMessage {
    private String MediaId;

    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
