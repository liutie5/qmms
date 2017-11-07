package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ser_loan_product_channel_url")
public class SerLoanProductChannelUrl implements Serializable {
    @Id
    private Long productId;
    @Id
    private Long channelId;
    private Integer channelUrl;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
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