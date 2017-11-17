package com.qmms.dao;

import com.qmms.entity.SerChangeShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerChangeShowDao extends CrudRepository<SerChangeShow,Long>,JpaRepository<SerChangeShow,Long>,JpaSpecificationExecutor<SerChangeShow> {
}