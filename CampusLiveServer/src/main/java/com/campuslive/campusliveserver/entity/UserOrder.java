package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description UserOrder实体
 */

public class UserOrder {
    //定义订单状态
    //state=-1 -> 订单取消, state=1 -> 订单已下单,未付款, state=2 -> 订单已付款，未接单, state=3 -> 订单已接单,未完成, state=4 -> 订单已完成,未收货
    //state=5 -> 订单已收货,未评价, state=6 -> 订单已评分,无问题, state=7 -> 订单需售后或投诉
    static public int ORDER_CANCELED = -1;
    static public int ORDER_DEFAULT = 1;
    static public int ORDER_PAID = 2;
    static public int ORDER_RECEIVED = 3;
    static public int ORDER_FINISHED = 4;
    static public int ORDER_GET = 5;
    static public int ORDER_MARKED = 6;
    static public int ORDER_AFTER_SALE = 7;

    //定义创建订单操作结果
    //state=0 -> 创建成功, state=-1 -> 订单创建失败
    static public int CREATE_ORDER_SUCCESSFULLY = 0;
    static public int CREATE_ORDER_FAILED = -1;

    //定义修改订单状态操作结果
    //state=0 -> 修改成功, state=-1 -> 订单修改失败
    static public int MODIFY_ORDER_SUCCESSFULLY = 0;
    static public int MODIFY_ORDER_FAILED = -1;

    //定义查询订单状态操作结果
    //state=0 -> 查询成功, state=-1 -> 订单查询失败
    static public int QUERY_ORDER_SUCCESSFULLY = 0;
    static public int QUERY_ORDER_FAILED = -1;

    private int orderID;
    private double orderMoney;
    private String orderTime;
    private int orderState;
    private int orderType;
    private int clientID;
    private int serverID;
    private String orderContent;
    private String orderAddress;
    private String orderReserveTime;
    private String orderRemarkContent;
    private int orderScore;

    public UserOrder() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
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

    public String getOrderReserveTime() {
        return orderReserveTime;
    }

    public void setOrderReserveTime(String orderReserveTime) {
        this.orderReserveTime = orderReserveTime;
    }

    public String getOrderRemarkContent() {
        return orderRemarkContent;
    }

    public void setOrderRemarkContent(String orderRemarkContent) {
        this.orderRemarkContent = orderRemarkContent;
    }

    public int getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(int orderScore) {
        this.orderScore = orderScore;
    }

    @Override
    public String toString() {
        return  "{\"orderID\":\"" + orderID +
                "\",\"orderMoney\":\"" + orderMoney +
                "\",\"orderTime\":\"" + orderTime +
                "\",\"orderState\":\"" + orderState +
                "\",\"orderType\":\"" + orderType +
                "\",\"clientID\":\"" + clientID +
                "\",\"serverID\":\"" + serverID +
                "\",\"orderContent\":\"" + orderContent +
                "\",\"orderAddress\":\"" + orderAddress +
                "\",\"orderReserveTime\":\"" + orderReserveTime +
                "\",\"orderRemarkContent\":\"" + orderRemarkContent +
                "\",\"orderScore\":\"" + orderScore+"\"}";
    }
}
