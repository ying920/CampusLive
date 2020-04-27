package com.campuslive.campusliveserver.dao;

import com.campuslive.campusliveserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description User对数据库的操作
 */

@Repository
@Mapper
public interface UserMapper {
    //注册成功，插入一条数据
    @Insert("insert into user (userID,userPsd) values (#{userID},#{userPsd})")
    void add(User user);

    //验证用户是否存在
    @Select("select count(userID) from User where userID=#{userID}")
    int isExist(int userID);

    //验证Android用户密码是否正确
    @Select("select count(userID) from user where userID=#{userID} and userPsd=#{userPsd}")
    int isPsdCorrect(int userID,String userPsd);

    //验证用户是否已经实名验证
    //state=0 -> 用户未进行实名验证, state=1 -> 用户正常, state=2 -> 用户帐号异常
    @Select("select userState from user where userID=#{userID}")
    int getUserState(int userID);
}
