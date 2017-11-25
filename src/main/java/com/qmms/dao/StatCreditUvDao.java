package com.qmms.dao;

import com.qmms.entity.StatCreditUv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface StatCreditUvDao extends CrudRepository<StatCreditUv,Long>,JpaRepository<StatCreditUv,Long>,JpaSpecificationExecutor<StatCreditUv> {
}
