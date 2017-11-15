package com.qmms.dao;

import com.qmms.entity.SerCreditProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SerCreditProductDao extends CrudRepository<SerCreditProduct,Long>,JpaRepository<SerCreditProduct,Long>,JpaSpecificationExecutor<SerCreditProduct> {
    @Query(value="delete from ser_credit_product_channel_url where channel_id = ?1",nativeQuery = true)
    @Modifying
    public void deleteByChannelId(Long channelId);
}