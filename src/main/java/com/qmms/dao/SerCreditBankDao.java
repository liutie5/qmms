package com.qmms.dao;

import com.qmms.entity.SerCreditBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerCreditBankDao extends CrudRepository<SerCreditBank,Long>,JpaRepository<SerCreditBank,Long>,JpaSpecificationExecutor<SerCreditBank> {
}