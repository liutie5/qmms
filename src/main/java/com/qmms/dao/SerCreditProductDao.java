package com.qmms.dao;

import com.qmms.entity.SerCreditProduct;
import com.qmms.entity.SerCreditType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface SerCreditProductDao extends CrudRepository<SerCreditProduct,Long>,JpaRepository<SerCreditProduct,Long>,JpaSpecificationExecutor<SerCreditProduct> {
    @Query(value="delete from ser_credit_product_channel_url where channel_id = ?1",nativeQuery = true)
    @Modifying
    public void deleteByChannelId(Long channelId);

    public List<SerCreditProduct> findAllByStatus(int status);

    public List<SerCreditProduct> findAllByCreditTypeListInAndStatus(Set<SerCreditType> creditTypeSet, int status);


}