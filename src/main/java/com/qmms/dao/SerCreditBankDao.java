package com.qmms.dao;

import com.qmms.entity.SerCreditBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SerCreditBankDao extends CrudRepository<SerCreditBank,Long>,JpaRepository<SerCreditBank,Long>,JpaSpecificationExecutor<SerCreditBank> {
    public List<SerCreditBank> findAllByIsJoinOrderByPriorityDesc(int isJoin);
    public List<SerCreditBank> findAllByOrderByPriorityDesc();
    @Query(value="select * from ser_credit_bank where process_url is not null and process_url !='' order by priority desc",nativeQuery = true)
    public List<SerCreditBank> findAllUseProcessUrl();

    @Query(value="select * from ser_credit_bank where bank_phone is not null and bank_phone !='' order by priority desc",nativeQuery = true)
    public List<SerCreditBank> findAllUseBankPhone();
}