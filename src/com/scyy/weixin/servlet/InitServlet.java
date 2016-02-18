package com.scyy.weixin.servlet;

import com.scyy.weixin.thread.TokenThread;
import com.scyy.weixin.utils.WeiXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;



/**
 * Created by mengyun on 2016/2/17.
 */
public class InitServlet extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);

    public void init() throws ServletException {

//        暂时把CORPID,CORPSECRET写死在程序中，以后应该移植到配置中
        new Thread(new TokenThread()).start();


    }


}
