package com.campuslive.campusliveserver.entity;

public class Order {
    private int orderID;
    private int orderMoney;
    private String orderTime;
    private int orderState;
    private int clientID;
    private int serverID;
    private String orderContent;
    private String orderAddress;

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(int orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderMoney=" + orderMoney +
                ", orderTime='" + orderTime + '\'' +
                ", orderState=" + orderState +
                ", clientID=" + clientID +
                ", serverID=" + serverID +
                ", orderContent='" + orderContent + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                '}';
    }
}
