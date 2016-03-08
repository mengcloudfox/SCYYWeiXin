package com.scyy.weixin.utils;

import java.math.BigDecimal;

/**
 * Created by mengyun on 2016/3/1.
 */
public class DrugStock {

    //基药
    private String jy;
//货主
    private String deptName;
//货号
    private String goods;
//品名+规格
    private String name;
//厂家
//    private String producer;
//助记码
    private String prcHead;
//包装
    private Integer packNum;
//单位
    private String msUnitNo;
//可分配数量
    private Integer allo_qty;
//可分配件数
    private Integer packPiece;
//质量状况
    private String status;
//    不可分配数量
    private Integer unallo_qty;
//    及时库存
    private Integer jskc;
//    挂网价
    private BigDecimal webPrice;
//零售价
    private BigDecimal rtlPrice;
//最新含税进价
    private BigDecimal lastPurPrice;
//经营范围
    private String jyfw;
//责任采购员姓名
    private String zycgy;

    public String getJy() {
        return jy;
    }

    public void setJy(String jy) {
        this.jy = jy;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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

    public String getPrcHead() {
        return prcHead;
    }

    public void setPrcHead(String prcHead) {
        this.prcHead = prcHead;
    }

    public Integer getPackNum() {
        return packNum;
    }

    public void setPackNum(Integer packNum) {
        this.packNum = packNum;
    }

    public String getMsUnitNo() {
        return msUnitNo;
    }

    public void setMsUnitNo(String msUnitNo) {
        this.msUnitNo = msUnitNo;
    }

    public Integer getAllo_qty() {
        return allo_qty;
    }

    public void setAllo_qty(Integer allo_qty) {
        this.allo_qty = allo_qty;
    }

    public Integer getPackPiece() {
        return packPiece;
    }

    public void setPackPiece(Integer packPiece) {
        this.packPiece = packPiece;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUnallo_qty() {
        return unallo_qty;
    }

    public void setUnallo_qty(Integer unallo_qty) {
        this.unallo_qty = unallo_qty;
    }

    public Integer getJskc() {
        return jskc;
    }

    public void setJskc(Integer jskc) {
        this.jskc = jskc;
    }

    public BigDecimal getWebPrice() {
        return webPrice;
    }

    public void setWebPrice(BigDecimal webPrice) {
        this.webPrice = webPrice;
    }

    public BigDecimal getRtlPrice() {
        return rtlPrice;
    }

    public void setRtlPrice(BigDecimal rtlPrice) {
        this.rtlPrice = rtlPrice;
    }

    public BigDecimal getLastPurPrice() {
        return lastPurPrice;
    }

    public void setLastPurPrice(BigDecimal lastPurPrice) {
        this.lastPurPrice = lastPurPrice;
    }

    public String getJyfw() {
        return jyfw;
    }

    public void setJyfw(String jyfw) {
        this.jyfw = jyfw;
    }

    public String getZycgy() {
        return zycgy;
    }

    public void setZycgy(String zycgy) {
        this.zycgy = zycgy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("基药: ").append(this.jy).append("\n");
        sb.append("货主: ").append(this.deptName).append("\n");
        sb.append("货号: ").append(this.goods).append("\n");
        sb.append("品名规格: ").append(this.name).append("\n");
        sb.append("助记码: ").append(this.prcHead).append("\n");
        sb.append("包装: ").append(this.packNum).append("\n");
        sb.append("单位: ").append(this.msUnitNo).append("\n");
        sb.append("可分配数量: ").append(this.allo_qty).append("\n");
        sb.append("可分配件数: ").append(this.packPiece).append("\n");
        sb.append("质量状况: ").append(this.status).append("\n");
        sb.append("不可分配数量: ").append(this.unallo_qty).append("\n");
        sb.append("即时库存: ").append(this.jskc).append("\n");
        sb.append("挂网价: ").append(this.webPrice).append("\n");
        sb.append("零售价: ").append(this.rtlPrice).append("\n");
        sb.append("最新含税进价: ").append(this.lastPurPrice).append("\n");
        sb.append("经营范围: ").append(this.jyfw).append("\n");
        sb.append("责任采购员: ").append(this.zycgy).append("\n");
        return sb.toString();
    }
}
