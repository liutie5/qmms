package com.qmms.entity.api;

import java.util.List;

public class CreditMainData {
    private List<CreditBanner> banner;
    private List<CreditType> types;
    private List<CreditBank> banks;
    private List<CreditProduct> hotps;

    public List<CreditBanner> getBanner() {
        return banner;
    }

    public void setBanner(List<CreditBanner> banner) {
        this.banner = banner;
    }

    public List<CreditType> getTypes() {
        return types;
    }

    public void setTypes(List<CreditType> types) {
        this.types = types;
    }

    public List<CreditBank> getBanks() {
        return banks;
    }

    public void setBanks(List<CreditBank> banks) {
        this.banks = banks;
    }

    public List<CreditProduct> getHotps() {
        return hotps;
    }

    public void setHotps(List<CreditProduct> hotps) {
        this.hotps = hotps;
    }
}
