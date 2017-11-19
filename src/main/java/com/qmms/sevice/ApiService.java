package com.qmms.sevice;

import com.qmms.entity.api.LoanMain;

public interface ApiService {

    public LoanMain loanMain(String domainName, String pkgName,String pkgKey,String source,String group,String hotType);
}
