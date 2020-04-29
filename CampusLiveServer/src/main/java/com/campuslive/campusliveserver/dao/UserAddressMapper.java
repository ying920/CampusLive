package com.campuslive.campusliveserver.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description UserAddressMapper对数据库的操作
 */

@Repository
@Mapper
public interface UserAddressMapper {
    //获取用户地址列表
    @Select("select address from userAddress where userID=#{userID}")
    List<String> getAddress(int userID);

    //增加用户地址
    @Insert("insert into userAddress (userID,address) values (#{userID},#{address}")
    void addUserAddress(int userID, String address);
}
