package com.qmms.entity;

import java.io.Serializable;
import java.util.Date;

public class StatCreditUvKey implements Serializable {
    private Date statDate;
    private Long bankId;
    private String channelName;

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}