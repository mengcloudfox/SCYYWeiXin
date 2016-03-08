package com.scyy.weixin.utils;

import com.scyy.weixin.department.Depart;
import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

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

    @org.junit.Test
    public void testCreateDepart() throws Exception {

//        AccessToken token = new AccessToken();
        AccessToken token = WeiXinUtil.getAccessToken();
//        token.setAccesstoken(TokenThread.accessToken.getAccesstoken());
//        token.setExpiresin(7000);

        String depart = JSONObject.fromObject(WeiXinUtil.initDepart()).toString();

        System.out.println(depart);

        int result = WeiXinUtil.createDepart(token.getAccesstoken(),depart);

        assertEquals("测试不成功",0, result);

    }

    @org.junit.Test
    public void testGetDeparts() throws Exception {

        AccessToken token = new AccessToken();
        token.setAccesstoken(WeiXinUtil.getAccessToken().getAccesstoken());

        List<Depart> departs = new ArrayList<>();
        departs = (ArrayList<Depart>)WeiXinUtil.getDeparts(token.getAccesstoken(),0).clone();

        System.out.println(departs);

        }

    @org.junit.Test
    public void testDeleteDeparts() throws Exception {

        AccessToken token = new AccessToken();
        token.setAccesstoken(WeiXinUtil.getAccessToken().getAccesstoken());

        Depart depart = new Depart();
        depart.setParentid("1");
        depart.setOrder("200");
        depart.setId(3);
        depart.setName("测试部门");
        String result = WeiXinUtil.deleteDepart(token.getAccesstoken(), depart);

        assertEquals("出现问题","0",result);

    }

    @org.junit.Test
    public void testUpdateDeparts() throws Exception {

        AccessToken token = new AccessToken();
        token.setAccesstoken(WeiXinUtil.getAccessToken().getAccesstoken());

        Depart depart = new Depart();
        depart.setParentid("1");
        depart.setOrder("200");
        depart.setId(2);
        depart.setName("省公司信息部");
        String result = WeiXinUtil.updateDepart(token.getAccesstoken(), depart);

        assertEquals("出现问题","0",result);

    }
}