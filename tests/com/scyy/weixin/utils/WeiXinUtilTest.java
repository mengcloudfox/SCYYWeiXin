package com.scyy.weixin.utils;

import net.sf.json.JSONObject;

import static org.junit.Assert.*;

/**
 * Created by mengyun on 2016/2/18.
 */
public class WeiXinUtilTest {

    @org.junit.Test
    public void testDoGetStr() throws Exception {

    }

    @org.junit.Test
    public void testDoPostStr() throws Exception {

    }

    @org.junit.Test
    public void testGetAccessToken() throws Exception {

    }

    @org.junit.Test
    public void testUpload() throws Exception {

    }

    @org.junit.Test
    public void testInitMenu() throws Exception {

    }

    @org.junit.Test
    public void testCreateMenu() throws Exception {
        AccessToken token = WeiXinUtil.getAccessToken();

        String menu = JSONObject.fromObject(WeiXinUtil.initMenu()).toString();

        System.out.println(menu);

        int result = WeiXinUtil.createMenu(token.getAccesstoken(),"0",menu);

        assertEquals("测试不成功",0, result);

    }
}