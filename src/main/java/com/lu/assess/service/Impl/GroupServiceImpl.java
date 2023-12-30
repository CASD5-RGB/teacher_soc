package com.lu.assess.service.Impl;

import com.lu.assess.mapper.CollegeQuotaMapper;
import com.lu.assess.mapper.EmployeeMapper;
import com.lu.assess.mapper.GroupMapper;
import com.lu.assess.pojo.Group;
import com.lu.assess.service.EmployeeService;
import com.lu.assess.service.GroupService;
import com.lu.assess.service.ex.DeleteException;
import com.lu.assess.service.ex.SelectException;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/29 16:30
 * @description:
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private CollegeQuotaMapper collegeQuotaMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Group selectQuotaByGid(Integer gid) {
        Group group = groupMapper.selectQuotaByGid(gid);
        if (group ==null){
            throw new SelectException("查询异常");
        }
        return group;
    }

    @Override
    public Integer deleteGroupByCid(Integer cid) {
        if(cid!=null){
            //删除本学院小组并清空本学院人员的分组信息
            employeeMapper.cleanGidByCid(cid);
            return groupMapper.deleteGroupByCid(cid);
        } else {
            //cid为空时删除所有小组
            return groupMapper.deleteAllGroup();
        }

    }

    @Override
    public List<Group> selectGroupByGid(Integer gid) {
        List<Group> groups = groupMapper.selectGroupByGid(gid);
        if(groups!=null)return groups;
        else throw new SelectException("通过小组编号查询组失败");
    }

    @Override
    public Integer insertGroup(String group_name,Integer cid) {
        return groupMapper.insertGroup(group_name,cid);
    }

    @Override
    public List<Group> selectAllGroup() {
        List<Group> groups = groupMapper.selectAllGroup();
        if(groups!=null)return groups;
        else throw new SelectException("查询所有组失败");
    }

    @Override
    public List<Group> selectGroupByCid(Integer cid) {
        List<Group> groups = groupMapper.selectGroupByCid(cid);
        if(groups!=null)return groups;
        else throw new SelectException("通过学院编号查询组失败");
    }

    @Override
    public Integer updateQuotaByGid(Integer gid, Integer groExceNum, Integer groGoodNNum) {
        return groupMapper.updateQuotaByGid(gid,groExceNum,groGoodNNum);
    }

    @Override
    public Integer selectRemainderExcequotaByCid(Integer cid) {
        return collegeQuotaMapper.selectCollegeExcequotaByCid(cid) - groupMapper.selectGroupExcequotaByCid(cid);
    }

    @Override
    public Integer selectRemainderGoodquotaByCid(Integer cid) {
        return collegeQuotaMapper.selectCollegeGoodquotaByCid(cid) - groupMapper.selectGroupGoodquotaByCid(cid);
    }

    @Override
    public void testInsert_1() {
        groupMapper.testInsert_1(180);
    }

    @Override
    public void testInsert_2() {
//        throw new RuntimeException("测试事务是否生效");
        groupMapper.testInsert_2(181);
    }

    @Override
    //通过cid和groupName查找gid
    public Integer selectGidByCidAndGroupName(Integer cid, @Param("groupName")String group_name){
        Integer gid = groupMapper.selectGidByCidAndGroupName(cid,group_name);
        if(gid==null){
            throw new SelectException("通过cid和groupName查找gid失败");
        }
        return gid;
    }

    @Override
    public Integer CountNumForUser(Integer gid,Integer cid) {
        Integer num1 = groupMapper.selectNumByGid(gid);
        Integer num2 = groupMapper.selectNumByCidAndRoles(cid, "sub");
        return num1+num2;
    }

    @Override
    public Integer CountNumForAll(Integer cid) {

        return groupMapper.selectNumByCid(cid);
    }
}
