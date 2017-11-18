package com.qmms.sevice.impl;

import com.qmms.dao.SerChannelDao;
import com.qmms.dao.SerChannelUmengDao;
import com.qmms.dao.SerLoanProductDao;
import com.qmms.entity.SerChannel;
import com.qmms.entity.SerChannelUmeng;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SerChannelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SerChannelServiceImpl implements SerChannelService {
    @Resource
    private SerChannelDao serChannelDao;
    @Resource
    private SerChannelUmengDao serChannelUmengDao;
    @Resource
    private SerLoanProductDao serLoanProductDao;
    /**
     * 分页查找
     * @param page
     * @param pageSize
     * @param channelName
     * @return
     */
    @Override
    public Page<SerChannel> getChannelListWithCondition(int page, int pageSize, final String channelName) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerChannel> channelPage = serChannelDao.findAll(new Specification<SerChannel>(){
            @Override
            public Predicate toPredicate(Root<SerChannel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(channelName)){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+channelName+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return channelPage;
    }

    /**
     *
     * @param channel
     * @param umengmarkets
     * @return
     */
    @Transactional
    @Override
    public SerChannel addChannel(SerChannel channel, String[] umengmarkets) {
        if(umengmarkets != null){
            List<SerChannelUmeng> dataList = new ArrayList<>();
            for(String umengmarket:umengmarkets) {
                SerChannelUmeng umeng = new SerChannelUmeng();
                String[] arr = umengmarket.split("_");
                String umengKey = arr[0];
                String marketId = "";
                if (arr.length >= 2) {
                    marketId = arr[1];
                }
                umeng.setSerChannel(channel);
                umeng.setUmengKey(umengKey);
                umeng.setMarketId(marketId);
                dataList.add(umeng);
            }
            channel.setChannelUmengList(dataList);
        }
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        channel.setAddTime(currentTime);
        channel.setAddUserId(currentUser.getUserId());
        channel.setUpdateUserId(currentUser.getUserId());
        channel.setUpdateTime(currentTime);
        channel = serChannelDao.save(channel);
        return channel;
    }

    /**
     * 删除
     * @param id
     */
    @Transactional
    public void delChannel(Long id){
        serLoanProductDao.deleteByChannelId(id);
        serChannelDao.delete(id);
    }

    /**
     * 获取
     * @param id
     * @return
     */
    @Override
    public SerChannel getChannelById(Long id) {
        return serChannelDao.findOne(id);
    }

    /**
     * 编辑
     * @param channel
     * @param umengmarkets
     * @return
     */
    @Transactional
    @Override
    public SerChannel editChannel(SerChannel channel, String[] umengmarkets) {
        SerChannel sc = serChannelDao.findOne(channel.getId());
        if(channel.getName() != null){
            sc.setName(channel.getName());
        }
        if(channel.getDesc() != null){
            sc.setDesc(channel.getDesc());
        }
        sc.getChannelUmengList().clear();
        if(umengmarkets != null){
            for(String umengmarket:umengmarkets) {
                SerChannelUmeng umeng = new SerChannelUmeng();
                String[] arr = umengmarket.split("_");
                String umengKey = arr[0];
                String marketId = "";
                if (arr.length >= 2) {
                    marketId = arr[1];
                }
                umeng.setUmengKey(umengKey);
                umeng.setMarketId(marketId);
                umeng.setSerChannel(sc);
                sc.getChannelUmengList().add(umeng);
            }
        }
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        sc.setUpdateUserId(currentUser.getUserId());
        sc.setUpdateTime(currentTime);
        serChannelDao.save(sc);
        return sc;

    }

    public boolean contains(SerChannelUmeng scu,List<SerChannelUmeng> list){
        if(list == null) return false;
        for(SerChannelUmeng d:list){
            if(scu.getUmengKey().equals(d.getUmengKey()) && scu.getMarketId().equals(d.getMarketId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SerChannel> getAllChannel() {
        return serChannelDao.findAll();
    }
}
