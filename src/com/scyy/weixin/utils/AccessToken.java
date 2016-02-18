package com.scyy.weixin.utils;

/**
 * Created by mengyun on 2016/2/17.
 */
public class AccessToken {

    private String accesstoken;

    private long expiresin;

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public long getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(long expiresin) {
        this.expiresin = expiresin;
    }


}
