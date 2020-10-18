package com.example.appbanhang.model;

import java.io.Serializable;

public class Order_detail implements Serializable {
String detail_ten;
String detail_gia;
String detail_soluong;
String detail_hinhanh;

    public Order_detail(String detail_ten, String detail_gia, String detail_soluong, String detail_hinhanh) {
        this.detail_ten = detail_ten;
        this.detail_gia = detail_gia;
        this.detail_soluong = detail_soluong;
        this.detail_hinhanh = detail_hinhanh;
    }

    public String getDetail_ten() {
        return detail_ten;
    }

    public void setDetail_ten(String detail_ten) {
        this.detail_ten = detail_ten;
    }

    public String getDetail_gia() {
        return detail_gia;
    }

    public void setDetail_gia(String detail_gia) {
        this.detail_gia = detail_gia;
    }

    public String getDetail_soluong() {
        return detail_soluong;
    }

    public void setDetail_soluong(String detail_soluong) {
        this.detail_soluong = detail_soluong;
    }

    public String getDetail_hinhanh() {
        return detail_hinhanh;
    }

    public void setDetail_hinhanh(String detail_hinhanh) {
        this.detail_hinhanh = detail_hinhanh;
    }
}
