package com.qmms.sevice;

import com.qmms.entity.SerChangeShow;
import org.springframework.data.domain.Page;

public interface SerConfigService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param desc
     * @return
     */
    Page<SerChangeShow> getChangeShowListWithCondition(int page, int pageSize, String desc);

    /**
     *
     * @param changeShow
     * @return
     */
    public SerChangeShow addChangeShow(SerChangeShow changeShow);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerChangeShow getChangeShow(Long id);

    /**
     * 删除
     * @param id
     */
    public void delChangeShow(Long id);

    /**
     * 编辑
     * @param changeShow
     * @return
     */
    public SerChangeShow editChangeShow(SerChangeShow changeShow) throws Exception;
}
