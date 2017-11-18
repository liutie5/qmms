package com.qmms.sevice;

import com.qmms.entity.SerChangeShow;
import com.qmms.entity.SerRnUpdate;
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


    //升级配置
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param desc
     * @return
     */
    Page<SerRnUpdate> getRnUpdateListWithCondition(int page, int pageSize, String desc);

    /**
     *
     * @param rnUpdate
     * @return
     */
    public SerRnUpdate addRnUpdate(SerRnUpdate rnUpdate);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerRnUpdate getRnUpdate(Long id);

    /**
     * 删除
     * @param id
     */
    public void delRnUpdate(Long id);

    /**
     * 编辑
     * @param serRnUpdate
     * @return
     */
    public SerRnUpdate editRnUpdate(SerRnUpdate serRnUpdate) throws Exception;
}
