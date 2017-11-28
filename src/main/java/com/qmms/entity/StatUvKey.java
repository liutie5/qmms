package com.qmms.entity;

import java.io.Serializable;
import java.util.Date;

public class StatUvKey implements Serializable {
    private Date statDate;
    private Long productId;
    private Long channelId;
    private String pkgKey;
    private String source;

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getPkgKey() {
        return pkgKey;
    }

    public void setPkgKey(String pkgKey) {
        this.pkgKey = pkgKey;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}