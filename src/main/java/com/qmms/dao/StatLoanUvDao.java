package com.qmms.dao;

import com.qmms.entity.StatLoanUv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StatLoanUvDao extends CrudRepository<StatLoanUv,Long>,JpaRepository<StatLoanUv,Long>,JpaSpecificationExecutor<StatLoanUv> {
    @Query(value = "DELETE from stat_loan_uv where from_unixtime(add_time,'%Y-%m-%d') <= ?1 ",nativeQuery = true)
    public void clearDateBeforeDate(String statDate);
}
