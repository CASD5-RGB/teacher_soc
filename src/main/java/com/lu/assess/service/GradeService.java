package com.lu.assess.service;

import com.lu.assess.pojo.Grade;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/19 20:20
 * @description:
 */
public interface GradeService {

    List<Grade> showGradeByJobNumed(Integer jobNumed);

    void insertEmpGrade(Grade grade);
    void insertLeaderGrade(Grade grade);

    void deleteGrade();

}
