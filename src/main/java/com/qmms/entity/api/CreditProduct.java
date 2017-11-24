package com.qmms.entity.api;

import java.io.Serializable;

public class CreditProduct implements Serializable {
    private String cardId;
    private String cardName;
    private String cardBankId;
    private String cardBankName;
    private String cardTags;
    private String cardPrivilege;
    private String cardDesc;
    private String cardImg;
    private String applyNum;
    private String url;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardBankId() {
        return cardBankId;
    }

    public void setCardBankId(String cardBankId) {
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

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}