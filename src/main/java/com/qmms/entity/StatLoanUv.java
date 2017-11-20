package com.qmms.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="stat_loan_uv")
public class StatLoanUv implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pkgKey;
    private String source;
    private String type;
    private String pid;
    private String fallback;
    private String deviceId;
    private int addTime;

    public StatLoanUv(){}
    public StatLoanUv(String pkgKey,String source,String type,String pid,String fallback,String deviceId){
        this.pkgKey = pkgKey;
        this.source = source;
        this.type = type;
        this.pid = pid;
        this.fallback = fallback;
        this.deviceId = deviceId;
        this.addTime =(int)(System.currentTimeMillis()/1000);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }


}