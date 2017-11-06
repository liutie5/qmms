package com.qmms.dao;

import com.qmms.entity.SerLoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerLoanTypeDao extends CrudRepository<SerLoanType,String>,JpaRepository<SerLoanType,String>,JpaSpecificationExecutor<SerLoanType> {
}