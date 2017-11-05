package com.qmms.entity;

import java.io.Serializable;

public class ChannelUmengPk implements Serializable {
    private String umengKey;
    private String marketId;

    public String getUmengKey() {
        return umengKey;
    }

    public void setUmengKey(String umengKey) {
        this.umengKey = umengKey;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
