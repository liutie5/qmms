package com.qmms.dao;

import com.qmms.entity.SerLoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerLoanProductDao extends CrudRepository<SerLoanProduct,Long>,JpaRepository<SerLoanProduct,Long>,JpaSpecificationExecutor<SerLoanProduct> {
}