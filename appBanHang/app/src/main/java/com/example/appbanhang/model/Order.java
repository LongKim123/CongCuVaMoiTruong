package com.example.appbanhang.model;

public class Order {
    int OrderId;
    String orderStatus,orderDate,orderAmount;

    public Order(int orderId, String orderStatus, String orderDate, String orderAmount) {
        OrderId = orderId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderAmount = orderAmount;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}
