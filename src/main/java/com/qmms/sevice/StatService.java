package com.qmms.sevice;

import com.qmms.entity.StatLoanUvChannel;
import org.springframework.data.domain.Page;

public interface StatService {
    public String loanUvChannelStat(String beginDate,String endDate);

    public String clearLoanUv();

    Page<StatLoanUvChannel> getLoanUvStatListWithCondition(int page, int pageSize, String date);
}
