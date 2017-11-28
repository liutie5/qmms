package com.qmms.sevice;

import com.qmms.entity.StatLoanUvChannel;
import com.qmms.entity.StatLoanUvSumByProduct;
import org.springframework.data.domain.Page;

public interface StatService {
    public String loanUvChannelStat(String beginDate,String endDate);

    public String clearLoanUv();

    Page<StatLoanUvSumByProduct> getLoanUvStatByPidListWithCondition(int page, int pageSize, String beginDate,String endDate);

    Page<StatLoanUvChannel> getLoanUvStatByPidDetailListWithCondition(int page, int pageSize, Long productId, String beginDate,String endDate);
}
