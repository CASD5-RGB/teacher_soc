package com.lu.assess.mapper;

import com.lu.assess.pojo.Depart;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/16 14:47
 * @description:
 */
public interface DepartMapper {
    //展示所有教研室
    List<Depart> selectDepart();

    List<Depart> showDepartByCid(Integer cid);

    //通过教研室名称查询
    Depart selectDepartByName(String departName);

    Integer insertDepart(Integer cid,String departName);

    Integer deleteDepartByDid(Integer did);

    Integer updateDepartByDid(Integer did,String departName);


}
