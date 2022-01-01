package com.onlinestore.dto.store;

public class PaymentData {

    private String name;
    private String expDate;
    private String number;

    public PaymentData() {
    }

    public PaymentData(String name, String expDate, String number) {
        this.name = name;
        this.expDate = expDate;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
