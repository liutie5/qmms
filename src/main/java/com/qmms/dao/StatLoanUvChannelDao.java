package com.qmms.dao;

import com.qmms.entity.StatLoanUvChannel;
import com.qmms.entity.StatLoanUvSumByProduct;
import com.qmms.entity.StatLoanUvSumByProductDetail;
import com.qmms.entity.StatUvKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface StatLoanUvChannelDao extends CrudRepository<StatLoanUvChannel,StatUvKey>,JpaRepository<StatLoanUvChannel,StatUvKey>,JpaSpecificationExecutor<StatLoanUvChannel> {
    @Query(value = "DELETE  from stat_loan_uv_channel where stat_date >= ?1 and stat_date <= ?2 ",nativeQuery = true)
    @Modifying
    public Integer deleteByStatDateBetween(String beginDate,String endDate);

    @Query(value =
            "insert into stat_loan_uv_channel(stat_date,product_id,channel_name,pv,uv,add_time)"+
            "(SELECT " +
            " from_unixtime(t1.add_time,'%Y-%m-%d'),pid, " +
            " t1.channel_name," +
            " count(*), " +
            " count(DISTINCT t1.device_id),UNIX_TIMESTAMP() "+
            " FROM " +
            " stat_loan_uv t1 " +
            " where from_unixtime(t1.add_time,'%Y-%m-%d') >= ?1 and from_unixtime(t1.add_time,'%Y-%m-%d') <= ?2 "+
            " GROUP BY " +
            " from_unixtime(t1.add_time,'%Y-%m-%d'),pid, " +
            " t1.channel_name )",nativeQuery = true)
    @Modifying
    public void insertLoanUvChannel(String beginDate,String endDate);

    @Query(value =
            "insert into stat_loan_uv_channel(stat_date,product_id,channel_name,pv,uv,add_time)"+
                    "(SELECT " +
                    " from_unixtime(t1.add_time,'%Y-%m-%d'),'-100', " +
                    " t1.channel_name," +
                    " count(*), " +
                    " count(DISTINCT t1.device_id),UNIX_TIMESTAMP() "+
                    " FROM " +
                    " stat_loan_uv t1 " +
                    " where from_unixtime(t1.add_time,'%Y-%m-%d') >= ?1 and from_unixtime(t1.add_time,'%Y-%m-%d') <= ?2 "+
                    " GROUP BY " +
                    " from_unixtime(t1.add_time,'%Y-%m-%d'),'-100', " +
                    " t1.channel_name )",nativeQuery = true)
    @Modifying
    public void insertLoanUvChannelSum(String beginDate,String endDate);

    @Query(value = "select pv from stat_loan_uv_channel where stat_date = ?1 limit 1",nativeQuery = true)
    public Long haveIdByAddDate(String statDate);


    @Query(value = "select new com.qmms.entity.StatLoanUvSumByProduct(t1.productId as productId,'未知',sum(t1.pv) as sumPv,sum(t1.uv) as sumUv)  from StatLoanUvChannel t1 where t1.statDate >= ?1  and t1.statDate <= ?2 group by t1.productId order by productId asc ",nativeQuery = false)
    public Page<StatLoanUvSumByProduct> selectByPid(Date beginDate,Date endDate,Pageable pageable);

    @Query(value = "select new com.qmms.entity.StatLoanUvSumByProductDetail(t1.channelName,sum(t1.pv) as sumPv,sum(t1.uv) as sumUv)  from StatLoanUvChannel t1 where t1.statDate >= ?1  and t1.statDate <= ?2 and t1.productId=?3 group by t1.channelName order by t1.channelName asc ",nativeQuery = false)
    public Page<StatLoanUvSumByProductDetail> selectByPidDetail(Date beginDate, Date endDate, Long pid, Pageable pageable);



}