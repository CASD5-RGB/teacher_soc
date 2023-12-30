package com.lu.assess.service.Impl;

import com.lu.assess.mapper.DepartMapper;
import com.lu.assess.mapper.EmployeeMapper;
import com.lu.assess.pojo.Depart;
import com.lu.assess.service.DepartService;
import com.lu.assess.service.ex.DeleteException;
import com.lu.assess.service.ex.DepartHaveException;
import com.lu.assess.service.ex.InsertException;
import com.lu.assess.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/16 17:36
 * @description:
 */
@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartMapper departMapper;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Depart> showDepart() {
        List<Depart> departs = departMapper.selectDepart();
        return departs;
    }

    @Override
    public List<Depart> showDepartByCid(Integer cid) {
        List<Depart> data = departMapper.showDepartByCid(cid);
        return data;
    }


    @Override
    public void insertDepart(Integer cid, String departName) {
        Depart result = departMapper.selectDepartByName(departName);
        if (result!=null){
            throw new DepartHaveException("该部门已存在");
        }
        Integer row = departMapper.insertDepart(cid, departName);
        if (row!=1){
            throw new InsertException("插入异常");
        }
    }

    @Override
    public void deleteDepart(Integer did) {
        Integer row = departMapper.deleteDepartByDid(did);
        if (row!=1){
            throw new DeleteException("删除异常");
        }
        employeeMapper.updateDepart(did);
    }

    @Override
    public void updateDepart(Integer did, String departName) {
        Integer row = departMapper.updateDepartByDid(did, departName);
        if (row!=1){
            throw new UpdateException("更新异常");
        }
    }
}
