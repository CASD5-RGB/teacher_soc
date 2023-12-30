package com.lu.assess.service.Impl;

import com.lu.assess.mapper.CollegeMapper;
import com.lu.assess.mapper.CollegeQuotaMapper;
import com.lu.assess.pojo.College;
import com.lu.assess.pojo.CollegeQuota;
import com.lu.assess.service.CollegeService;
import com.lu.assess.service.ex.CollegeNotFoundException;
import com.lu.assess.service.ex.DeleteException;
import com.lu.assess.service.ex.InsertException;
import com.lu.assess.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/16 17:32
 * @description:
 */
@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CollegeQuotaMapper collegeQuotaMapper;

    @Override
    public List<College> showCollege() {
        List<College> colleges = collegeMapper.selectCollege();
        return colleges;
    }

    @Override
    public College findCollegeByCid(Integer cid) {
        College college = collegeMapper.findCollegeByCid(cid);
        return college;
    }

    @Override
    public void insertCollege(String collegeName) {
        Integer row = collegeMapper.insertCollege(collegeName);
        if (row!=1){
            throw new InsertException("插入异常");
        }
    }

    @Override
    public void deleteCollegeByCid(Integer cid) {
        College result = collegeMapper.findCollegeByCid(cid);
        if (result==null){
            throw new CollegeNotFoundException("该学院不存在");
        }
        Integer row = collegeMapper.deleteCollegeByCid(cid);
        if (row!=1){
            throw new DeleteException("删除异常");
        }
    }

    @Override
    public void updateCollegeByCid(Integer cid, String collegeName) {
        College result = collegeMapper.findCollegeByCid(cid);
        if (result==null){
            throw new CollegeNotFoundException("该学院不存在");
        }
        Integer row = collegeMapper.updateCollegeByCid(cid, collegeName);
        if (row!=1){
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public List<College> findNotCollegeQuota() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<College> list3 = new ArrayList<>();
        List<College> colleges = collegeMapper.selectCollege();
        List<CollegeQuota> collegeQuotas = collegeQuotaMapper.selectCid();

        for (College college : colleges) {
            list1.add(college.getCid());
        }
        for (CollegeQuota collegeQuota : collegeQuotas) {
            list2.add(collegeQuota.getCid());
        }
        list1.removeAll(list2);

        for (Integer cid : list1) {
            College collegeByCid = collegeMapper.findCollegeByCid(cid);
            list3.add(collegeByCid);
        }
        return list3;
    }


}
