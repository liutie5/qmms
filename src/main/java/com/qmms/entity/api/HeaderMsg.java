package com.qmms.entity.api;

/**
 * Created by liutie on 17-11-22.
 */
public class HeaderMsg {
    private String pkgName;
    private String pkgKey;
    private String source;

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getPkgKey() {
        return pkgKey;
    }

    public void setPkgKey(String pkgKey) {
        this.pkgKey = pkgKey;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
