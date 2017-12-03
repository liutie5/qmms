package com.qmms.entity.api;

import java.util.List;

public class CreditBanks extends CommonMain{
    private List<CreditBank> data;

    public List<CreditBank> getData() {
        return data;
    }

    public void setData(List<CreditBank> data) {
        this.data = data;
    }
}
