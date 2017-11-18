package com.qmms.dao;

import com.qmms.entity.SerRnUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerRnUpdateDao extends CrudRepository<SerRnUpdate,Long>,JpaRepository<SerRnUpdate,Long>,JpaSpecificationExecutor<SerRnUpdate> {
}