package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ser_loan_product")
public class SerLoanProduct implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="`name`")
    private String name;
    private String company;
    @Column(length = 1024)
    private String balanceDesc;
    private Long minBalance;
    private Long maxBalance;
    private String rate;
    private String rateDesc;
    private String term;
    private String termDesc;
    private Integer speed;
    private String speedDesc;
    private String tags;
    private String slogan;
    private String productDesc;
    private String productCondition;
    private String productProcess;
    private Integer applyNum;
    private String img;
    private String url;
    private int status;
    private int orderedBy;
    private Integer addUserId;
    private Integer addTime;
    private Integer updateUserId;
    private Integer updateTime;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(name = "SerLoanProductType", joinColumns = { @JoinColumn(name = "productId") }, inverseJoinColumns ={@JoinColumn(name = "typeKey") })
    private List<SerLoanType> loanTypeList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "loanProduct",orphanRemoval = true)
    private List<SerLoanProductChannelUrl> channelUrls;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBalanceDesc() {
        return balanceDesc;
    }

    public void setBalanceDesc(String balanceDesc) {
        this.balanceDesc = balanceDesc;
    }

    public Long getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Long minBalance) {
        this.minBalance = minBalance;
    }

    public Long getMaxBalance() {
        return maxBalance;
    }

    public void setMaxBalance(Long maxBalance) {
        this.maxBalance = maxBalance;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTermDesc() {
        return termDesc;
    }

    public void setTermDesc(String termDesc) {
        this.termDesc = termDesc;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getSpeedDesc() {
        return speedDesc;
    }

    public void setSpeedDesc(String speedDesc) {
        this.speedDesc = speedDesc;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getProductProcess() {
        return productProcess;
    }

    public void setProductProcess(String productProcess) {
        this.productProcess = productProcess;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public List<SerLoanType> getLoanTypeList() {
        return loanTypeList;
    }

    public void setLoanTypeList(List<SerLoanType> loanTypeList) {
        this.loanTypeList = loanTypeList;
    }

    public List<SerLoanProductChannelUrl> getChannelUrls() {
        return channelUrls;
    }

    public void setChannelUrls(List<SerLoanProductChannelUrl> channelUrls) {
        this.channelUrls = channelUrls;
    }
}