package com.qmms.dao;

import com.qmms.entity.SerRnUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SerRnUpdateDao extends CrudRepository<SerRnUpdate,Long>,JpaRepository<SerRnUpdate,Long>,JpaSpecificationExecutor<SerRnUpdate> {

    @Query(value="select * from ser_rn_update where umeng = ?1 and market_id = ?2 and version > ?3 order by id desc limit 1",nativeQuery = true)
    SerRnUpdate findOneByUmengAndMarketIdAndVerson(String umeng,String marketId, double version);
}