package com.scyy.weixin.utils;

/**
 * Created by mengyun on 2016/2/26.
 * 药片信息类
 */
public class Drug {
//货号
    private String goods;
//品名
    private String name;
//规格
    private String spec;
//生产厂家
    private String producer;
//后续可以添加


    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        String string = "货号:" + goods +"\n品名:" + name + "\n规格:" + spec + "\n厂家:" + producer +"\n";
        return string;
    }
}
