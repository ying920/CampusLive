package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.UserAddressMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        String str = userAddressMapper.getAddress(userID).toString();

        JSONObject returnJson = new JSONObject();
        returnJson.put("data",str);
        returnJson.put("msg","Successfully get address!");

        return returnJson.toString();
    }

}
