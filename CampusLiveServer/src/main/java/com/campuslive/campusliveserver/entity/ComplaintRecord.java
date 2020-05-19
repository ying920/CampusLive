package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description ComplaintRecord实体
 */
public class ComplaintRecord {
    //定义添加投诉订单操作状态
    static public int ADD_COMPLAINT_RECORD_FAILED = -1;
    static public int ADD_COMPLAINT_RECORD_SUCCESSFULLY = 0;

    private int complaintID;
    private int orderID;
    private int complainantID;
    private int complaineeID;
    private int serviceID;
    private String complaintTime;
    private int complaintState;
    private String complaintContent;

    public ComplaintRecord() {
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getComplaineeID() {
        return complaineeID;
    }

    public void setComplaineeID(int complaineeID) {
        this.complaineeID = complaineeID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getComplainantID() {
        return complainantID;
    }

    public void setComplainantID(int complainantID) {
        this.complainantID = complainantID;
    }

    public String getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(String complaintTime) {
        this.complaintTime = complaintTime;
    }

    public int getComplaintState() {
        return complaintState;
    }

    public void setComplaintState(int complaintState) {
        this.complaintState = complaintState;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    @Override
    public String toString() {
        return "{\"complaintID\":\"" + complaintID +
                "\",\"orderID\":\"" + orderID +
                "\",\"complaineeID\":\"" + complaineeID +
                "\",\"serviceID\":\"" + serviceID +
                "\",\"complainantID\":\"" + complainantID +
                "\",\"complaintTime\":\"" + complaintTime +
                "\",\"complaintState\":\"" + complaintState +
                "\",\"complaintContent\":\"" + complaintContent +"\"}";
    }
}
