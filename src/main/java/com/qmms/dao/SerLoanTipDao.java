package com.qmms.dao;

import com.qmms.entity.SerLoanTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerLoanTipDao extends CrudRepository<SerLoanTip,Long>,JpaRepository<SerLoanTip,Long>,JpaSpecificationExecutor<SerLoanTip> {
}