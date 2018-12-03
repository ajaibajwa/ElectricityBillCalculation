package com.example.ajay_mac.electricitybillcalculation;

import java.io.Serializable;

public class ElectricityBill implements Serializable{
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String gender;
    private String billDate;
    private Double unitConsumed;
    private Double totalBillAmount;

    public ElectricityBill(){

    }
    public ElectricityBill(String customerId, String customerName, String customerEmail, String gender, String billDate, Double unitConsumed, Double totalBillAmount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.gender = gender;
        this.billDate = billDate;
        this.unitConsumed = unitConsumed;
        this.totalBillAmount = totalBillAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public Double getUnitConsumed() {
        return unitConsumed;
    }

    public void setUnitConsumed(Double unitConsumed) {
        this.unitConsumed = unitConsumed;
    }

    public Double getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(Double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }
}
