package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ser_channel")
public class SerChannel implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="`name`")
    private String name;
    @Column(name="`desc`")
    private String desc;
    private Integer addUserId;
    private Integer addTime;
    private Integer updateUserId;
    private Integer updateTime;

    @OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy = "serChannel",orphanRemoval=true)
    private List<SerChannelUmeng> channelUmengList;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<SerChannelUmeng> getChannelUmengList() {
        return channelUmengList;
    }

    public void setChannelUmengList(List<SerChannelUmeng> channelUmengList) {
        this.channelUmengList = channelUmengList;
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