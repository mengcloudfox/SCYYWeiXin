package com.scyy.weixin.thread;

import com.scyy.weixin.utils.AccessToken;
import com.scyy.weixin.utils.WeiXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 定时获取微信access_token的线程
 * Created by cloudloverain on 2016/2/17.
 */
public class TokenThread implements Runnable {

    private static Logger log = LoggerFactory.getLogger(TokenThread.class);

    public static AccessToken accessToken = null;

    public void run() {
        while (true) {
            try {
                accessToken = WeiXinUtil.getAccessToken();
                if (null != accessToken) {
                    log.info("获取access_Token成功,有效时长{}秒 token:{}", accessToken.getExpiresin(), accessToken.getAccesstoken());
                    //休眠7000秒，提前200秒
                    Thread.sleep((accessToken.getExpiresin() - 200) * 1000);
                } else {
//                    如果access_token为空，60秒后再获取
                    Thread.sleep(60 * 1000);
                }


            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}", e1);

                }
                log.error("{}", e);
            }
        }
    }


}
