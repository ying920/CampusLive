package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description User实体
 */

public class User {
    //定义用户状态
    //state=0 -> 用户未进行实名验证, state=1 -> 用户正常, state=2 -> 用户帐号异常
    static public int NOT_VERIFY_IDENTITY = 0;
    static public int VERIFY_IDENTITY = 1;

    private int userID;
    private String userPsd;
    private int userType;

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userPsd='" + userPsd + '\'' +
                ", userType=" + userType +
                '}';
    }
}
