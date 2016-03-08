package com.scyy.weixin.service;

import com.scyy.weixin.thread.TokenThread;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengyun on 2016/2/29.
 */
public class SendMessageService {

    private static  final String postURL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

    public static void sendMessage() {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpPost httpPost = new HttpPost(postURL);

        String accessToken = TokenThread.accessToken.getAccesstoken();

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("access_token",accessToken));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

}
