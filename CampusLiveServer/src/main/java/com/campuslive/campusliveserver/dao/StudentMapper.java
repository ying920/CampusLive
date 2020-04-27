package com.campuslive.campusliveserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description Student对数据库的操作，模拟学生认证操作
 */
@Repository
@Mapper
public interface StudentMapper {
    @Select("select count(stuPersonID) from student where stuPersonID=#{stuPersonID} and stuID=#{stuID} and stuName=#{stuName}")
    int identityVerify(String stuPersonID,int stuID,String stuName);
}

