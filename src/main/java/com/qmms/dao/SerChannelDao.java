package com.qmms.dao;

import com.qmms.entity.SerChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SerChannelDao extends CrudRepository<SerChannel,Long>,JpaRepository<SerChannel,Long>,JpaSpecificationExecutor<SerChannel> {
}