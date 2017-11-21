package com.qmms.sevice;

import com.qmms.entity.api.LoanMain;
import com.qmms.entity.api.LoanPlist;

public interface ApiService {

    public LoanMain loanMain(String domainName, String pkgName,String pkgKey,String source,String group,String hotType);

    public LoanPlist loanProduct(String domainName, String pkgName, String pkgKey, String source, String type, String balance, String term);
}
