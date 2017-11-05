package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ser_channel_umeng")
@IdClass(ChannelUmengPk.class)
public class SerChannelUmeng implements Serializable {

    @Id
    private String umengKey;
    @Id
    private String marketId;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="channelId")
    private SerChannel serChannel;

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

    public SerChannel getSerChannel() {
        return serChannel;
    }

    public void setSerChannel(SerChannel serChannel) {
        this.serChannel = serChannel;
    }
}