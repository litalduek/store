package com.onlinestore.dto.user;

import java.util.Set;

public class SignupData {

    private String userName;
    private String email;
    private Set<String> role;
    private String password;
    private String firstName;
    private String lastName;
    private String paymentFullName;
    private String paymentExpDate;
    private String paymentCardNum;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPaymentFullName() {
        return paymentFullName;
    }

    public String getPaymentExpDate() {
        return paymentExpDate;
    }

    public String getPaymentCardNum() {
        return paymentCardNum;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRole() {
        return this.role;
    }

}