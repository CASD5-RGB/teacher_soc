package com.lu.assess.service;

import com.lu.assess.pojo.Score;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/26 14:32
 * @description:
 */
public interface ScoreService {

    void insertFirst(Integer cid,Integer eid);

    void updateYearPerByJobNum(Integer cid);

    void updateHierByJobNum(Integer cid);

    List<Score> showAssessResult(Integer cid);

    void deleteScore();
}
