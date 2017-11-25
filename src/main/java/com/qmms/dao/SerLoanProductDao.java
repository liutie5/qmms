package com.qmms.dao;

import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface SerLoanProductDao extends CrudRepository<SerLoanProduct,Long>,JpaRepository<SerLoanProduct,Long>,JpaSpecificationExecutor<SerLoanProduct> {
    @Query(value="delete from ser_loan_product_channel_url where channel_id = ?1",nativeQuery = true)
    @Modifying
    public void deleteByChannelId(Long channelId);

    public List<SerLoanProduct> findAllByStatusOrderByOrderedByDesc(int status);

    public List<SerLoanProduct> findAllByLoanTypeListInAndStatusOrderByOrderedByDesc(Set<SerLoanType> loanTypeSet,int status);
}