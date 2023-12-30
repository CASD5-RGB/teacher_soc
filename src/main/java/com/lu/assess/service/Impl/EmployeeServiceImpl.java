package com.lu.assess.service.Impl;

import com.lu.assess.controller.ex.FinishException;
import com.lu.assess.mapper.EmployeeMapper;
import com.lu.assess.mapper.GroupMapper;
import com.lu.assess.pojo.Employee;
import com.lu.assess.pojo.Group;
import com.lu.assess.service.EmployeeService;
import com.lu.assess.service.ex.*;
import com.lu.assess.until.EncryptUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: helu
 * @date: 2022/7/14 17:24
 * @description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Employee login(Integer jobNum, String password) {
        //从数据库中获取信息
        Employee result = employeeMapper.findEmployeeByJobNum(jobNum);
        if (result == null) {
            throw new EmployeeNotFoundException("该用户不存在");
        }
        //将数据库中的密码进行解密处理，decryptPassword解密之后的密码
        String decryptPassword = EncryptUtil.decrypt(result.getPassword());
        if (!password.equals(decryptPassword)) {
            throw new PasswordNotMatchException("密码错误");
        }
        Employee employee = new Employee();
        employee.setEid(result.getEid());
        employee.setJobNum(result.getJobNum());
        employee.setUsername(result.getUsername());
        employee.setRoles(result.getRoles());
        employee.setCid(result.getCid());
        return employee;
    }

    @Override
    public void insertEmployee(Employee employee) {
        Employee result = employeeMapper.findEmployeeByJobNum(employee.getJobNum());
        if (result != null) {
            throw new JobNumDuplicateException("工号已被占用");
        }
        employee.setComEva(1);
        employee.setDid(1);
        employee.setGid(0);
        employee.setParticipate(0);
        String roles = employee.getRoles();
        if (roles.equals("colAdmin")) {
            employee.setCount(1);
        } else {
            employee.setCount(null);
        }
        Integer row = employeeMapper.insertEmployee(employee);
        if (row != 1) {
            throw new InsertException("插入异常");
        }

    }

    @Override
    public void deleteEmployee(Integer eid) {
        Employee result = employeeMapper.findByEid(eid);
        if (result == null) {
            throw new EmployeeNotFoundException("该员工不存在");
        }
        Integer row = employeeMapper.deleteEmployeeByEid(eid);
        if (row != 1) {
            throw new DeleteException("删除异常");
        }
    }

    @Override
    public List<Employee> showEmployeeInfo() {
        List<Employee> employees = employeeMapper.showEmployeeInfo();
        for (Employee employee : employees) {
            employee.setPassword(null);
        }
        return employees;
    }

    @Override
    public Employee findEmployeeByEid(Integer eid) {
        Employee result = employeeMapper.findByEid(eid);
        if (result == null) {
            throw new EmployeeNotFoundException("该员工不存在");
        }
//        result.setPassword(null);
//        result.setRoles(null);
        return result;
    }

    @Override
    public void updateEmployeeInfo(Integer eid, Integer jobNum, String username, Integer sex, Integer cid, Integer did, String roles) {
        //Integer eid,Integer jobNum,String username,Integer sex,Integer cid,Integer did
        Employee result = employeeMapper.findByEid(eid);
        if (result == null) {
            throw new EmployeeNotFoundException("员工不存在");
        }

        Integer row = employeeMapper.updateEmployeeInfo(eid, jobNum, username, sex, cid, did, roles);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }

    }

    @Override
    public void updateComEva(Integer eid, Integer comEva) {
        Integer row = employeeMapper.updateComEva(eid, comEva);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public List<Employee> showEmployeeByGid(Integer gid) {
        List<Employee> employees = employeeMapper.showEmployeeByGid(gid);
        return employees;
    }

    @Override
    public List<Employee> showEmployeeByCid(Integer cid) {
        List<Employee> employees = employeeMapper.showEmployeeByCid(cid);
        return employees;
    }

    @Override
    public void updatePassword(Integer eid, String oldPassword, String newPassword, String reNewPassword) {
        // 从DB中获取信息
        Employee result = employeeMapper.findByEid(eid);
        if (result == null) {
            throw new EmployeeNotFoundException("用户不存在");
        }
        // 解密之后的密码
        String decrypt = EncryptUtil.decrypt(result.getPassword());
        if (!Objects.equals(decrypt, oldPassword)) {
            throw new PasswordNotMatchException("原密码有误");
        }
        if (!newPassword.equals(reNewPassword)) {
            throw new NewPasswordException("请再次确认新密码");
        }
        if (Objects.equals(decrypt, newPassword)) {
            throw new EqualsPasswordException("新密码与原密码相同");
        }
        // 将新密码加密之后，修改数据库信息
        Integer row = employeeMapper.updatePassword(EncryptUtil.encrypt(newPassword), eid);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public void updateParticipateAndComEvaAndGid() {
        employeeMapper.updateParticipateAndComEvaAndGid();
    }

    @Override
    public void updateGidByEid(Integer eid, Integer gid) {
        Employee result = employeeMapper.findByEid(eid);
        if (result == null) {
            throw new EmployeeNotFoundException("员工不存在");
        }
        List<Group> group = groupMapper.selectGroupByGid(gid);
        if (group == null) {
            throw new GroupNotFoundException("小组不存在");
        }
        Integer row = employeeMapper.updateGidByEid(eid, gid);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public Integer selectEmpCountByGid(Integer gid) {
        Integer empCount = employeeMapper.selectEmpCountByGid(gid);
        if (empCount != null) {
            return empCount;
        } else {
            throw new SelectException("查询人数失败");
        }
    }

    @Override
    public List<Employee> selectEmployeeByCid(Integer cid) {
        List<Employee> employees = employeeMapper.selectEmployeeByCid(cid);
        if (employees != null) {
            return employees;
        } else {
            throw new SelectException("通过学院编号查询用户失败");
        }
    }

    @Override
    public Integer uploadNotParticipateEids(Integer[] allEids, Integer[] eids) {
//        for (Integer eid : eids) {
//            System.out.println("\neid:"+eid);
//        }
        Integer res = employeeMapper.uploadParticipateEids(allEids);
        if (eids != null) {
            res = employeeMapper.uploadNotParticipateEids(eids);
        }
        if (res != null) {
            return res;
        } else {
            throw new UpdateException("上传不参加打卡人员名单失败");
        }
    }

    @Override
    public List<Employee> selectParticipateEmpolyeeByCid(Integer cid) {
        List<Employee> employees = employeeMapper.selectParticipateEmpolyeeByCid(cid);
        if (employees != null) {
            for (Employee employee : employees) {
                employee.setPassword(null);
            }
            return employees;
        } else {
            throw new SelectException("通过学院编号查询参加考核的用户失败");
        }
    }

    @Override
    public List<Employee> showUsernameAndDepartNameByCid(Integer cid) {
        List<Employee> employees = employeeMapper.showUsernameAndDepartNameByCid(cid);
        if (employees == null) {
            throw new SelectException("查询异常");
        }
        return employees;
    }

    @Override
    public Integer uploadEidsByGid(Integer gid, Integer[] eids) {
        Integer r = employeeMapper.uploadEidsByGid(gid, eids);
        Integer length20 = Math.toIntExact(Math.round(0.2 * employeeMapper.selectEmpCountByGid(gid)));
        if (length20 == 0) {
            groupMapper.updateQuotaByGid(gid, 1, 1);
        }
        groupMapper.updateQuotaByGid(gid, length20, length20);
        if (r != null) {
            return r;
        } else {
            throw new UpdateException("上传人员分组情况失败");
        }
    }

    @Override
    public void showWhetherCount(Integer eid) {
        Integer result = employeeMapper.showWhetherCount(eid);
        if (result == 0) {
            throw new FinishException("请勿重复计算结果");
        }
    }

    @Override
    public void updateCount(Integer eid) {
        Integer result = employeeMapper.updateCount(eid);
        if (result != 1) {
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public void updateCountOne() {
        Integer row = employeeMapper.updateCountOne();
        if (row == 0) {
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public List<Employee> showComEvaZero(Integer cid) {
        List<Employee> employees = employeeMapper.showComEvaZero(cid);
        if (employees == null) {
            throw new NotDataException("暂无数据");
        }
        return employees;
    }

    @Override
    public List<Employee> showComEvaOne(Integer cid) {
        List<Employee> employees = employeeMapper.showComEvaOne(cid);
        return employees;
    }

    @Override
    public List<Employee> selectParticipateEmployeeByCidAndRolesMed(Integer cid) {
        List<Employee> result = employeeMapper.selectParticipateEmployeeByCidAndRolesMed(cid);
        if (result == null) {
            throw new SelectException("该单位没有中层干部");
        }
        return result;
    }

    @Override
    public List<Employee> selectParticipateEmployeeByCidAndRolesSub(Integer cid) {
        List<Employee> result = employeeMapper.selectParticipateEmployeeByCidAndRolesSub(cid);
        if (result == null) {
            throw new SelectException("该单位没有中层干部");
        }
        return result;
    }

    @Override
    public Integer selectGidByEid(Integer eid) {
        return employeeMapper.selectGidByEid(eid);
    }


}
