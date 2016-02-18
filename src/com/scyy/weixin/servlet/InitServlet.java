package com.scyy.weixin.servlet;

import com.scyy.weixin.thread.TokenThread;
import com.scyy.weixin.utils.AccessToken;
import com.scyy.weixin.utils.WeiXinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by mengyun on 2016/2/17.
 */
public class InitServlet extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);

    public void init() throws ServletException {

//        暂时把CORPID,CORPSECRET写死在程序中，以后应该移植到配置中
        new Thread(new TokenThread()).start();

        try{
            AccessToken token = TokenThread.accessToken;
            String agentID = "0";


            String menu = JSONObject.fromObject(WeiXinUtil.initMenu()).toString();
            int result = WeiXinUtil.createMenu(token.getAccesstoken(), agentID, menu);
            if(0 == result){
                log.info("成功创建菜单！");
            }else
            {
                log.error("创建菜单失败,错误码为:{}",result);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("{}",e);
        }

    }


}
