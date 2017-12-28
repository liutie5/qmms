package com.qmms.entity.api;

import java.util.List;

public class CreditPhone extends CommonMain{
    private List<CreditPhoneData> data;

    public List<CreditPhoneData> getData() {
        return data;
    }

    public void setData(List<CreditPhoneData> data) {
        this.data = data;
    }
}
