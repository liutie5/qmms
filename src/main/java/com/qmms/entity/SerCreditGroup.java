package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ser_credit_group")
public class SerCreditGroup implements Serializable {
    @Id
    private String id;
    @Column(name="`name`")
    private String name;

    private Integer addUserId;
    private Integer addTime;
    private Integer updateUserId;
    private Integer updateTime;

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SerCreditGroupType", joinColumns = { @JoinColumn(name = "groupId") }, inverseJoinColumns ={@JoinColumn(name = "typeKey") })
    private List<SerCreditType> loanTypeList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SerCreditType> getCreditTypeList() {
        return loanTypeList;
    }

    public void setCreditTypeList(List<SerCreditType> loanTypeList) {
        this.loanTypeList = loanTypeList;
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
}