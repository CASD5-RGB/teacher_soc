package com.lu.assess.service;

import com.lu.assess.pojo.Depart;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/16 17:35
 * @description:
 */
public interface DepartService {
    List<Depart> showDepart();

    List<Depart> showDepartByCid(Integer cid);

    void insertDepart(Integer cid,String departName);

    void deleteDepart(Integer did);

    void updateDepart(Integer did,String departName);
}
