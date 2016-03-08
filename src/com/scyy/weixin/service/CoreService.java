package com.scyy.weixin.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.scyy.weixin.message.resp.TextMessage;
import com.scyy.weixin.utils.*;
import javafx.beans.binding.ObjectExpression;


/**
 * Created by mengyun on 2016/2/16.
 */
public class CoreService {
    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        String enRespMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            //解密数据
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s ="";
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            String originalXML = sb.toString();


            System.out.println("解密前数据为:" + originalXML);
            //解密xml数据
            String dXML = AuthProcess.decryptMsg(request, originalXML);

            System.out.println("解密后数据为:" + dXML);


            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(dXML);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            String msgContent = requestMap.get("Content");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);


            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                if (msgContent.equals("贾洪斌")) {
                    respContent = "贾总您好！有什么需要为您效劳的？";
                }else if(msgContent.length() >= 2 && (!WeiXinUtil.isNumeric(msgContent))) {
                    Map drugMap = (Map)WebServiceUtil.getDrug(msgContent).clone();
                    if(drugMap.keySet().size() >= 30) {
                        respContent = "系统查询相关匹配品种结果共"+
                                drugMap.keySet().size() +
                                "条。查询结果过多，请输入更为详细的品种信息或者使用其他方式进行查询!";
                    }else {
                        respContent = "";
                        Iterator iter = drugMap.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry entry = (Map.Entry) iter.next();
                            respContent += entry.getValue().toString();
                        }
                    }

                    /*for (HashMap.Entry<String, Drug> entry : drugMap.entrySet()) {

                        System.out.println("货号 = " + entry.getKey() + ", Value = " + entry.getValue().toString());
                        respContent = respContent + entry.getValue().toString();
                    }*/
                }else if (msgContent.length() >= 7 && (WeiXinUtil.isNumeric(msgContent))){
                    List<DrugStock> drugStockList = WebServiceUtil.getDrugStock(msgContent);
                    respContent = "";
                    Iterator iterator = drugStockList.listIterator();
                    while(iterator.hasNext()) {
                        DrugStock drugStock = (DrugStock)iterator.next();
                        respContent += drugStock.toString();
                    }
                }

                else
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                }
            }

            if (msgType.equals(MessageUtil.RESP_MESSAGE_TYPE_TEXT)) {
                //消息长度
                System.out.println("消息长度为: "+ msgContent.getBytes("UTF-8").length);

                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                System.out.println("发送信息明文为:" + respMessage);

                //加密返回信息
                enRespMessage = AuthProcess.encryptMsg(request, respMessage);
                System.out.println("发送信息密文为:" + enRespMessage);

            }
            /**
             * 其他的就返回一个图文消息做测试用
             */
            else{
                respMessage = MessageUtil.initNewsMessage(toUserName, fromUserName);
                System.out.println("发送信息明文为:" + respMessage);
                enRespMessage = AuthProcess.encryptMsg(request, respMessage);
                System.out.println("发送信息密文为:" + enRespMessage);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return enRespMessage;
    }

}
