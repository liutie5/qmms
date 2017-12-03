package com.qmms.dao;

import com.qmms.entity.SerLoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SerLoanTypeDao extends CrudRepository<SerLoanType,String>,JpaRepository<SerLoanType,String>,JpaSpecificationExecutor<SerLoanType> {
    @Query(value = "DELETE from ser_loan_group_type where type_key = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteLoanGroupType(String loanType);

    @Query(value = "DELETE from ser_loan_product_type where type_key = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteLoanProductType(String loanType);
}