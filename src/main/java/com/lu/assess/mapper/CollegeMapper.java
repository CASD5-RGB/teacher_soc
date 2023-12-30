package com.lu.assess.mapper;

import com.lu.assess.pojo.College;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: helu
 * @date: 2022/7/15 15:59
 * @description:
 */
public interface CollegeMapper {
    //查询学院
    List<College> selectCollege();
    College findCollegeByCid(Integer cid);

    //增加学院
    Integer insertCollege(@Param("collegeName") String collegeName);

    //删除学院
    Integer deleteCollegeByCid(Integer cid);

    //更新学院名称
    Integer updateCollegeByCid(Integer cid,String collegeName);



}
