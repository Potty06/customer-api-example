package com.smartacademy.mvcpattern2.model.customermodel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer customerId;

    private String customerName;

    private LocalDate dateOfBirth;

    private String address;

    private String email;

    private String phoneNo;

//    public Customer(Integer customerId, String customerName, LocalDate dateOfBirth, String address, String email, String phoneNo) {
//        this.customerId = customerId;
//        this.customerName = customerName;
//        this.dateOfBirth = dateOfBirth;
//        this.address = address;
//        this.email = email;
//        this.phoneNo = phoneNo;
//    }
//
//    public Customer() {
//    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
