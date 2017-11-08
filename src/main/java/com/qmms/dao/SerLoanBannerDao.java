package com.qmms.dao;

import com.qmms.entity.SerLoanBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerLoanBannerDao extends CrudRepository<SerLoanBanner,Long>,JpaRepository<SerLoanBanner,Long>,JpaSpecificationExecutor<SerLoanBanner> {
}