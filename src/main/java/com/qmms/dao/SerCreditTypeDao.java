package com.qmms.dao;

import com.qmms.entity.SerCreditType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SerCreditTypeDao extends CrudRepository<SerCreditType,String>,JpaRepository<SerCreditType,String>,JpaSpecificationExecutor<SerCreditType> {
    @Query(value = "DELETE from ser_credit_group_type where type_key = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteCreditGroupType(String creditType);

    @Query(value = "DELETE from ser_credit_product_type where type_key = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteCreditProductType(String creditType);
}