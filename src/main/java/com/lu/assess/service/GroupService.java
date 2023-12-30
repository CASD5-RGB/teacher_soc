package com.lu.assess.service;

import com.lu.assess.pojo.Group;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/29 16:29
 * @description:
 */
public interface GroupService {
    Group selectQuotaByGid(Integer gid);

    //通过cid删除本学院小组并清空本学院人员的分组信息，cid为空时删除所有小组
    Integer deleteGroupByCid(Integer cid);

    //通过gid查询组
    List<Group> selectGroupByGid(Integer gid);

    //添加组
    Integer insertGroup(String group_name,Integer cid);

    //查询所有组
    List<Group> selectAllGroup();

    //通过学院编号cid查询组
    List<Group> selectGroupByCid(Integer cid);

    //通过gid更新小组指标数
    Integer updateQuotaByGid(Integer gid,Integer gro_exce_num,Integer gro_good_num);

    //通过cid查询学院剩余优秀指标数
    Integer selectRemainderExcequotaByCid(Integer cid);

    //通过cid查询学院剩余良好指标数
    Integer selectRemainderGoodquotaByCid(Integer cid);

    void testInsert_1();

    void testInsert_2();

    //通过cid和groupName查找gid
    Integer selectGidByCidAndGroupName(Integer cid,@Param("groupName")String group_name);

    //普通用户评价时查看评价总人数
    Integer CountNumForUser(Integer gid,Integer cid);

    //中层 基层 领导班子需评价人数
    Integer CountNumForAll(Integer cid);
}
