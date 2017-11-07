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
    private String balanceDesc;
    private Long minBalance;
    private Long maxBalance;
    private String rate;
    private String rateDesc;
    private Integer term;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "loanProduct")
    private List<SerLoanProductChannelUrl> channelUrls;




}