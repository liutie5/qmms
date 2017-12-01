package com.qmms.dao;

import com.qmms.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface StatCreditUvChannelDao extends CrudRepository<StatCreditUvChannel,StatCreditUvKey>,JpaRepository<StatCreditUvChannel,StatCreditUvKey>,JpaSpecificationExecutor<StatCreditUvChannel> {
    @Query(value = "DELETE  from stat_credit_uv_channel where stat_date >= ?1 and stat_date <= ?2 ",nativeQuery = true)
    @Modifying
    public Integer deleteByStatDateBetween(String beginDate, String endDate);

    @Query(value =
            "insert into stat_credit_uv_channel(stat_date,bank_id,channel_name,pv,uv,add_time)"+
            "(SELECT " +
            " from_unixtime(t1.add_time,'%Y-%m-%d'),bank_id, " +
            " t1.channel_name," +
            " count(*), " +
            " count(DISTINCT t1.device_id),UNIX_TIMESTAMP() "+
            " FROM " +
            " stat_credit_uv t1 " +
            " where from_unixtime(t1.add_time,'%Y-%m-%d') >= ?1 and from_unixtime(t1.add_time,'%Y-%m-%d') <= ?2 "+
            " GROUP BY " +
            " from_unixtime(t1.add_time,'%Y-%m-%d'),bank_id, " +
            " t1.channel_name )",nativeQuery = true)
    @Modifying
    public void insertCreditUvChannel(String beginDate, String endDate);

    @Query(value =
            "insert into stat_credit_uv_channel(stat_date,bank_id,channel_name,pv,uv,add_time)"+
                    "(SELECT " +
                    " from_unixtime(t1.add_time,'%Y-%m-%d'),'-100', " +
                    " t1.channel_name," +
                    " count(*), " +
                    " count(DISTINCT t1.device_id),UNIX_TIMESTAMP() "+
                    " FROM " +
                    " stat_credit_uv t1 " +
                    " where from_unixtime(t1.add_time,'%Y-%m-%d') >= ?1 and from_unixtime(t1.add_time,'%Y-%m-%d') <= ?2 "+
                    " GROUP BY " +
                    " from_unixtime(t1.add_time,'%Y-%m-%d'),'-100', " +
                    " t1.channel_name )",nativeQuery = true)
    @Modifying
    public void insertCreditUvChannelSum(String beginDate, String endDate);

    @Query(value = "select pv from stat_credit_uv_channel where stat_date = ?1 limit 1",nativeQuery = true)
    public Long haveIdByAddDate(String statDate);


    @Query(value = "select new com.qmms.entity.StatCreditUvSumByBank(t1.bankId,'未知',sum(t1.pv) as sumPv,sum(t1.uv) as sumUv)  from StatCreditUvChannel t1 where t1.statDate >= ?1  and t1.statDate <= ?2 group by t1.bankId order by bankId asc ",nativeQuery = false)
    public Page<StatCreditUvSumByBank> selectByBankId(Date beginDate, Date endDate, Pageable pageable);

    @Query(value = "select new com.qmms.entity.StatCreditUvSumByBankDetail(t1.channelName,sum(t1.pv) as sumPv,sum(t1.uv) as sumUv)  from StatCreditUvChannel t1 where t1.statDate >= ?1  and t1.statDate <= ?2 and t1.bankId=?3 group by t1.channelName order by t1.channelName asc ",nativeQuery = false)
    public Page<StatCreditUvSumByBankDetail> selectByBankDetail(Date beginDate, Date endDate, Long bankId, Pageable pageable);



}