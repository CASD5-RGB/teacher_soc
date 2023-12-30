package com.lu.assess.mapper;

import com.lu.assess.pojo.Score;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/25 18:48
 * @description:
 */
public interface ScoreMapper {

    //插入员工工号、姓名、员工互评得分、单位班子评价得分、综合评价得分
    Integer insertFirst(Score score);

    //通过学院id求每个学院员工综合评价得分总分
    Double sumComprtScore(Integer cid);

    //通过学院id查出员工id及对应compre_score
    List<Score> selectJobNumAndCompreScore(Integer cid);

    //根据工号插入个人年终绩效总额
    Integer updateYearPerByJobNum(Integer jobNum,Double yearPer);

    //根据学院id插入考核评价等次
    Integer updateHierByJobNum(Integer jobNum,Integer hier);

    //根据学院id展示对应评价情况
    List<Score> showAssessResult(Integer cid);

    //清空
    void deleteScore();
}
