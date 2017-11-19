package com.qmms.dao;

import com.qmms.entity.SerLoanTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SerLoanTipDao extends CrudRepository<SerLoanTip,Long>,JpaRepository<SerLoanTip,Long>,JpaSpecificationExecutor<SerLoanTip> {
    @Query(value="select * from ser_loan_tip where rm_package not like concat('%',?1,'%') and display=1 order by id desc limit 1",nativeQuery = true)
    SerLoanTip findOneByRmPackageNotLike(String rmPackage);

    @Query(value="select * from ser_loan_tip where display=1  order by id desc limit 1",nativeQuery = true)
    SerLoanTip findLastOne();
}