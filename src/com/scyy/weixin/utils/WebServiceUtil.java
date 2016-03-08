package com.scyy.weixin.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by mengyun on 2016/2/23.
 */
public class WebServiceUtil {

    public static final String ENDPOINTSTOCK = "http://192.168.0.208:8073/WebService1.asmx/Getstock";

    public static final String ENDPOINTWAREDICT = "http://192.168.0.208:8073/WebService1.asmx/Getwaredict";
//    写死用户名
    public static final String USERNAME = "admin";
//    写死密码

    public static final String PASSWORD = "scyyxxzx";

    private static Logger log = LoggerFactory.getLogger(WebServiceUtil.class);

    public static HashMap<String, Drug> getDrug(String sku) {
        //bulider
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //client
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpPost httpPost = new HttpPost(ENDPOINTWAREDICT);


        //httpPost.setConfig(RequestConfig.DEFAULT);

        String drugInfo = ""; //返回商品信息
        String result = "";


        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username",USERNAME));
        nvps.add(new BasicNameValuePair("password",PASSWORD));
        nvps.add(new BasicNameValuePair("names", sku));
        Map<String,Drug> drugMap = new HashMap<>();

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            log.info("{}",httpPost);

            System.out.println(httpPost.toString());


            HttpResponse response = closeableHttpClient.execute(httpPost);
            System.out.println("返回值为："+response.getStatusLine().getStatusCode());

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                log.info("返回字符串为{}", result);
            }


            Document document = DocumentHelper.parseText(result);
            Element root = document.getRootElement();
            for(Iterator it = root.elementIterator();it.hasNext();){
                Element childEle = (Element)it.next();
                Drug drug = new Drug();
                drug.setGoods(childEle.element("Goods").getText());
                drug.setName(childEle.element("Name").getText());
                drug.setSpec(childEle.element("Spec").getText());
                drug.setProducer(childEle.element("Producer").getText());
                if (null != drug){
                    drugMap.put(drug.getGoods(),drug);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}", e);
        }
        return (HashMap<String, Drug>) drugMap;
    }

    public static ArrayList<DrugStock> getDrugStock(String sku) {
        //bulider
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //client
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpPost httpPost = new HttpPost(ENDPOINTSTOCK);


        //httpPost.setConfig(RequestConfig.DEFAULT);

        String drugInfo = ""; //返回商品信息
        String result = "";


        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username",USERNAME));
        nvps.add(new BasicNameValuePair("password",PASSWORD));
        nvps.add(new BasicNameValuePair("goods", sku));
        List<DrugStock> drugStockList = new ArrayList<>();

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            log.info("{}",httpPost);

            System.out.println(httpPost.toString());


            HttpResponse response = closeableHttpClient.execute(httpPost);
            System.out.println("返回值为："+response.getStatusLine().getStatusCode());

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                log.info("返回字符串为{}", result);
            }


            Document document = DocumentHelper.parseText(result);
            Element root = document.getRootElement();
            for(Iterator it = root.elementIterator();it.hasNext();){
                Element childEle = (Element)it.next();
                DrugStock drugStock = new DrugStock();
                drugStock.setJy(childEle.element("Jy").getText());
                drugStock.setDeptName(childEle.elementText("Deptname"));
                drugStock.setGoods(childEle.elementText("Goods"));
                drugStock.setName(childEle.elementText("Name"));
                drugStock.setPrcHead(childEle.elementText("Prchead"));
                drugStock.setPackNum(Integer.parseInt(childEle.elementText("Packnum")));
                drugStock.setMsUnitNo(childEle.elementText("Msunitno"));
                drugStock.setAllo_qty(Integer.parseInt(childEle.elementText("Allo_qty")));
                drugStock.setPackPiece(Integer.parseInt(childEle.elementText("Packpiece")));
                drugStock.setStatus(childEle.elementText("Status"));
                drugStock.setUnallo_qty(Integer.parseInt(childEle.elementText("Unallo_qty")));
                drugStock.setJskc(Integer.parseInt(childEle.elementText("Jskc")));
                drugStock.setWebPrice(new BigDecimal((childEle.elementText("Webprc"))));
                drugStock.setRtlPrice(new BigDecimal((childEle.elementText("Rtlprc"))));
                drugStock.setLastPurPrice(new BigDecimal((childEle.elementText("Lastpurprc"))));
                drugStock.setJyfw(childEle.elementText("Jyfw"));
                drugStock.setZycgy(childEle.elementText("Zycgy"));


                if (null != drugStock){
                    drugStockList.add(drugStock);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}", e);
        }
        return (ArrayList<DrugStock>)drugStockList;
    }


}
