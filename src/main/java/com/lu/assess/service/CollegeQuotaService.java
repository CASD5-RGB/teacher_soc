package com.lu.assess.service;

import com.lu.assess.pojo.CollegeQuota;

/**
 * @author: helu
 * @date: 2022/7/18 19:19
 * @description:
 */
public interface CollegeQuotaService {
    void insertAndUpdateQuota(CollegeQuota collegeQuota);

    CollegeQuota selectQuotaByCid(Integer cid);

    void deleteCollegeQuota();

    //查询指定学院的优秀指标数
    Integer selectCollegeExcequotaByCid(Integer cid);

    //查询指定学院的良好指标数
    Integer selectCollegeGoodquotaByCid(Integer cid);
}
