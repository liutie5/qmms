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

    @OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy = "serChannel")
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
}