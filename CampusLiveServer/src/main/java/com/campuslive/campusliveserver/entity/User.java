package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description User实体
 */

public class User {
    //定义用户登陆方式
    static public int WECHAT_LOGIN = 0;
    static public int ANDROID_LOGIN = 1;

    //定义用户是否存在变量
    //state=0 -> 用户不存在, state=1 -> 用户存在
    static public int NOT_EXISTED = 0;
    static public int IS_EXISTED = 1;

    //定义用户操作状态变量
    //state=0 -> 用户已存在无需创建, state=1 -> 用户成功创建, state=2 -> 用户创建失败, state=-1 -> 用户密码错误
    static public int ACCOUNT_EXISTED = 0;
    static public int ACCOUNT_CREATED_SUCCESSFULLY = 1;
    static public int ACCOUNT_CREATED_FAIL = 2;
    static public int PASSWORD_WRONG = -1;

    //定义用户状态变量
    //state=0 -> 用户未进行实名验证, state=1 -> 用户正常, state=2 -> 用户帐号异常
    static public int NOT_VERIFY_IDENTITY = 0;
    static public int VERIFY_IDENTITY = 1;

    //定义用户学生认证结果变量
    //state=-1 -> 用户已经进行过学生认证, state=0 -> 用户学生认证失败, state=1 -> 用户学生认证成功
    static public int HAS_VERIFIED = -1;
    static public int VERIFY_FAIL = 0;
    static public int VERIFY_SUCCESS = 1;

    //定义查询个人信息操作状态
    //state=-1 -> 查询用户失败, state=0 -> 查询成功
    static  public int QUERY_USER_SUCCESSFULLY = 0;
    static  public int QUERY_USER_FAILED = -1;

    private int userID;
    private String userPsd;
    private String userName;
    private String userSchool;
    private int userAge;
    private String userPhone;
    private String userMail;
    private String userWechat;
    private double userBalance;
    private int userCredit;
    private int userType;
    private int userState;
    private int businessNum;
    private double employeeBasicSalary;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserWechat() {
        return userWechat;
    }

    public void setUserWechat(String userWechat) {
        this.userWechat = userWechat;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    public int getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(int userCredit) {
        this.userCredit = userCredit;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public int getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(int businessNum) {
        this.businessNum = businessNum;
    }

    public double getEmployeeBasicSalary() {
        return employeeBasicSalary;
    }

    public void setEmployeeBasicSalary(double employeeBasicSalary) {
        this.employeeBasicSalary = employeeBasicSalary;
    }

    @Override
    public String toString() {
        return "{\"userID\":\"" + userID +
                "\",\"userPsd\":\"" + userPsd +
                "\",\"userName\":\"" + userName +
                "\",\"userSchool\":\"" + userSchool +
                "\",\"userAge\":\"" + userAge +
                "\",\"userPhone\":\"" + userPhone +
                "\",\"userMail\":\"" + userMail +
                "\",\"userWechat\":\"" + userWechat +
                "\",\"userBalance\":\"" + userBalance +
                "\",\"userCredit\":\"" + userCredit +
                "\",\"userType\":\"" + userType +
                "\",\"userState\":\"" + userState +
                "\",\"businessNum\":\"" + businessNum +
                "\",\"employeeBasicSalary\":\"" + employeeBasicSalary +"\"}";
    }
}
