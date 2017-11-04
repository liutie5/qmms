package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ser_cfg_market")
public class SerCfgMarket implements Serializable {
    @Id
    private String key;
    private String name;
    private Integer orderKey;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Integer orderKey) {
        this.orderKey = orderKey;
    }
}