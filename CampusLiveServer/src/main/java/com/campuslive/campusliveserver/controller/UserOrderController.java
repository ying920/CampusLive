package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.UserOrderMapper;
import com.campuslive.campusliveserver.entity.UserOrder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    //获取当前系统时间
    public String getTime(){
        Date date = new Date();//获得系统时间.
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return nowTime;
    }
}
