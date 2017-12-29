package com.qmms.sevice;

import com.qmms.entity.api.*;

public interface ApiService {
    //loan
    public LoanMain loanMain(String domainName, String pkgName,String pkgKey,String source,String group,String hotType);

    public LoanPlist loanPlist(String domainName, String pkgName, String pkgKey, String source, String type, String balance, String term);

    public LoanPdetail loanPdetail(String domainName, String pkgName, String pkgKey, String source, String pid);

    public LoanTypes loanTypes(String domainName, String pkgName,String pkgKey,String source,String group);

    //credit
    public CreditMain creditMain(String domainName, String pkgName,String pkgKey,String source,String group,String hotType);

    public CreditList creditList(String domainName, String pkgName,String pkgKey,String source,String bankId,String type);

    public CreditDetail creditDetail(String domainName, String pkgName,String pkgKey,String source,String cardId);

    public CreditTypes creditTypes(String domainName, String pkgName,String pkgKey,String source,String group);

    public CreditBanks creditBanks(String domainName, String pkgName,String pkgKey,String source);

    public CreditProgress creditProgress(String domainName, String pkgName, String pkgKey, String source);

    public CreditPhone creditPhone(String domainName, String pkgName, String pkgKey, String source);

    public ChangeShow changeShow(String domainName,String pkgName,String pkgKey,String source);

    public RnUpdate rnUpdate(String domainName,String pkgName,String pkgKey,String source,String version);
}
