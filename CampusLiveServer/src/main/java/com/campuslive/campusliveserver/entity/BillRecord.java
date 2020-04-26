package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description BillRecord实体
 */

public class BillRecord {
    private int userID;
    private int billAmount;
    private int billType;
    private String billTime;    //Format "YYYY-MM-DD HH:mm:ss"等同于MySQL的DateTime类型
    private String billSource;

    public BillRecord() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getBillSource() {
        return billSource;
    }

    public void setBillSource(String billSource) {
        this.billSource = billSource;
    }

    @Override
    public String toString() {
        return "BillRecord{" +
                "userID=" + userID +
                ", billAmount=" + billAmount +
                ", billType=" + billType +
                ", billTime='" + billTime + '\'' +
                ", billSource='" + billSource + '\'' +
                '}';
    }
}
