package com.campuslive.campusliveserver.dao;

import com.campuslive.campusliveserver.entity.ComplaintRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 * @description ComplaintRecordMapper对数据库的操作
 */
@Repository
@Mapper
public interface ComplaintRecordMapper {
    //插入投诉记录
    @Insert("insert into complaintRecord (complaintID,orderID,complainantID,complaineeID,complaintTime,complaintState,complaintContent)" +
            "values (#{complaintID},#{orderID},#{complainantID},#{complaineeID},#{complaintTime},#{complaintState},#{complaintContent})")
    void addComplaintRecord(ComplaintRecord complaintRecord);

    //获取最大投诉记录ID
    @Select("select max(complaintID) from complaintRecord")
    int getMaxComplaintID();
}
