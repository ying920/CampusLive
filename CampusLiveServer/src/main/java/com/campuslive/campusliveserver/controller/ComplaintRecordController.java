package com.campuslive.campusliveserver.controller;

import com.campuslive.campusliveserver.dao.ComplaintRecordMapper;
import com.campuslive.campusliveserver.dao.UserOrderMapper;
import com.campuslive.campusliveserver.entity.ComplaintRecord;
import com.campuslive.campusliveserver.entity.UserOrder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.campuslive.campusliveserver.entity.ComplaintRecord.ADD_COMPLAINT_RECORD_FAILED;
import static com.campuslive.campusliveserver.entity.ComplaintRecord.ADD_COMPLAINT_RECORD_SUCCESSFULLY;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description ComplaintRecord的控制层
 */

@RestController
public class ComplaintRecordController {
    private final ComplaintRecordMapper complaintRecordMapper;
    private final UserOrderMapper userOrderMapper;

    public ComplaintRecordController(ComplaintRecordMapper complaintRecordMapper,UserOrderMapper userOrderMapper){
        this.complaintRecordMapper = complaintRecordMapper;
        this.userOrderMapper = userOrderMapper;
    }

    /**
     * @author 林新宇
     * @Phone 17810204868
     * @email aomiga523@163.com
     * @description 添加订单操作，POST请求json格式数据
     * @param json 详见AddComplaintRecordJSON.txt
     * @return json格式字符串 详见RAddComplaintRecordJSON.txt
     * @throws JSONException  抛出JSON相关异常
     */
    @ResponseBody
    @RequestMapping(value = "/add-complaint-record", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addComplaintRecord(@RequestBody String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        JSONObject dataJsonObject = jsonObject.getJSONObject("data");

        int complaintID = complaintRecordMapper.getMaxComplaintID()+1;
        int orderID = dataJsonObject.getInt("orderID");
        String complaintContent = dataJsonObject.getString("complaintContent");
        String complaintTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        UserOrder userOrder = userOrderMapper.getOrder(orderID);
        int complainantID=userOrder.getClientID();
        int complaineeID=userOrder.getServerID();

        ComplaintRecord complaintRecord = new ComplaintRecord();
        complaintRecord.setComplaintID(complaintID);
        complaintRecord.setOrderID(orderID);
        complaintRecord.setComplainantID(complainantID);
        complaintRecord.setComplaineeID(complaineeID);
        complaintRecord.setComplaintTime(complaintTime);
        complaintRecord.setComplaintContent(complaintContent);

        //创建返回Json对象
        JSONObject returnJson = new JSONObject();

//        try{
            complaintRecordMapper.addComplaintRecord(complaintRecord);
//        }catch (Exception e){
//            returnJson.put("data",null);
//            returnJson.put("msg","Add complaint record failed!");
//            returnJson.put("check",ADD_COMPLAINT_RECORD_FAILED);
//            return returnJson.toString();
//        }
        JSONObject returnDataJsonObject = new JSONObject();
        returnDataJsonObject.put("complaintID",complaintID);
        returnDataJsonObject.put("orderID",orderID);
        returnDataJsonObject.put("complainantID",complainantID);
        returnDataJsonObject.put("complaineeID",complaineeID);
        returnDataJsonObject.put("complaintTime", complaintTime);
        returnDataJsonObject.put("complaintContent", complaintContent);
        returnJson.put("data",returnDataJsonObject);
        returnJson.put("msg","Add complaint record successfully!");
        returnJson.put("check",ADD_COMPLAINT_RECORD_SUCCESSFULLY);
        return returnJson.toString();
    }
}
