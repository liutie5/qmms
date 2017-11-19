package com.qmms.entity.api;

import java.util.List;

public class LoanMainData{
    private List<LoanBanner> banner;
    private List<LoanType> types;
    private List<LoanProduct> hotps;
    private String tipcfg;

    public List<LoanBanner> getBanner() {
        return banner;
    }

    public void setBanner(List<LoanBanner> banner) {
        this.banner = banner;
    }

    public List<LoanType> getTypes() {
        return types;
    }

    public void setTypes(List<LoanType> types) {
        this.types = types;
    }

    public List<LoanProduct> getHotps() {
        return hotps;
    }

    public void setHotps(List<LoanProduct> hotps) {
        this.hotps = hotps;
    }

    public String getTipcfg() {
        return tipcfg;
    }

    public void setTipcfg(String tipcfg) {
        this.tipcfg = tipcfg;
    }
}