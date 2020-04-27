package com.campuslive.campusliveserver.entity;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description Student实体，用于模拟学信网的数据库，对项目无作用
 */
public class Student {
    private String stuPersonID;
    private int stuID;
    private String stuName;

    public Student() {
    }

    public String getStuPersonID() {
        return stuPersonID;
    }

    public void setStuPersonID(String stuPersonID) {
        this.stuPersonID = stuPersonID;
    }

    public int getStuID() {
        return stuID;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuPersonID=" + stuPersonID +
                ", stuID=" + stuID +
                ", stuName='" + stuName + '\'' +
                '}';
    }
}
