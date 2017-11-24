package com.qmms.entity.api;

import java.util.List;

public class CreditTypes extends CommonMain{
    private List<CreditType> data;

    public List<CreditType> getData() {
        return data;
    }

    public void setData(List<CreditType> data) {
        this.data = data;
    }
}
