package com.qmms.sevice;

import com.qmms.entity.StatCreditUvSumByBank;
import com.qmms.entity.StatCreditUvSumByBankDetail;
import com.qmms.entity.StatLoanUvSumByProduct;
import com.qmms.entity.StatLoanUvSumByProductDetail;
import org.springframework.data.domain.Page;

public interface StatService {
    public String loanUvChannelStat(String beginDate,String endDate);

    public String clearLoanUv();

    Page<StatLoanUvSumByProduct> getLoanUvStatByPidListWithCondition(int page, int pageSize, String beginDate,String endDate);

    Page<StatLoanUvSumByProductDetail> getLoanUvStatByPidDetailListWithCondition(int page, int pageSize, Long productId, String beginDate, String endDate);


    public String creditUvChannelStat(String beginDate,String endDate);

    public String clearCreditUv();

    Page<StatCreditUvSumByBank> getCreditUvStatByBankListWithCondition(int page, int pageSize, String beginDate, String endDate);

    Page<StatCreditUvSumByBankDetail> getCreditUvStatByBankDetailListWithCondition(int page, int pageSize, Long bankId, String beginDate, String endDate);
}
