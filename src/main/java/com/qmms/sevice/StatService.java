package com.qmms.sevice;

import com.qmms.entity.StatLoanUvSumByProduct;
import com.qmms.entity.StatLoanUvSumByProductDetail;
import org.springframework.data.domain.Page;

public interface StatService {
    public String loanUvChannelStat(String beginDate,String endDate);

    public String clearLoanUv();

    Page<StatLoanUvSumByProduct> getLoanUvStatByPidListWithCondition(int page, int pageSize, String beginDate,String endDate);

    Page<StatLoanUvSumByProductDetail> getLoanUvStatByPidDetailListWithCondition(int page, int pageSize, Long productId, String beginDate, String endDate);
}
