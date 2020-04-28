package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.StudentMapper;
import com.campuslive.campusliveserver.dao.UserMapper;
import com.campuslive.campusliveserver.entity.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import static com.campuslive.campusliveserver.entity.User.*;


/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description User的控制层
 */

@RestController
public class UserController {
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;

    public UserController(UserMapper userMapper, StudentMapper studentMapper) {
        this.userMapper = userMapper;
        this.studentMapper = studentMapper;
    }

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
     * @param json 详见LoginJSON.txt
     * @return json格式字符串 详见RLoginJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        int check = jsonObject.getInt("check");
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        int userID = dataJsonObject.getInt("userID");
        String userPsd = dataJsonObject.getString("userPsd");

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();
        returnJson.put("data",null);

        //如果账户存在便显示存在
        if(userMapper.isExist(userID)==IS_EXISTED){
            //微信用户直接显示已存在
            if(check==WECHAT_LOGIN){
                returnJson.put("msg","Account exists!");
                returnJson.put("check",ACCOUNT_EXISTED);
            }
            //Android用户检验密码是否正确
            else if(check==ANDROID_LOGIN){
                int isPsdCorrect=userMapper.isPsdCorrect(userID,userPsd);
                if(isPsdCorrect==0){
                    returnJson.put("msg","Password is wrong!");
                    returnJson.put("check",PASSWORD_WRONG);
                }else{
                    returnJson.put("msg","Account exists!");
                    returnJson.put("check",ACCOUNT_EXISTED);
                }
            }
            return returnJson.toString();
        }

        //账户不存在新建账户
        User user = new User();
        user.setUserID(userID);
        user.setUserType(0);
        //1-Android产生密码
        if(check==1){
            user.setUserPsd(userPsd);
            user.setUserType(1);
        }

        try{
            //添加用户
            userMapper.add(user);
            //初始化用户，未进行学生认证
            userMapper.updateUserState(userID,NOT_VERIFY_IDENTITY);
            returnJson.put("msg","Create account successfully!");
            returnJson.put("check", ACCOUNT_CREATED_SUCCESSFULLY);
        }catch (Exception e){
            returnJson.put("msg","Create account fail!");
            returnJson.put("check",ACCOUNT_CREATED_FAIL);
        }
        return returnJson.toString();
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 实名验证操作，POST请求json格式数据
     * @param json 详见IdentityVerifyJSON.txt
     * @return json格式字符串 详见RIdentityVerifyJSON.txt
     * @throws JSONException 抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/identity-verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String realNameVerify(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();
        returnJson.put("data",null);

        //获取对应数据
        int userID=dataJsonObject.getInt("userID");
        String stuPersonID=dataJsonObject.getString("stuPersonID");
        int stuID=dataJsonObject.getInt("stuID");
        String stuName = dataJsonObject.getString("stuName");
        String stuSchool=dataJsonObject.getString("stuSchool");

        if(userMapper.getUserState(userID)==VERIFY_IDENTITY){
            returnJson.put("msg","Account has been verified!");
            returnJson.put("check",HAS_VERIFIED);
            return returnJson.toString();
        }

        int isRealName = studentMapper.identityVerify(stuPersonID,stuID,stuName,stuSchool);
        if(isRealName==VERIFY_FAIL){
            returnJson.put("msg","Error information!");
            returnJson.put("check",VERIFY_FAIL);
            return returnJson.toString();
        }else{
            userMapper.updateUserState(userID,VERIFY_IDENTITY);
        }
        returnJson.put("msg","Verify successfully!");
        returnJson.put("check",VERIFY_SUCCESS);
        return returnJson.toString();
    }

}
