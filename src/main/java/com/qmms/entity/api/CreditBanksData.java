package com.qmms.entity.api;


public class CreditBanksData {
    private Long bankId;
    private String bankName;
    private String bankLogo;
    private String bankDesc;
    private String bankTag;
    private String isCOO;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getBankDesc() {
        return bankDesc;
    }

    public void setBankDesc(String bankDesc) {
        this.bankDesc = bankDesc;
    }

    public String getBankTag() {
        return bankTag;
    }

    public void setBankTag(String bankTag) {
        this.bankTag = bankTag;
    }

    public String getIsCOO() {
        return isCOO;
    }

    public void setIsCOO(String isCOO) {
        this.isCOO = isCOO;
    }
}
