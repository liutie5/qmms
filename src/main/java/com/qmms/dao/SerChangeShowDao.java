package com.qmms.dao;

import com.qmms.entity.SerChangeShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SerChangeShowDao extends CrudRepository<SerChangeShow,Long>,JpaRepository<SerChangeShow,Long>,JpaSpecificationExecutor<SerChangeShow> {
    @Query(value="select * from ser_change_show where umeng = ?1 order by id desc limit 1",nativeQuery = true)
    SerChangeShow findOneByUmeng(String umeng);
}