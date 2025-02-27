package com.campuslive.campusliveserver.dao;

import com.campuslive.campusliveserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
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
    void addUser(User user);

    //验证用户是否存在
    @Select("select count(userID) from user where userID=#{userID}")
    int isExist(int userID);

    //验证Android用户密码是否正确
    @Select("select count(userID) from user where userID=#{userID} and userPsd=#{userPsd}")
    int isPsdCorrect(int userID,String userPsd);

    //验证用户是否已经实名验证
    //state=0 -> 用户未进行实名验证, state=1 -> 用户正常, state=2 -> 用户帐号异常
    @Select("select userState from user where userID=#{userID}")
    int getUserState(int userID);

    //修改用户的状态
    //state=0 -> 用户未进行实名验证, state=1 -> 用户正常, state=2 -> 用户帐号异常
    @Update("update user set userState=#{userState} where userID=#{userID}")
    void updateUserState(int userID,int userState);

    //获取指定用户个人信息
    @Select("select * from user where userID=#{userID}")
    User getMyAccount(int userID);

    //获取账户余额
    @Select("select userBalance from user where userID=#{userID}")
    double getUserBalance(int userID);

    //修改账户余额
    @Update("update user set userBalance=userBalance+#{balance} where userID=#{userID}")
    void modifyUserBalance(int userID,double balance);

    //修改用户评分
    @Update("update user set userCredit=#{userCredit} where userID=#{userID}")
    void modifyUserCredit(int userID,double userCredit);

}
