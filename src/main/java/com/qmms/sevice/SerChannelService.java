package com.qmms.sevice;


import com.qmms.entity.SerChannel;
import com.qmms.entity.SysUserInfo;
import org.springframework.data.domain.Page;

public interface SerChannelService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param channelName
     * @return
     */
    Page<SerChannel> getChannelListWithCondition(int page, int pageSize, String channelName);

    /**
     *
     * @param channel
     * @param umengmarkets
     * @return
     */
    public SerChannel addChannel(SerChannel channel,String[] umengmarkets);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerChannel getChannelById(Long id);

    /**
     * 删除
     * @param id
     */
    public void delChannel(Long id);

    /**
     * 编辑
     * @param channel
     * @param umengmarkets
     * @return
     */
    public SerChannel editChannel(SerChannel channel,String[] umengmarkets);
}
