package com.qmms.entity;

import java.io.Serializable;

public class StatCreditUvSumByBank implements Serializable {

    private Long bankId;
    private String bankName;
    private Long sumPv;
    private Long sumUv;
    public StatCreditUvSumByBank(){}
    public StatCreditUvSumByBank(Long bankId, String bankName, Long sumPv, Long sumUv){
        this.bankId = bankId;
        this.bankName = bankName;
        this.sumPv = sumPv;
        this.sumUv = sumUv;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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