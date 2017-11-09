package com.qmms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ser_loan_product_channel_url")
public class SerLoanProductChannelUrl implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long channelId;
    private String channelUrl;
    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonIgnore
    public SerLoanProduct loanProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public SerLoanProduct getLoanProduct() {
        return loanProduct;
    }

    public void setLoanProduct(SerLoanProduct loanProduct) {
        this.loanProduct = loanProduct;
    }



}