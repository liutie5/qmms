package com.qmms.sevice;

import com.qmms.entity.SerCreditBank;
import com.qmms.entity.SerCreditBanner;
import com.qmms.entity.SerCreditProduct;
import com.qmms.entity.SerCreditType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SerCreditService {
    //信用卡银行
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param bankName
     * @return
     */
    Page<SerCreditBank> getCreditBankListWithCondition(int page, int pageSize, String bankName);

    /**
     *
     * @param creditBank
     * @return
     */
    public SerCreditBank addCreditBank(SerCreditBank creditBank);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerCreditBank getCreditBank(Long id);

    /**
     * 删除
     * @param id
     */
    public void delCreditBank(Long id);

    /**
     * 编辑
     * @param creditBank
     * @return
     */
    public SerCreditBank editCreditBank(SerCreditBank creditBank) throws Exception;

    public List<SerCreditBank> getAllCreditBank();

    //信用卡类型
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    Page<SerCreditType> getCreditTypeListWithCondition(int page, int pageSize, String title);

    /**
     *
     * @param creditType
     * @return
     */
    public SerCreditType addCreditType(SerCreditType creditType);

    /**
     * 获取
     * @param key
     * @return
     */
    public SerCreditType getCreditType(String key);

    /**
     * 删除
     * @param key
     */
    public void delCreditType(String key);

    /**
     * 编辑
     * @param creditType
     * @return
     */
    public SerCreditType editCreditType(SerCreditType creditType) throws Exception;

    /**
     * 获取所有贷款类型
     * @return
     */
    List<SerCreditType> getAllCreditTypes();
    
    //信用卡广告
    /**
     * 分页
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    public Page<SerCreditBanner> getCreditBannerList(int page, int pageSize, String title);

    /**
     * 获取
     * @param id
     * @return
     */
    public SerCreditBanner getCreditBanner(Long id);

    /**
     * 添加
     * @param banner
     * @return
     */
    public SerCreditBanner addCreditBanner(SerCreditBanner banner);

    /**
     * 编辑
     * @param banner
     * @return
     */
    public SerCreditBanner editCreditBanner(SerCreditBanner banner) throws Exception;

    /**
     * 删除
     * @param id
     */
    public void delCreditBanner(Long id);

    //信用卡产品
    /**
     * 分页
     * @param page
     * @param pageSize
     * @param cardName
     * @return
     */
    public Page<SerCreditProduct> getCreditProductList(int page, int pageSize, String cardName);

    /**
     * 获取
     * @param cartId
     * @return
     */
    public SerCreditProduct getCreditProduct(Long cartId);

    /**
     * 添加
     * @param product
     * @return
     */
    public SerCreditProduct addCreditProduct(SerCreditProduct product,String[] creditTypes,String[] channelUrls);

    /**
     * 编辑
     * @param product
     * @return
     */
    public SerCreditProduct editCreditProduct(SerCreditProduct product,String[] creditTypes,String[] channelUrls) throws Exception;

    /**
     * 删除
     * @param cardId
     */
    public void delCreditProduct(Long cardId);

    /**
     * 获取所有贷款产品
     * @return
     */
    public List<SerCreditProduct> getAllCreditProducts();

}
