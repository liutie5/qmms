package com.qmms.entity.api;

import java.util.List;

public class LoanPlist extends CommonMain{
    private List<LoanProduct> data;

    public List<LoanProduct> getData() {
        return data;
    }

    public void setData(List<LoanProduct> data) {
        this.data = data;
    }
}
