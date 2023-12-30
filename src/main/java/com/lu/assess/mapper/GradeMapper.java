package com.lu.assess.mapper;

import com.lu.assess.pojo.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/19 16:34
 * @description:
 */
public interface GradeMapper {
    //通过被评价人的工号查询评价结果
    List<Grade> showGradeByJobNumed(Integer jobNumed);

    //选出所有被评价人的工号
    List<Grade> showJobNum(Integer cid);

    //通过工号计算员工评价的平均分
    Double empGradeAvg(@Param("job_numed") Integer jobNum);

    //通过工号计算领导评价的平均分
    Double leaderGradeAvg(@Param("job_numed") Integer jobNum);

    //插入员工评分数据
    Integer insertEmpGrade(Grade grade);

    //插入领导评分
    Integer insertLeaderGrade(Grade grade);

    //清空
    void deleteGrade();

    //计算已完成评价的人数
    Integer finishAssessNum(Integer cid);

    //计算管理员评价人数
    Integer ColFinishAssessNum(Integer cid);

}
