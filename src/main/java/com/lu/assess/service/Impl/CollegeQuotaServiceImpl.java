package com.lu.assess.service.Impl;

import com.lu.assess.mapper.CollegeQuotaMapper;
import com.lu.assess.pojo.CollegeQuota;
import com.lu.assess.service.CollegeQuotaService;
import com.lu.assess.service.ex.InsertException;
import com.lu.assess.service.ex.SelectException;
import com.lu.assess.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: helu
 * @date: 2022/7/18 19:21
 * @description:
 */
@Service
public class CollegeQuotaServiceImpl implements CollegeQuotaService {
    @Autowired
    private CollegeQuotaMapper collegeQuotaMapper;
    @Override
    public void insertAndUpdateQuota(CollegeQuota collegeQuota) {
        CollegeQuota result = collegeQuotaMapper.selectQuotaByCid(collegeQuota.getCid());
        if (result==null){
            Integer row = collegeQuotaMapper.insertQuota(collegeQuota.getCid(),collegeQuota.getTotalAnnual(),
                    collegeQuota.getColExceNum(),collegeQuota.getColGoodNum());
            if (row!=1){
                throw new InsertException("插入异常");
            }
        }
        Integer row1 = collegeQuotaMapper.updateQuota(collegeQuota.getCid(),collegeQuota.getTotalAnnual(),
                collegeQuota.getColExceNum(),collegeQuota.getColGoodNum());
        if (row1!=1){
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public CollegeQuota selectQuotaByCid(Integer cid) {
        CollegeQuota collegeQuota = collegeQuotaMapper.selectQuotaByCid(cid);

        return collegeQuota;
    }

    @Override
    public void deleteCollegeQuota() {
        collegeQuotaMapper.deleteCollegeQuota();
    }

    @Override
    public Integer selectCollegeExcequotaByCid(Integer cid) {
        Integer CollegeExcequota = collegeQuotaMapper.selectCollegeExcequotaByCid(cid);
        if(CollegeExcequota!=null)return CollegeExcequota;
        else throw new SelectException("查询学院优秀指标数失败");
    }

    @Override
    public Integer selectCollegeGoodquotaByCid(Integer cid) {
        Integer CollegeGoodquota = collegeQuotaMapper.selectCollegeGoodquotaByCid(cid);
        if(CollegeGoodquota!=null)return CollegeGoodquota;
        else throw new SelectException("查询学院良好指标数失败");
    }
}
