package com.qmms.entity.api;

import java.util.List;

public class LoanTypes extends CommonMain{
    private List<LoanType> data;

    public List<LoanType> getData() {
        return data;
    }
    public void setData(List<LoanType> data) {
        this.data = data;
    }
}
