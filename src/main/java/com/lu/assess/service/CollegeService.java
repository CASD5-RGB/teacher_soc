package com.lu.assess.service;

import com.lu.assess.pojo.College;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/16 17:30
 * @description:
 */
public interface CollegeService {
    List<College> showCollege();
    College findCollegeByCid(Integer cid);

    void insertCollege(String collegeName);

    void deleteCollegeByCid(Integer cid);

    void updateCollegeByCid(Integer cid,String collegeName);

    //查找未分配指标学院
    List<College> findNotCollegeQuota();
}
