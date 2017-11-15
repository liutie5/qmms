package com.qmms.dao;

import com.qmms.entity.SerCreditBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerCreditBannerDao extends CrudRepository<SerCreditBanner,Long>,JpaRepository<SerCreditBanner,Long>,JpaSpecificationExecutor<SerCreditBanner> {
}