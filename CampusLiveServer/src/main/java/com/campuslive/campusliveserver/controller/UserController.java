package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.UserMapper;
import com.campuslive.campusliveserver.entity.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description User的控制层
 */

@RestController
public class UserController {
    //实现自动装配
    @Autowired
    private UserMapper userMapper;

//    //form提交形式
//    //验证账号密码
//    @PostMapping(value="/login")
//    public String login(@RequestParam("userID") int userID,
//                        @RequestParam("userPsd") String userPsd) {
//        User user = new User();
//        user.setUserID(userID);
//        user.setUserPsd(userPsd);
//        System.out.printf(userID+userPsd);
//        userMapper.add(user);
//        return "id:"+userID+", Psd:"+userPsd;
//    }

    //json形式

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 登陆操作，POST请求json格式数据
     * @param json
     * @return
     * @throws JSONException
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        int check = jsonObject.getInt("check");
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        int userID = dataJsonObject.getInt("userID");
        String userPsd = dataJsonObject.getString("userPsd");

        //如果账户存在便显示存在
        if(userMapper.isExist(userID)>0){
            //微信用户直接显示已存在
            if(check==0){
                return "Account exists!";
            }
            //Android用户检验密码是否正确
            else if(check==1){
                int isPsdCorrect=userMapper.isPsdCorrect(userID,userPsd);
                if(isPsdCorrect==0){
                    return "Password is wrong!";
                }else{
                    return "Account exists!";
                }
            }else if(check==2){
                return "This is Web Account";
            }
        }

        //账户不存在新建账户
        User user = new User();
        user.setUserID(userID);
        //1-Android产生密码
        if(check==1){
            user.setUserPsd(userPsd);
        }

        //添加用户
        userMapper.add(user);
        return "Create account successfully!";
    }
}
