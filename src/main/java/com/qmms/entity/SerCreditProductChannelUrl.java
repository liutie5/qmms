package com.qmms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ser_credit_product_channel_url")
public class SerCreditProductChannelUrl implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long channelId;
    private String channelUrl;
    @ManyToOne
    @JoinColumn(name = "cardId")
    @JsonIgnore
    public SerCreditProduct creditProduct;

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

    public SerCreditProduct getCreditProduct() {
        return creditProduct;
    }

    public void setCreditProduct(SerCreditProduct creditProduct) {
        this.creditProduct = creditProduct;
    }
}