package com.scyy.weixin.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mengyun on 2016/2/23.
 */
public class WebServiceUtilTest {

    @Test
    public void testGetHH() throws Exception {
        String hh = "1110001";

        String pm = WebServiceUtil.getHH(hh);

        System.out.print(pm);

        assertNotNull(pm);
    }
}