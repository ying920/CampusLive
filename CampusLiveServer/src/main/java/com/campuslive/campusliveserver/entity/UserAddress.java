package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description UserAddress实体
 */

public class UserAddress {
    //定义用户添加地址状态
    static public int ADD_ADDRESS_FAIL = -1;
    static public int ADD_ADDRESS_SUCCESSFULLY = 0;

    //定义查询用户地址状态
    static public int GET_ADDRESS_FAIL = -1;
    static public int GET_ADDRESS_SUCCESSFULLY = 0;

    private int userID;
    private String address;

    public UserAddress() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{\"userID\":\"" + userID +
                "\",\"address\":\"" + address +"\"}";
    }
}
