package com.qmms.sevice;


import com.qmms.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SerLoanService {
    //贷款类型
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    Page<SerLoanType> getLoanTypeListWithCondition(int page, int pageSize, String title);

    /**
     *
     * @param loanType
     * @return
     */
    public SerLoanType addLoanType(SerLoanType loanType);

    /**
     * 获取
     * @param key
     * @return
     */
    public SerLoanType getLoanType(String key);

    /**
     * 删除
     * @param key
     */
    public void delLoanType(String key);

    /**
     * 编辑
     * @param loanType
     * @return
     */
    public SerLoanType editLoanType(SerLoanType loanType) throws Exception;

    /**
     * 获取所有贷款类型
     * @return
     */
    List<SerLoanType> getAllLoanTypes();

    //贷款产品

    /**
     * 分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    public Page<SerLoanProduct> getLoanProductList(int page, int pageSize, String name,int status);

    /**
     * 获取
     * @param productId
     * @return
     */
    public SerLoanProduct getLoanProduct(Long productId);

    /**
     * 添加
     * @param product
     * @return
     */
    public SerLoanProduct addLoanProduct(SerLoanProduct product,String[] loanTypes,String[] channelUrls);

    /**
     * 编辑
     * @param product
     * @return
     */
    public SerLoanProduct editLoanProduct(SerLoanProduct product,String[] loanTypes,String[] channelUrls) throws Exception;

    /**
     * 删除
     * @param productId
     */
    public void delLoanProduct(Long productId);

    /**
     * 获取所有贷款产品
     * @return
     */
    public List<SerLoanProduct> getAllLoanProducts();

    //贷款广告
    /**
     * 分页
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    public Page<SerLoanBanner> getLoanBannerList(int page, int pageSize, String title);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerLoanBanner getLoanBanner(Long id);

    /**
     * 添加
     * @param banner
     * @return
     */
    public SerLoanBanner addLoanBanner(SerLoanBanner banner);

    /**
     * 编辑
     * @param banner
     * @return
     */
    public SerLoanBanner editLoanBanner(SerLoanBanner banner) throws Exception;

    /**
     * 删除
     * @param id
     */
    public void delLoanBanner(Long id);

    //运营消息
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param context
     * @return
     */
    Page<SerLoanTip> getLoanTipListWithCondition(int page, int pageSize, String context);

    /**
     *
     * @param loanTip
     * @return
     */
    public SerLoanTip addLoanTip(SerLoanTip loanTip);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerLoanTip getLoanTip(Long id);

    /**
     * 删除
     * @param id
     */
    public void delLoanTip(Long id);

    /**
     * 编辑
     * @param loanTip
     * @return
     */
    public SerLoanTip editLoanTip(SerLoanTip loanTip) throws Exception;

    //分类组
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param id
     * @return
     */
    Page<SerLoanGroup> getLoanGroupListWithCondition(int page, int pageSize, String id);

    /**
     *
     * @param loanGroup
     * @return
     */
    public SerLoanGroup addLoanGroup(SerLoanGroup loanGroup,String[] loanTypes);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerLoanGroup getLoanGroup(String id);

    /**
     * 删除
     * @param id
     */
    public void delLoanGroup(String id);

    /**
     * 编辑
     * @param loanGroup
     * @return
     */
    public SerLoanGroup editLoanGroup(SerLoanGroup loanGroup,String[] loanTypes) throws Exception;


}
