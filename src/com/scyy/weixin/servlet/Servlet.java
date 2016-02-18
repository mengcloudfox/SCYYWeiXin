package com.scyy.weixin.servlet;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scyy.weixin.service.CoreService;
import com.scyy.weixin.utils.AuthProcess;

/**
 * Created by mengyun on 2016/2/16.
 */
public class Servlet extends HttpServlet {



    public Servlet(){
        super();
    }


    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {


        String respMessage = CoreService.processRequest(request);

        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //明文返回字符串
       String dEchoStr = AuthProcess.verifyURL(request);

       System.out.println("验证明文为:" + dEchoStr);

       PrintWriter out = response.getWriter();
       try {
           out.print(dEchoStr);
       }catch (Exception e){
           e.printStackTrace();
           out.close();
           out = null;
       }
    }
}
