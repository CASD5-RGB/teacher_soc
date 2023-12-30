package com.lu.assess.mapper;

import com.lu.assess.pojo.College;
import com.lu.assess.pojo.CollegeQuota;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/18 16:45
 * @description: 学院指标分配
 */
public interface CollegeQuotaMapper {

    //通过学院id查询指标
    CollegeQuota selectQuotaByCid(Integer cid);

    //添加指标
    Integer insertQuota(Integer cid,Double totalAnnual,Integer colExceNum,Integer colGoodNum);

    //更新指标
    Integer updateQuota(Integer cid,Double totalAnnual,Integer colExceNum,Integer colGoodNum);

    //查出所有学院id
    List<CollegeQuota> selectCid();

    //清空
    void deleteCollegeQuota();

    //查询指定学院的优秀指标数
    Integer selectCollegeExcequotaByCid(Integer cid);

    //查询指定学院的良好指标数
    Integer selectCollegeGoodquotaByCid(Integer cid);

}
