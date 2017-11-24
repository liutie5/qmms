package com.qmms.entity.api;

import java.util.List;

public class CreditList extends CommonMain{
    private List<CreditProduct> data;

    public List<CreditProduct> getData() {
        return data;
    }

    public void setData(List<CreditProduct> data) {
        this.data = data;
    }
}
