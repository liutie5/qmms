package com.qmms.dao;

import com.qmms.entity.SerChannelUmeng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SerChannelUmengDao extends CrudRepository<SerChannelUmeng,Long>,JpaRepository<SerChannelUmeng,Long>,JpaSpecificationExecutor<SerChannelUmeng> {

    @Query(value="delete from ser_channel_umeng where channel_id = ?1",nativeQuery = true)
    @Modifying
    public void deleteByChannelId(Long channelId);
}