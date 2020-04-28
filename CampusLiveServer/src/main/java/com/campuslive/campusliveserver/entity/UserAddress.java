package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description UserAddress实体
 */

public class UserAddress {
    //定义用户添加地址状态
    static public int ADD_ADDRESS_FAIL = 0;
    static public int ADD_ADDRESS_SUCCESSFULLY = 1;

    private int userID;
    private String userAddress;

    public UserAddress() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "userID=" + userID +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
