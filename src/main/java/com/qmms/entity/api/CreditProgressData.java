package com.qmms.entity.api;


public class CreditProgressData {
    private Long bankId;
    private String bankName;
    private String bankLogo;
    private String bankDesc;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
