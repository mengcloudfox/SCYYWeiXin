package com.scyy.weixin.message.req;



/**
 * Created by mengyun on 2016/2/16.
 */
public class TextMessage extends BaseMessage {
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
