package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.UserMapper;
import com.campuslive.campusliveserver.dao.UserOrderMapper;
import com.campuslive.campusliveserver.entity.UserOrder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.campuslive.campusliveserver.entity.UserOrder.*;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description UserOrder的控制层
 */

@RestController
public class UserOrderController {
    private final UserOrderMapper userOrderMapper;
    private final UserMapper userMapper;

    public UserOrderController(UserOrderMapper userOrderMapper,UserMapper userMapper){
        this.userOrderMapper = userOrderMapper;
        this.userMapper = userMapper;
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 修改订单状态操作，POST请求json格式数据
     * @param json 详见ChangeOrderStateJSON.txt
     * @return json格式字符串 详见RChangeOrderStateJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/change-order-state", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String changeOrderState(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        int orderID = dataJsonObject.getInt("orderID");
        int state = dataJsonObject.getInt("orderState");

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try{
            userOrderMapper.changeOrderState(orderID,state);
        }catch (Exception e){
            returnJson.put("data",null);
            returnJson.put("msg","Modify userOrder state failed!");
            returnJson.put("check", MODIFY_ORDER_FAILED);
            return returnJson.toString();
        }
        JSONObject returnDataJsonObject = new JSONObject(userOrderMapper.getOrder(orderID).toString());
        returnJson.put("data",returnDataJsonObject);
        returnJson.put("msg","Modify userOrder state successfully!");
        returnJson.put("check", MODIFY_ORDER_SUCCESSFULLY);
        return returnJson.toString();
    }


    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 接单操作，修改接单人和订单状态，POST请求json格式数据
     * @param json 详见ServerGetOrderJSON.txt
     * @return json格式字符串 详见RServerGetOrderJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/server-get-order", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String serverGetOrder(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        int orderID = dataJsonObject.getInt("orderID");
        int serverID = dataJsonObject.getInt("serverID");
        int state = dataJsonObject.getInt("orderState");

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try{
            userOrderMapper.serverGetOrder(orderID,serverID,state);
        }catch (Exception e){
            returnJson.put("data",null);
            returnJson.put("msg","Modify userOrder state failed!");
            returnJson.put("check", MODIFY_ORDER_FAILED);
            return returnJson.toString();
        }
        JSONObject returnDataJsonObject = new JSONObject(userOrderMapper.getOrder(orderID).toString());
        returnJson.put("data",returnDataJsonObject);
        returnJson.put("msg","Modify userOrder state successfully!");
        returnJson.put("check", MODIFY_ORDER_SUCCESSFULLY);
        return returnJson.toString();
    }


    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 添加订单操作，POST请求json格式数据
     * @param json 详见AddOrderJSON.txt
     * @return json格式字符串 详见RAddOrderJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/add-order", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addOrder(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        int orderID = userOrderMapper.getMaxOrderID()+1;
        double orderMoney = dataJsonObject.getDouble("orderMoney");
        String orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int orderType = dataJsonObject.getInt("orderType");
        int clientID = dataJsonObject.getInt("clientID");
        String orderContent = dataJsonObject.getString("orderContent");
        String orderAddress = dataJsonObject.getString("orderAddress");
        String orderReserveTime = dataJsonObject.getString("orderReserveTime");

        UserOrder userOrder = new UserOrder();
        userOrder.setOrderID(orderID);
        userOrder.setOrderTime(orderTime);
        userOrder.setOrderType(orderType);
        userOrder.setOrderMoney(orderMoney);
        userOrder.setClientID(clientID);
        userOrder.setOrderContent(orderContent);
        userOrder.setOrderAddress(orderAddress);
        userOrder.setOrderReserveTime(orderReserveTime);

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try{
            userOrderMapper.addOrder(userOrder);
            userOrderMapper.changeOrderState(orderID,ORDER_DEFAULT);
        }catch (Exception e){
            returnJson.put("data",null);
            returnJson.put("msg","Create userOrder failed!");
            returnJson.put("check",CREATE_ORDER_FAILED);
            return returnJson.toString();
        }
        JSONObject returnDataJsonObject = new JSONObject();
        returnDataJsonObject.put("orderID",orderID);
        returnDataJsonObject.put("orderTime",orderTime);
        returnDataJsonObject.put("orderMoney",orderMoney);
        returnDataJsonObject.put("orderState", userOrderMapper.getOrderState(orderID));
        returnJson.put("data",returnDataJsonObject);
        returnJson.put("msg","Create userOrder successfully!");
        returnJson.put("check",CREATE_ORDER_SUCCESSFULLY);
        return returnJson.toString();
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 查看订单操作，GET请求
     * @param orderID GET传入参数
     * @return json格式字符串 详见RGetOrderJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/get-order/{orderID}", method= RequestMethod.GET)
    public String getOneOrder(@PathVariable int orderID) throws JSONException{
        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try{
            JSONObject returnDataJsonObject = new JSONObject(userOrderMapper.getOrder(orderID).toString());
            returnJson.put("data",returnDataJsonObject);
            returnJson.put("msg","Query userOrder successfully!");
            returnJson.put("check",QUERY_ORDER_SUCCESSFULLY);
        }catch (Exception e){
            returnJson.put("data",null);
            returnJson.put("msg","Query userOrder failed!");
            returnJson.put("check",QUERY_ORDER_FAILED);
        }

        return returnJson.toString();
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 查看所有未接单的订单，GET请求
     * @return json格式字符串 详见RGetAllMissedOrderJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/get-all-missed-order", method = RequestMethod.GET)
    public String getAllMissedOrder() throws JSONException{
        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try {
            JSONArray returnDataJsonArray = new JSONArray();
            List<UserOrder> missedOrderList = userOrderMapper.getAllMissedOrder();
            for (UserOrder userOrder : missedOrderList) {
                JSONObject missedOrderJson = new JSONObject(userOrder.toString());
                returnDataJsonArray.put(missedOrderJson);
            }

            returnJson.put("data", returnDataJsonArray);
            returnJson.put("msg", "Get all missed order successfully!");
            returnJson.put("check",QUERY_ORDER_SUCCESSFULLY);
        }catch (Exception e){
            returnJson.put("data",null);
            returnJson.put("msg","Query userOrder failed!");
            returnJson.put("check",QUERY_ORDER_FAILED);
        }

        return returnJson.toString();
    }


    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 查看指定用户所有订单，GET请求
     * @param clientID GET传入参数
     * @return json格式字符串 详见RGetAllMyOrderJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/get-all-my-order/{clientID}", method= RequestMethod.GET)
    public String getAllMyOrder(@PathVariable int clientID) throws JSONException{
        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        JSONObject returnDataJson = new JSONObject();

        try {

            //获取订单取消订单
            JSONArray canceledOrderJsonArray = new JSONArray();
            List<UserOrder> canceledOrderList = userOrderMapper.getTheOrder(clientID,ORDER_CANCELED);
            for (UserOrder userOrder : canceledOrderList) {
                JSONObject cancelOrderJson = new JSONObject(userOrder.toString());
                canceledOrderJsonArray.put(cancelOrderJson);
            }
            returnDataJson.put("CanceledOrder", canceledOrderJsonArray);

            //获取已下单,未付款订单
            JSONArray missedOrderJsonArray = new JSONArray();
            List<UserOrder> missedOrderList = userOrderMapper.getTheOrder(clientID,ORDER_DEFAULT);
            for (UserOrder userOrder : missedOrderList) {
                JSONObject missedOrderJson = new JSONObject(userOrder.toString());
                missedOrderJsonArray.put(missedOrderJson);
            }
            returnDataJson.put("MissedOrder", missedOrderJsonArray);

            //获取已付款，未接单订单
            JSONArray paidOrderJsonArray = new JSONArray();
            List<UserOrder> paidOrderList = userOrderMapper.getTheOrder(clientID,ORDER_PAID);
            for (UserOrder userOrder : paidOrderList) {
                JSONObject paidOrderJson = new JSONObject(userOrder.toString());
                paidOrderJsonArray.put(paidOrderJson);
            }
            returnDataJson.put("PaidOrder", paidOrderJsonArray);

            //获取已接单,未完成订单
            JSONArray receivedOrderJsonArray = new JSONArray();
            List<UserOrder> receivedOrderList = userOrderMapper.getTheOrder(clientID,ORDER_RECEIVED);
            for (UserOrder userOrder : receivedOrderList) {
                JSONObject receivedOrderJson = new JSONObject(userOrder.toString());
                receivedOrderJsonArray.put(receivedOrderJson);
            }
            returnDataJson.put("ReceivedOrder", receivedOrderJsonArray);

            //获取已完成,未收货订单
            JSONArray finishedOrderJsonArray = new JSONArray();
            List<UserOrder> finishedOrderList = userOrderMapper.getTheOrder(clientID,ORDER_FINISHED);
            for (UserOrder userOrder : finishedOrderList) {
                JSONObject finishedOrderJson = new JSONObject(userOrder.toString());
                finishedOrderJsonArray.put(finishedOrderJson);
            }
            returnDataJson.put("FinishedOrder", finishedOrderJsonArray);

            //获取已收货,未评价订单
            JSONArray otherOrderJsonArray = new JSONArray();
            List<UserOrder> getOrderList = userOrderMapper.getTheOrder(clientID,ORDER_GET);
            for (UserOrder userOrder : getOrderList) {
                JSONObject getOrderJson = new JSONObject(userOrder.toString());
                otherOrderJsonArray.put(getOrderJson);
            }

            //获取已评分，无问题订单
            List<UserOrder> markedOrderList = userOrderMapper.getTheOrder(clientID,ORDER_MARKED);
            for (UserOrder userOrder : markedOrderList) {
                JSONObject markedOrderJson = new JSONObject(userOrder.toString());
                otherOrderJsonArray.put(markedOrderJson);
            }

            //获取需要售后订单
            List<UserOrder> afterSaleOrderList = userOrderMapper.getTheOrder(clientID,ORDER_AFTER_SALE);
            for (UserOrder userOrder : afterSaleOrderList) {
                JSONObject afterSaleOrderJson = new JSONObject(userOrder.toString());
                otherOrderJsonArray.put(afterSaleOrderJson);
            }

            returnDataJson.put("OtherOrder",otherOrderJsonArray);

            returnJson.put("data", returnDataJson);
            returnJson.put("msg", "Get all order successfully!");
            returnJson.put("check", QUERY_ORDER_SUCCESSFULLY);
        }catch (Exception e){
            returnJson.put("data",null);
            returnJson.put("msg","Query userOrder failed!");
            returnJson.put("check",QUERY_ORDER_FAILED);
        }

        return returnJson.toString();
    }


    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 查看指定服务用户所有订单
     * @param serverID GET传入参数
     * @return json格式字符串 详见RGetAllMyServerOrderJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/get-all-my-server-order/{serverID}", method= RequestMethod.GET)
    public String getAllMyServerOrder(@PathVariable int serverID) throws JSONException{
        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        JSONObject returnDataJson = new JSONObject();

        try {
            //获取已接单，未完成订单
            JSONArray receivedServerOrderJsonArray = new JSONArray();
            List<UserOrder> receivedServerOrderList = userOrderMapper.getTheServerOrder(serverID,ORDER_RECEIVED);
            for (UserOrder userOrder : receivedServerOrderList) {
                JSONObject receivedServerOrderJson = new JSONObject(userOrder.toString());
                receivedServerOrderJsonArray.put(receivedServerOrderJson);
            }
            returnDataJson.put("ReceivedServerOrder", receivedServerOrderJsonArray);

            //获取已完成,未收货订单
            JSONArray finishedServerOrderJsonArray = new JSONArray();
            List<UserOrder> finishedServerOrderList = userOrderMapper.getTheServerOrder(serverID,ORDER_FINISHED);
            for (UserOrder userOrder : finishedServerOrderList) {
                JSONObject finishedServerOrderJson = new JSONObject(userOrder.toString());
                finishedServerOrderJsonArray.put(finishedServerOrderJson);
            }
            returnDataJson.put("FinishedServerOrder", finishedServerOrderJsonArray);

            //获取已收货,未评价订单
            JSONArray paidServerOrderJsonArray = new JSONArray();
            List<UserOrder> paidServerOrderList = userOrderMapper.getTheServerOrder(serverID,ORDER_GET);
            for (UserOrder userOrder : paidServerOrderList) {
                JSONObject paidServerOrderJson = new JSONObject(userOrder.toString());
                paidServerOrderJsonArray.put(paidServerOrderJson);
            }
            returnDataJson.put("PaidServerOrder", paidServerOrderJsonArray);

            //获取已评分，无问题订单
            JSONArray markedServerOrderJsonArray = new JSONArray();
            List<UserOrder> markedServerOrderList = userOrderMapper.getTheServerOrder(serverID,ORDER_MARKED);
            for (UserOrder userOrder : markedServerOrderList) {
                JSONObject markedServerOrderJson = new JSONObject(userOrder.toString());
                markedServerOrderJsonArray.put(markedServerOrderJson);
            }
            returnDataJson.put("MarkedServerOrder", markedServerOrderJsonArray);

            //获取需要售后订单
            JSONArray afterSaleServerOrderJsonArray = new JSONArray();
            List<UserOrder> afterSaleServerOrderList = userOrderMapper.getTheServerOrder(serverID,ORDER_AFTER_SALE);
            for (UserOrder userOrder : afterSaleServerOrderList) {
                JSONObject afterSaleServerOrderJson = new JSONObject(userOrder.toString());
                afterSaleServerOrderJsonArray.put(afterSaleServerOrderJson);
            }
            returnDataJson.put("AfterSaleServerOrder", afterSaleServerOrderJsonArray);

            returnJson.put("data", returnDataJson);
            returnJson.put("msg", "Get all order successfully!");
            returnJson.put("check", QUERY_ORDER_SUCCESSFULLY);
        }catch (Exception e){
            returnJson.put("data",null);
            returnJson.put("msg","Query userOrder failed!");
            returnJson.put("check",QUERY_ORDER_FAILED);
        }

        return returnJson.toString();
    }


    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 修改订单评分评价操作，POST请求json格式数据
     * @param json 详见AddOrderRemarkJSON.txt
     * @return json格式字符串 详见RAddOrderRemarkJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/add-order-remark", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addOrderRemark(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        int orderID = dataJsonObject.getInt("orderID");
        String orderRemarkContent = dataJsonObject.getString("orderRemarkContent");
        int orderScore = dataJsonObject.getInt("orderScore");


        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try{
            userOrderMapper.addOrderRemark(orderID,orderRemarkContent,orderScore);
        }catch (Exception e){
            returnJson.put("msg","Modify userOrder failed!");
            returnJson.put("check", MODIFY_ORDER_FAILED);
            return returnJson.toString();
        }
        returnJson.put("msg","Modify userOrder successfully!");
        returnJson.put("check", MODIFY_ORDER_SUCCESSFULLY);
        return returnJson.toString();
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 修改client的账户，GET请求
     * @return json格式字符串 详见RClientPayMoneyJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/client-pay-money/{orderID}", method = RequestMethod.GET)
    public String clientPayMoney(@PathVariable int orderID) throws JSONException{
        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try {
            UserOrder userOrder = userOrderMapper.getOrder(orderID);
            int clientID = userOrder.getClientID();
            double orderMoney = userOrder.getOrderMoney();
            userMapper.modifyUserBalance(clientID,-orderMoney);

            returnJson.put("msg", "Pay successfully!");
            returnJson.put("check", MODIFY_ORDER_SUCCESSFULLY);
        }catch (Exception e){
            returnJson.put("msg","Pay failed!");
            returnJson.put("check", MODIFY_ORDER_FAILED);
        }

        return returnJson.toString();
    }


    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 修改server的账户，GET请求
     * @return json格式字符串 详见RServerPaidMoneyJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/server-paid-money/{orderID}", method = RequestMethod.GET)
    public String ServerPaidMoney(@PathVariable int orderID) throws JSONException{
        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try {
            UserOrder userOrder = userOrderMapper.getOrder(orderID);
            int serverID = userOrder.getServerID();
            double orderMoney = userOrder.getOrderMoney();
            userMapper.modifyUserBalance(serverID,orderMoney);

            returnJson.put("msg", "Pay successfully!");
            returnJson.put("check", MODIFY_ORDER_SUCCESSFULLY);
        }catch (Exception e){
            returnJson.put("msg","Pay failed!");
            returnJson.put("check", MODIFY_ORDER_FAILED);
        }

        return returnJson.toString();
    }
}
