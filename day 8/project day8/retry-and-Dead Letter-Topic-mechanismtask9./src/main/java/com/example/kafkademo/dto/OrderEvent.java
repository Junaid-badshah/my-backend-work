package com.example.kafkademo.dto;

public class OrderEvent {
    private String orderId;
    private String item;
    private double price;

    public OrderEvent() {}

    public OrderEvent(String orderId, String item, double price) {
        this.orderId = orderId;
        this.item = item;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}