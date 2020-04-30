package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.UserAddressMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.campuslive.campusliveserver.entity.UserAddress.ADD_ADDRESS_FAIL;
import static com.campuslive.campusliveserver.entity.UserAddress.ADD_ADDRESS_SUCCESSFULLY;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description UserAddress的控制层
 */

@RestController
public class UserAddressController {
    private final UserAddressMapper userAddressMapper;

    public UserAddressController(UserAddressMapper userAddressMapper){
        this.userAddressMapper = userAddressMapper;
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 获取地址操作，GET请求
     * @param userID GET传入参数
     * @return json格式字符串 详见RGetAddressJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @RequestMapping(value="/get-address/{userID}", method= RequestMethod.GET)
    public String getAllUserAddress(@PathVariable int userID) throws JSONException {
        //String str = userAddressMapper.getAddress(userID).toString();
        List<String> stringList=userAddressMapper.getAddress(userID);

        JSONObject returnJson = new JSONObject();

        JSONArray returnDataJsonArray = new JSONArray();
        for(String str:stringList){
            JSONObject addressJson = new JSONObject();
            addressJson.put("address",str);
            returnDataJsonArray.put(addressJson);
        }

        returnJson.put("data",returnDataJsonArray);
        returnJson.put("msg","Successfully get address!");

        return returnJson.toString();
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 添加地址操作，POST请求
     * @param json POST传入参数 详见AddAddressJSON.txt
     * @return json格式字符串 详见RAddAddressJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value="/add-address", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addAddress(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();
        returnJson.put("data",null);

        //获取对应数据
        int userID=dataJsonObject.getInt("userID");
        String userAddress = dataJsonObject.getString("userAddress");

        try {
            userAddressMapper.addUserAddress(userID,userAddress);
        }catch (Exception e){
            returnJson.put("msg","Add user address fail!");
            returnJson.put("check",ADD_ADDRESS_FAIL);
            return returnJson.toString();
        }
        returnJson.put("msg","Add user address successfully!");
        returnJson.put("check",ADD_ADDRESS_SUCCESSFULLY);
        return returnJson.toString();
    }

}
