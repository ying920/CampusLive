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

    //接单功能，修改订单接单人和订单状态
    @Update("update userOrder set serverID=#{serverID},orderState=#{orderState} where orderID=#{orderID}")
    void serverGetOrder(int orderID,int serverID,int orderState);

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
    @Select("select * from userOrder where orderState=2")
    List<UserOrder> getAllMissedOrder();

    //获取指定用户所有订单信息
    @Select("select * from userOrder where clientID=#{clientID}")
    List<UserOrder> getAllMyOrder(int clientID);

//    //获取指定用户未接单订单信息
//    @Select("select * from userOrder where clientID=#{clientID} and orderState=0")
//    List<UserOrder> getMyMissedOrder(int clientID);

    //获取指定下单者指定状态订单
    @Select("select * from userOrder where clientID=#{clientID} and orderState=#{orderState}")
    List<UserOrder> getTheOrder(int clientID,int orderState);

//    //获取指定服务用户未接单订单信息
//    @Select("select * from userOrder where serverID=#{serverID} and orderState=1")
//    List<UserOrder> getMyReceivedServerOrder(int serverID);

    //获取指定服务者指定状态订单
    @Select("select * from userOrder where serverID=#{serverID} and orderState=#{orderState}")
    List<UserOrder> getTheServerOrder(int serverID,int orderState);

    //更改订单评论
    @Update("update userOrder set orderRemarkContent=#{orderRemarkContent},orderScore=#{orderScore} where orderID=#{orderID}")
    void addOrderRemark(int orderID, String orderRemarkContent,int orderScore);

    //获取相应服务者订单评分的平均值
    @Select("select avg(orderScore) from userOrder where serverID=#{serverID} and orderState=6")
    double getUserCredit(int serverID);
}
