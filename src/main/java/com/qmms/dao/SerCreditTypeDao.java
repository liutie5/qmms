package com.qmms.dao;

import com.qmms.entity.SerCreditType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerCreditTypeDao extends CrudRepository<SerCreditType,String>,JpaRepository<SerCreditType,String>,JpaSpecificationExecutor<SerCreditType> {
}