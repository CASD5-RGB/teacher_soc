package com.lu.assess.mapper;

import com.lu.assess.pojo.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/29 16:10
 * @description:
 */
public interface GroupMapper {
    //通过组id查看指标
    Group selectQuotaByGid(Integer gid);

    //通过cid删除小组
    Integer deleteGroupByCid(Integer cid);

    //清空所有小组
    Integer deleteAllGroup();

    //通过gid查询组
    List<Group> selectGroupByGid(Integer gid);

    //添加组
    Integer insertGroup(@Param("groupName")String group_name, Integer cid);

    //查询所有组
    List<Group> selectAllGroup();

    //通过学院编号cid查询组
    List<Group> selectGroupByCid(Integer cid);

    //通过gid更新小组指标数
    Integer updateQuotaByGid(Integer gid,@Param("groExceNum")Integer gro_exce_num,@Param("groGoodNum")Integer gro_good_num);

    //通过cid查询学院所属所有小组的优秀指标数之和
    Integer selectGroupExcequotaByCid(Integer cid);

    //通过cid查询学院所属所有小组的良好指标数之和
    Integer selectGroupGoodquotaByCid(Integer cid);

    void testInsert_1(int i);

    void testInsert_2(int i);

    //通过cid和groupName查找gid
    Integer selectGidByCidAndGroupName(Integer cid,@Param("groupName")String group_name);

    //通过gid查询人数
    Integer selectNumByGid(Integer gid);

    //通过gid查询人数
    Integer selectNumByCidAndRoles(Integer cid,String roles);

    //通过cid查询人数
    Integer selectNumByCid(Integer cid);


}
