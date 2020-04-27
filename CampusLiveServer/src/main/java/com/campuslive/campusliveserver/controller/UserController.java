package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.StudentMapper;
import com.campuslive.campusliveserver.dao.UserMapper;
import com.campuslive.campusliveserver.entity.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import static com.campuslive.campusliveserver.entity.User.NOT_VERIFY_IDENTITY;
import static com.campuslive.campusliveserver.entity.User.VERIFY_IDENTITY;


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
     * @return string
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
        user.setUserType(0);
        //1-Android产生密码
        if(check==1){
            user.setUserPsd(userPsd);
            user.setUserType(1);
        }

        //添加用户
        userMapper.add(user);
        //初始化用户，未进行学生认证
        userMapper.updateUserState(userID,NOT_VERIFY_IDENTITY);
        return "Create account successfully!";
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 实名验证操作，POST请求json格式数据
     * @param json 详见IdentityVerifyJSON.txt
     * @return string
     * @throws JSONException 抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/identity-verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String realNameVerify(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        //return dataJsonObject.toString();
        int userID=dataJsonObject.getInt("userID");
        String stuPersonID=dataJsonObject.getString("stuPersonID");
        int stuID=dataJsonObject.getInt("stuID");
        String stuName = dataJsonObject.getString("stuName");

        int isRealName = studentMapper.identityVerify(stuPersonID,stuID,stuName);
        if(isRealName==0){
            return "Error information";
        }else{
            userMapper.updateUserState(userID,VERIFY_IDENTITY);
        }
        return "This user: "+isRealName;
    }

}
