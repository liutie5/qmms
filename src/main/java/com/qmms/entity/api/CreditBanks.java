package com.qmms.entity.api;

import java.util.List;

public class CreditBanks extends CommonMain{
    private List<CreditBanksData> data;

    public List<CreditBanksData> getData() {
        return data;
    }

    public void setData(List<CreditBanksData> data) {
        this.data = data;
    }
}
