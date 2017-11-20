package com.qmms.dao;

import com.qmms.entity.StatLoanUv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface StatLoanUvDao extends CrudRepository<StatLoanUv,Long>,JpaRepository<StatLoanUv,Long>,JpaSpecificationExecutor<StatLoanUv> {
}
