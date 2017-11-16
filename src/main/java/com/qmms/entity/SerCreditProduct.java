package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="ser_credit_product")
public class SerCreditProduct implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cardId;
    private String cardName;
    private Long cardBankId;
    private String cardBankName;
    @Column(length = 1024)
    private String cardTags;
    @Column(length = 1024)
    private String cardPrivilege;
    @Column(length = 1024)
    private String cardDesc;
    @Column(length = 1024)
    private String cardImg;
    private Integer applyNum;
    private String url;
    private int status;
    private int orderedBy;
    private Integer addUserId;
    private Integer addTime;
    private Integer updateUserId;
    private Integer updateTime;
    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(name = "SerCreditProductType", joinColumns = { @JoinColumn(name = "cardId") }, inverseJoinColumns ={@JoinColumn(name = "typeKey") })
    private Set<SerCreditType> creditTypeList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "creditProduct",orphanRemoval = true)
    private Set<SerCreditProductChannelUrl> channelUrls;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Long getCardBankId() {
        return cardBankId;
    }

    public void setCardBankId(Long cardBankId) {
        this.cardBankId = cardBankId;
    }

    public String getCardBankName() {
        return cardBankName;
    }

    public void setCardBankName(String cardBankName) {
        this.cardBankName = cardBankName;
    }

    public String getCardTags() {
        return cardTags;
    }

    public void setCardTags(String cardTags) {
        this.cardTags = cardTags;
    }

    public String getCardPrivilege() {
        return cardPrivilege;
    }

    public void setCardPrivilege(String cardPrivilege) {
        this.cardPrivilege = cardPrivilege;
    }

    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(int orderedBy) {
        this.orderedBy = orderedBy;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Set<SerCreditType> getCreditTypeList() {
        return creditTypeList;
    }

    public void setCreditTypeList(Set<SerCreditType> creditTypeList) {
        this.creditTypeList = creditTypeList;
    }

    public Set<SerCreditProductChannelUrl> getChannelUrls() {
        return channelUrls;
    }

    public void setChannelUrls(Set<SerCreditProductChannelUrl> channelUrls) {
        this.channelUrls = channelUrls;
    }
}