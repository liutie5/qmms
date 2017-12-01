package com.qmms.entity;

import java.io.Serializable;

public class StatCreditUvSumByBankDetail implements Serializable {

    private String channelName;
    private Long sumPv;
    private Long sumUv;
    public StatCreditUvSumByBankDetail(){}
    public StatCreditUvSumByBankDetail(String channelName, Long sumPv, Long sumUv){
        this.channelName = channelName;
        this.sumPv = sumPv;
        this.sumUv = sumUv;
    }


    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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