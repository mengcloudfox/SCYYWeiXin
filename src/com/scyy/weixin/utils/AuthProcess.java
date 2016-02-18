package com.scyy.weixin.utils;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by mengyun on 2016/2/16.
 */
public class AuthProcess {

    public final static String Token = "dwjwlxs611";

    public final static String EncodingAESKey = "ydmkQ2bNVekkAUs8qybx9Anm3RehDN8kdgNJG9CHig2";

    public final static String CorpID = "wx71d4e76e5a0df551";

    //重新封装验证url
    public static String verifyURL(HttpServletRequest request){

        String signature = request.getParameter("msg_signature");

        //时间戳
        String timestamp = request.getParameter("timestamp");

        //nonce
        String nonce = request.getParameter("nonce");

        //返回值
        String echostr = request.getParameter("echostr");


        WXBizMsgCrypt wxBizMsgCrypt;
        try {
            wxBizMsgCrypt = new WXBizMsgCrypt(Token, EncodingAESKey, CorpID);

            return wxBizMsgCrypt.VerifyURL(signature, timestamp, nonce, echostr);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //解密消息
    public static String decryptMsg(HttpServletRequest request,String originalXML){
        String msgSignature = request.getParameter("msg_signature");

        String timestamp = request.getParameter("timestamp");

        String nonce = request.getParameter("nonce");

        try{
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(Token,EncodingAESKey,CorpID);
            return wxBizMsgCrypt.DecryptMsg(msgSignature,timestamp,nonce,originalXML);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //加密消息
    public static String encryptMsg(HttpServletRequest request,String replyXML){
        String timestamp = request.getParameter("timestamp");

        String nonce = request.getParameter("nonce");

        try{
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(Token,EncodingAESKey,CorpID);
            return wxBizMsgCrypt.EncryptMsg(replyXML, timestamp, nonce);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
