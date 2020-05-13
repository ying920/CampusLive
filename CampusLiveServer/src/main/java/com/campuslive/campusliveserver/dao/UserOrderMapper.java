package com.campuslive.campusliveserver.dao;

import com.campuslive.campusliveserver.entity.UserOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description Order对数据库的操作
 */

@Repository
@Mapper
public interface UserOrderMapper {
    //查询订单状态
    @Select("select orderState from userOrder where orderID=#{orderID}")
    int getOrderState(int orderID);

    //更改订单状态
    @Update("update userOrder set orderState=#{orderState} where orderID=#{orderID}")
    void changeOrderState(int orderID, int orderState);

    //写入订单，下单功能
    @Insert("insert into userOrder (orderID,orderMoney,orderTime,orderType,clientID,serverID,orderContent,orderAddress,orderReserveTime,orderScore) " +
            "values (#{orderID},#{orderMoney},#{orderTime},#{orderType},#{clientID},#{serverID},#{orderContent},#{orderAddress},#{orderReserveTime},#{orderScore})")
    void addOrder(UserOrder userOrder);

    //获取最大订单ID
    @Select("select max(orderID) from userOrder")
    int getMaxOrderID();

    //获取订单信息
    @Select("select * from userOrder where orderID=#{orderID}")
    UserOrder getOrder(int orderID);

    //获取所有未接单订单信息
    @Select("select * from userOrder where orderState=0")
    List<UserOrder> getAllMissedOrder();

    //获取指定用户所有订单信息
    @Select("select * from userOrder where clientID=#{clientID}")
    List<UserOrder> getAllMyOrder(int clientID);

    //获取指定用户未接单订单信息
    @Select("select * from userOrder where clientID=#{clientID} and orderState=0")
    List<UserOrder> getMyMissedOrder(int clientID);

    //获取指定用户已取消订单信息
    @Select("select * from userOrder where clientID=#{clientID} and orderState=-1")
    List<UserOrder> getMyCanceledOrder(int clientID);

    //获取指定用户未接单订单信息
    @Select("select * from userOrder where clientID=#{clientID} and orderState=1")
    List<UserOrder> getMyReceivedOrder(int clientID);

    //获取指定用户已完成订单信息
    @Select("select * from userOrder where clientID=#{clientID} and orderState=2")
    List<UserOrder> getMyFinishedOrder(int clientID);

    //获取指定用户已付款订单信息
    @Select("select * from userOrder where clientID=#{clientID} and orderState=3")
    List<UserOrder> getMyPaidOrder(int clientID);

    //获取指定用户已评分订单信息
    @Select("select * from userOrder where clientID=#{clientID} and orderState=4")
    List<UserOrder> getMyMarkedOrder(int clientID);

    //获取指定用户售后订单信息
    @Select("select * from userOrder where clientID=#{clientID} and orderState=5")
    List<UserOrder> getMyAfterSaleOrder(int clientID);
}
