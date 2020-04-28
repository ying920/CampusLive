package com.campuslive.campusliveserver.dao;

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
    @Select("select address from userAddress where userID=#{userID}")
    List<String> getAddress(int userID);
}
