package com.qmms.dao;

import com.qmms.entity.StatLoanUv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StatLoanUvDao extends CrudRepository<StatLoanUv,Long>,JpaRepository<StatLoanUv,Long>,JpaSpecificationExecutor<StatLoanUv> {
    @Query(value = "DELETE from stat_loan_uv where from_unixtime(add_time,'%Y-%m-%d') <= ?1 ",nativeQuery = true)
    @Modifying
    public void clearDateBeforeDate(String statDate);

    @Query(value = "SELECT t2.name FROM ser_loan_product_channel_url t1 LEFT JOIN ser_channel t2 ON t1.channel_id = t2.id LEFT  JOIN ser_channel_umeng t3 ON t1.channel_id = t3.channel_id\n" +
            "WHERE t1.product_id = ?1 AND t3.umeng_key =?2 AND (t3.market_id =?3 or t3.market_id ='') limit 1",nativeQuery = true)
    public String findChannelName(Long productId,String pkgKey,String source);
}
