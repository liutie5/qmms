package com.qmms.sevice;


import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import org.springframework.data.domain.Page;

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
    public SerLoanType editLoanType(SerLoanType loanType);

    //贷款产品

    /**
     * 分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    public Page<SerLoanProduct> getLoanProductList(int page, int pageSize, String name);

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

    //贷款广告

    //其他设置
}
