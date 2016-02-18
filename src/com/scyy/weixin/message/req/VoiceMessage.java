package com.scyy.weixin.message.req;

/**
 * Created by mengyun on 2016/2/16.
 */
public class VoiceMessage extends BaseMessage {
    private String MediaId;

    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
