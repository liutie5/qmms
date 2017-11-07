package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ser_loan_product_channel_url")
public class SerLoanProductChannelUrl implements Serializable {
    private Integer productId;
    private Integer channelId;
    private Integer channelUrl;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(Integer channelUrl) {
        this.channelUrl = channelUrl;
    }

    @ManyToOne
    @JoinColumn(name = "productId")
    public SerLoanProduct loanProduct;
}