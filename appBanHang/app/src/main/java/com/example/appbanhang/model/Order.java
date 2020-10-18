package com.example.appbanhang.model;

import java.io.Serializable;

public class Order implements Serializable {
    public int  orderId;

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public String Status;
    public String Date;
    public String Amount;
    public String Ordermail;

    public Order(int orderId, String status, String date, String amount, String ordermail) {
        this.orderId = orderId;
        Status = status;
        Date = date;
        Amount = amount;
        Ordermail = ordermail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getOrdermail() {
        return Ordermail;
    }

    public void setOrdermail(String ordermail) {
        Ordermail = ordermail;
    }
}
