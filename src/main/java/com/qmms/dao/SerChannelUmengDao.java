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

    public SerChannelUmeng findFirstByUmengKeyAndMarketId(String umengKey,String marketId);

    @Query(value="select * from ser_channel_umeng where umeng_key=?1 and market_id=?2 and channel_id != ?3 limit 1",nativeQuery = true)
    public SerChannelUmeng findFirstByUmengKeyAndMarketIdAndChannelIdNot(String umengKey,String marketId,Long channelId);
}