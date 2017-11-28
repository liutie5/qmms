package com.qmms.entity;

import java.io.Serializable;

public class StatLoanUvSumByProduct implements Serializable {

    private Long productId;
    private String productName;
    private Long sumPv;
    private Long sumUv;
    public StatLoanUvSumByProduct(){}
    public StatLoanUvSumByProduct(Long productId,String productName,Long sumPv,Long sumUv){
        this.productId = productId;
        this.productName = productName;
        this.sumPv = sumPv;
        this.sumUv = sumUv;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getSumPv() {
        return sumPv;
    }

    public void setSumPv(Long sumPv) {
        this.sumPv = sumPv;
    }

    public Long getSumUv() {
        return sumUv;
    }

    public void setSumUv(Long sumUv) {
        this.sumUv = sumUv;
    }
}