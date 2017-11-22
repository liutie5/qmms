package com.qmms.sevice;

import com.qmms.entity.api.LoanMain;
import com.qmms.entity.api.LoanPdetail;
import com.qmms.entity.api.LoanPlist;
import com.qmms.entity.api.LoanTypes;

public interface ApiService {

    public LoanMain loanMain(String domainName, String pkgName,String pkgKey,String source,String group,String hotType);

    public LoanPlist loanPlist(String domainName, String pkgName, String pkgKey, String source, String type, String balance, String term);

    public LoanPdetail loanPdetail(String domainName, String pkgName, String pkgKey, String source, String pid);

    public LoanTypes loanTypes(String domainName, String pkgName,String pkgKey,String source,String group);
}
