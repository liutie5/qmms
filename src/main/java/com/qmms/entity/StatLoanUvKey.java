package com.qmms.entity;

import java.io.Serializable;
import java.util.Date;

public class StatLoanUvKey implements Serializable {
    private Date statDate;
    private Long productId;
    private String channelName;

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}