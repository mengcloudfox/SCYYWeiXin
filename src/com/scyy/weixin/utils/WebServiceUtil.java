package com.scyy.weixin.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengyun on 2016/2/23.
 */
public class WebServiceUtil {

    public static final String ENDPOINTHH = "http://192.168.0.208:8073/WebService1.asmx/HelloWorld";

    private static Logger log = LoggerFactory.getLogger(WebServiceUtil.class);

    //根据货号获取名称
    public static String getHH(String sku) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(ENDPOINTHH);
        String pm = "";
        String result = "";

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("hh", sku));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            log.info("{}",httpPost);
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                log.info("返回字符串为{}", result);
            }

            Document document = DocumentHelper.parseText(result);
            Element root = document.getRootElement();
            pm = root.getText();

        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}", e);
        }
        return pm;
    }
}
