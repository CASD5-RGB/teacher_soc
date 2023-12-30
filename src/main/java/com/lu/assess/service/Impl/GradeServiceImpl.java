package com.lu.assess.service.Impl;

import com.lu.assess.mapper.GradeMapper;
import com.lu.assess.pojo.Grade;
import com.lu.assess.service.GradeService;
import com.lu.assess.service.ex.DeleteException;
import com.lu.assess.service.ex.InsertException;
import com.lu.assess.service.ex.NotGradeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/19 20:23
 * @description:
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Override
    public List<Grade> showGradeByJobNumed(Integer jobNumed) {
        List<Grade> grades = gradeMapper.showGradeByJobNumed(jobNumed);
        if (grades==null){
            throw new NotGradeException("还未被评价");
        }
        return grades;
    }

    @Override
    public void insertEmpGrade(Grade grade) {
//        Grade grade1 = new Grade();
//        grade1.setJobNumed(grade.getJobNumed());
//        grade1.setJobNum(grade.getJobNum());
//        grade1.setUsername(grade.getUsername());
//        grade1.setRoles(grade.getRoles());
        Integer row = gradeMapper.insertEmpGrade(grade);
        if (row!=1){
            throw new InsertException("插入异常");
        }

    }

    @Override
    public void insertLeaderGrade(Grade grade) {
        Integer row = gradeMapper.insertLeaderGrade(grade);
        if (row!=1){
            throw new InsertException("插入异常");
        }
    }

    @Override
    public void deleteGrade() {
        gradeMapper.deleteGrade();

    }



}
