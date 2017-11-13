package com.qmms.dao;

import com.qmms.entity.SerLoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SerLoanProductDao extends CrudRepository<SerLoanProduct,Long>,JpaRepository<SerLoanProduct,Long>,JpaSpecificationExecutor<SerLoanProduct> {
    @Query(value="delete from ser_loan_product_channel_url where channel_id = ?1",nativeQuery = true)
    @Modifying
    public void deleteByChannelId(Long channelId);
}