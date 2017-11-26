package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="stat_loan_uv_channel")
@IdClass(StatUvKey.class)
public class StatLoanUvChannel implements Serializable {
    @Id
    private Date statDate;
    @Id
    private Long channelId;
    @Id
    private String pkgKey;
    @Id
    private String source;
    private Long pv;
    private Long uv;
    private int addTime;

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

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }
}