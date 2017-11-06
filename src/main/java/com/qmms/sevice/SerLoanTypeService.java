package com.qmms.sevice;


import com.qmms.entity.SerLoanType;
import org.springframework.data.domain.Page;

public interface SerLoanTypeService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    Page<SerLoanType> getListWithCondition(int page, int pageSize, String title);

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
}
