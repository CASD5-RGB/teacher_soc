package com.lu.assess.service.Impl;

import com.lu.assess.mapper.CollegeQuotaMapper;
import com.lu.assess.mapper.EmployeeMapper;
import com.lu.assess.mapper.GradeMapper;
import com.lu.assess.mapper.ScoreMapper;
import com.lu.assess.pojo.CollegeQuota;
import com.lu.assess.pojo.Grade;
import com.lu.assess.pojo.Score;
import com.lu.assess.service.EmployeeService;
import com.lu.assess.service.ScoreService;
import com.lu.assess.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/26 14:33
 * @description:
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CollegeQuotaMapper collegeQuotaMapper;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void insertFirst(Integer cid,Integer eid) {
        //得到被评价人的所有工号
        List<Grade> jobNums = gradeMapper.showJobNum(cid);
        if (jobNums.isEmpty()){
            throw new NotDataException("所有员工还未评价");
        }

//        if (gradeMapper.ColFinishAssessNum(cid)!=employeeMapper.countNum(cid)-1){
//            throw new NotFinishAssessException("管理员未完成评价");
//        }
        if (employeeService.findEmployeeByEid(eid).getComEva()!=0){
            throw new NotFinishAssessException("管理员未完成评价");
        }

        if (employeeMapper.countNum(cid) != gradeMapper.finishAssessNum(cid)){
//            System.out.println(employeeMapper.countNum(cid));
//            System.out.println(gradeMapper.finishAssessNum(cid));
            throw new NotFinishAssessException("还有员工未完成评价");
        }

        for (Grade jobNum : jobNums) {
            //获取员工姓名
            String username = employeeMapper.findEmployeeByJobNum(jobNum.getJobNumed()).getUsername();
            Double empGradeAvg = gradeMapper.empGradeAvg(jobNum.getJobNumed());
            //员工互评总得分
            Double empScore = changeResult(empGradeAvg);
            //System.out.println(empScore);
            Double leaderGradeAvg = gradeMapper.leaderGradeAvg(jobNum.getJobNumed());
            //领导班子评分
            Double leaderScore = changeResult(leaderGradeAvg);
            System.out.println(leaderScore);
            //综合得分
            Double compreScore = changeResult(empScore*0.3+leaderScore*0.7);
            //插入数据
            Score score = new Score();
            score.setJobNum(jobNum.getJobNumed());
            score.setUsername(username);
            score.setEmpScore(empScore);
            score.setLeaderScore(leaderScore);
            score.setCompreScore(compreScore);
            Integer row = scoreMapper.insertFirst(score);
            if (row!=1){
                throw new InsertException("插入异常");
            }
        }

    }

    @Override
    public void updateYearPerByJobNum(Integer cid) {

        //根据学院id获取对应年度绩效总额
        Double totalAnnual = collegeQuotaMapper.selectQuotaByCid(cid).getTotalAnnual();
        //根据cid计算每个学院的综合评价得分总分
        Double sumComprtScore = scoreMapper.sumComprtScore(cid);
        //根据学院id查出每个学院员工和对应综合评价得分
        List<Score> scores = scoreMapper.selectJobNumAndCompreScore(cid);
        for (Score score : scores) {
            Integer jobNum = score.getJobNum();
            //System.out.println(jobNum);
            Double compreScore = score.getCompreScore();
            //System.out.println(compreScore);
            Double yearPer = changeResult(compreScore / sumComprtScore * totalAnnual);
            //System.out.println(yearPer);
            Integer row = scoreMapper.updateYearPerByJobNum(jobNum, yearPer);
            if (row!=1){
                throw new UpdateException("更新异常");
            }
        }

/**        List<CollegeQuota> collegeQuotas = collegeQuotaMapper.selectCid();
        for (CollegeQuota collegeQuota : collegeQuotas) {
            //获得学院cid
            Integer cid = collegeQuota.getCid();
            //根据学院id获取对应年度绩效总额
            Double totalAnnual = collegeQuotaMapper.selectQuotaByCid(cid).getTotalAnnual();
            //根据cid计算每个学院的综合评价得分总分
            Double sumComprtScore = scoreMapper.sumComprtScore(cid);
            //根据学院id查出每个学院员工和对应综合评价得分
            List<Score> scores = scoreMapper.selectJobNumAndCompreScore(cid);
            for (Score score : scores) {
                Integer jobNum = score.getJobNum();
                System.out.println(jobNum);
                Double compreScore = score.getCompreScore();
                System.out.println(compreScore);
                Double yearPer = changeResult(compreScore / sumComprtScore * totalAnnual);
                System.out.println(yearPer);
                Integer row = scoreMapper.updateYearPerByJobNum(jobNum, yearPer);
                if (row!=1){
                    throw new UpdateException("更新异常");
                }
            }
        }**/
    }

    @Override
    public void updateHierByJobNum(Integer cid) {

        //通过cid查询指标
        CollegeQuota collegeQuota1 = collegeQuotaMapper.selectQuotaByCid(cid);
        //获取学院优秀指标
        Integer colExceNum = collegeQuota1.getColExceNum();
        //获取学院良好指标
        Integer colGoodNum = collegeQuota1.getColGoodNum();;
        //根据学院id选出工号，综合评价得分，降序
        List<Score> scores = scoreMapper.selectJobNumAndCompreScore(cid);
        for(int i = 0; i<scores.size();i++){
            Integer jobNum = scores.get(i).getJobNum();
            if (i<colExceNum){
                Integer row = scoreMapper.updateHierByJobNum(jobNum, 0);
                if (row!=1){
                    throw new InsertException("插入异常");
                }
            }
            else if (i<(colExceNum+colGoodNum)){
                Integer row = scoreMapper.updateHierByJobNum(jobNum, 1);
                if (row!=1){
                    throw new InsertException("插入异常");
                }
            }
            else {
                if (scores.get(i).getCompreScore()<60){
                    Integer row = scoreMapper.updateHierByJobNum(jobNum, 3);
                    if (row!=1){
                        throw new InsertException("插入异常");
                    }
                }
                Integer row = scoreMapper.updateHierByJobNum(jobNum, 2);
                if (row!=1){
                    throw new InsertException("插入异常");
                }
            }
        }

/**        List<CollegeQuota> collegeQuotas = collegeQuotaMapper.selectCid();
        for (CollegeQuota collegeQuota : collegeQuotas) {
            //获取学院id
            Integer cid = collegeQuota.getCid();
            //通过cid查询指标
            CollegeQuota collegeQuota1 = collegeQuotaMapper.selectQuotaByCid(cid);
            //获取学院优秀指标
            Integer colExceNum = collegeQuota1.getColExceNum();
            //获取学院良好指标
            Integer colGoodNum = collegeQuota1.getColGoodNum();;
            //根据学院id选出工号，综合评价得分，降序
            List<Score> scores = scoreMapper.selectJobNumAndCompreScore(cid);
            for(int i = 0; i<scores.size();i++){
                Integer jobNum = scores.get(i).getJobNum();
                if (i<colExceNum){
                    Integer row = scoreMapper.updateHierByJobNum(jobNum, 0);
                    if (row!=1){
                        throw new InsertException("插入异常");
                    }
                }
                else if (i<(colExceNum+colGoodNum)){
                    Integer row = scoreMapper.updateHierByJobNum(jobNum, 1);
                    if (row!=1){
                        throw new InsertException("插入异常");
                    }
                }
                else {
                    if (scores.get(i).getCompreScore()<60){
                        Integer row = scoreMapper.updateHierByJobNum(jobNum, 3);
                        if (row!=1){
                            throw new InsertException("插入异常");
                        }
                    }

                    Integer row = scoreMapper.updateHierByJobNum(jobNum, 2);
                    if (row!=1){
                        throw new InsertException("插入异常");
                    }
                }
            }

        }**/
    }

    @Override
    public List<Score> showAssessResult(Integer cid) {
        List<Score> scores = scoreMapper.showAssessResult(cid);
        if (scores==null){
            throw new SelectException("查询异常");
        }
        return scores;
    }

    @Override
    public void deleteScore() {
        scoreMapper.deleteScore();
    }

    //结果保留两位小数
    private Double changeResult(Double num){
        String  str = String.format("%.2f",num);
        Double result = Double.parseDouble(str);
        return result;
    }
}
