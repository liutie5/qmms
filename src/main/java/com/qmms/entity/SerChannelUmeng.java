package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ser_channel_umeng")
public class SerChannelUmeng implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String umengKey;
    private String marketId;

    @ManyToOne
    @JoinColumn(name="channelId")
    private SerChannel serChannel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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