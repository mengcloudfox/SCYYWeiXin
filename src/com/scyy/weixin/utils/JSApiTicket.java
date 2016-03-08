package com.scyy.weixin.utils;

/**
 * Created by mengyun on 2016/3/2.
 */
public class JSApiTicket {
    private String ticket;

    private long expiresin;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public long getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(long expiresin) {
        this.expiresin = expiresin;
    }
}
