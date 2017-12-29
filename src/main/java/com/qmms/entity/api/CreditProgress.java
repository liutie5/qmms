package com.qmms.entity.api;

import java.util.List;

public class CreditProgress extends CommonMain{
    private List<CreditProgressData> data;

    public List<CreditProgressData> getData() {
        return data;
    }

    public void setData(List<CreditProgressData> data) {
        this.data = data;
    }
}
