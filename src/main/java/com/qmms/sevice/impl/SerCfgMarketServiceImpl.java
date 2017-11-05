package com.qmms.sevice.impl;

import com.qmms.dao.SerCfgMarketDao;
import com.qmms.entity.SerCfgMarket;
import com.qmms.sevice.SerCfgMarketService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SerCfgMarketServiceImpl implements SerCfgMarketService {
    @Resource
    private SerCfgMarketDao serCfgMarketDao;
    @Override
    public List<SerCfgMarket> getMarketList() {
        try{
            List<SerCfgMarket> rs = serCfgMarketDao.findAll(new Sort(Sort.Direction.ASC,"orderKey"));
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
