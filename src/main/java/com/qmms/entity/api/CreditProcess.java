package com.qmms.entity.api;

import java.util.List;

public class CreditProcess extends CommonMain{
    private List<CreditProcessData> data;

    public List<CreditProcessData> getData() {
        return data;
    }

    public void setData(List<CreditProcessData> data) {
        this.data = data;
    }
}
