package com.qmms.entity;

import java.io.Serializable;

public class StatLoanUvSumByProductDetail implements Serializable {

    private Long channelId;
    private String channelName;
    private Long sumPv;
    private Long sumUv;
    public StatLoanUvSumByProductDetail(){}
    public StatLoanUvSumByProductDetail(Long channelId, String channelName, Long sumPv, Long sumUv){
        this.channelId = channelId;
        this.channelName = channelName;
        this.sumPv = sumPv;
        this.sumUv = sumUv;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
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