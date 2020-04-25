package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description User实体
 */

public class User {
    private int userID;
    private String userPsd;

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

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userPsd='" + userPsd + '\'' +
                '}';
    }
}
