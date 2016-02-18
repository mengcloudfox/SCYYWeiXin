package com.scyy.weixin.utils;

import com.scyy.weixin.menu.Button;
import com.scyy.weixin.menu.ClickButton;
import com.scyy.weixin.menu.Menu;
import com.scyy.weixin.menu.ViewButton;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by mengyun on 2016/2/17.
 */
public class WeiXinUtil {
    private static final String CORPID = "wx71d4e76e5a0df551";
    private static final String CORPSECRET = "u_WXEYbSf4imF6OsjHs4JEoiXFtXyt3W00FCaFQsgYBGOToE6WTkpehQd6ZKKEzK";

    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRET";

    private static final String UPLOAD_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    private static final String CREATE_MENU_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENTID";

    private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);

    public static JSONObject doGetStr(String url) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject doPostStr(String url, String outStr) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }

    /**
     * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken(){
        AccessToken accessToken = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("CORPID",CORPID).replace("CORPSECRET",CORPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null){
            accessToken.setAccesstoken(jsonObject.getString("access_token"));
            accessToken.setExpiresin(jsonObject.getLong("expires_in"));
        }
        return accessToken;
    }

    public static String upload(String filePath,String accessToken,String type) throws IOException,NoSuchAlgorithmException,NoSuchProviderException,KeyManagementException{
        File file = new File(filePath);
        if(!file.exists() || !file.isFile()){
            log.error("文件不存在");
            throw new IOException("文件不存在");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);

        URL urlObj = new URL(url);

//        连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

//        设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

//        设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");
//获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
//        输出表头
        out.write(head);

//        文件正文
//        把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

//        结尾
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            //定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject.toString());

        String typeName = "media_id";
        if(!"image".equals(type)){
            typeName = type + "_media_id";
        }
        String mediaId = jsonObject.getString(typeName);
        return mediaId;





    }

    public static Menu initMenu(){
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("11");

        ViewButton button21 = new ViewButton();
        button21.setName("公司网站");
        button21.setType("view");
        button21.setUrl("http://www.scyy.com");

        ClickButton button31 = new ClickButton();
        button31.setName("扫码录入");
        button31.setType("scancode_push");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("地理位置");
        button32.setType("location_select");
        button32.setKey("32");

        Button button = new Button();
        button.setName("物流菜单");
        button.setSub_button(new Button[]{button31,button32});

        menu.setButtons(new Button[]{button11,button21,button});
        return menu;
    }

    public static int createMenu(String accessToken,String agentID,String menu){
        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN",accessToken).replace("AGENTID", agentID);
        JSONObject jsonObject = doPostStr(url, menu);
        if (null != jsonObject){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }


}
