package com.campuslive.campusliveserver.controller;

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

    public UserOrderController(UserOrderMapper userOrderMapper){
        this.userOrderMapper = userOrderMapper;
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
            returnJson.put("check",MODIFY_ORDER_STATE_FAILED);
            return returnJson.toString();
        }
        JSONObject returnDataJsonObject = new JSONObject(userOrderMapper.getOrder(orderID).toString());
        returnJson.put("data",returnDataJsonObject);
        returnJson.put("msg","Modify userOrder state successfully!");
        returnJson.put("check",MODIFY_ORDER_STATE_SUCCESSFULLY);
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
        String orderTime = getTime();
        int clientID = dataJsonObject.getInt("clientID");
        String orderContent = dataJsonObject.getString("orderContent");
        String orderAddress = dataJsonObject.getString("orderAddress");

        UserOrder userOrder = new UserOrder();
        userOrder.setOrderID(orderID);
        userOrder.setOrderTime(orderTime);
        userOrder.setOrderMoney(orderMoney);
        userOrder.setClientID(clientID);
        userOrder.setOrderContent(orderContent);
        userOrder.setOrderAddress(orderAddress);

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();
        //returnJson.put("data",null);

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
     * @description 查看订单操作，GET请求
     * @param clientID GET传入参数
     * @return json格式字符串 详见RGetAllMyOrderJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/get-all-my-order/{clientID}", method= RequestMethod.GET)
    public String getAllMyOrder(@PathVariable int clientID) throws JSONException{
        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

        try {
            JSONArray returnDataJsonArray = new JSONArray();
            List<UserOrder> missedOrderList = userOrderMapper.getAllMyOrder(clientID);
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

    //获取当前系统时间
    public String getTime(){
        Date date = new Date();//获得系统时间.
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
