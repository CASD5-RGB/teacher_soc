package com.lu.assess.service;

import com.lu.assess.pojo.Employee;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/14 17:20
 * @description:
 */
public interface EmployeeService {

    //登录
    Employee login(Integer jobNum,String password);

    //插入用户数据
    void insertEmployee(Employee employee);

    //删除用户
    void deleteEmployee(Integer eid);

    //展示员工
    List<Employee> showEmployeeInfo();

    //通过eid查询用户
    Employee findEmployeeByEid(Integer eid);

    //修改员工信息
    void updateEmployeeInfo(Integer eid,Integer jobNum,String username,Integer sex,Integer cid,Integer did,String roles);

    void updateComEva(Integer eid,Integer comEva);

    List<Employee> showEmployeeByGid(Integer gid);

    List<Employee> showEmployeeByCid(Integer cid);

    void updatePassword(Integer eid,String oldPassword,String newPassword,String reNewPassword);

    void updateParticipateAndComEvaAndGid();

    //更新小组信息 用户分组
    void updateGidByEid(Integer eid, Integer gid);

    //读取指定小组人数
    Integer selectEmpCountByGid(Integer gid);

    //通过cid读取人员信息
    List<Employee> selectEmployeeByCid(Integer cid);

    //上传不参加打卡人员名单
    Integer uploadNotParticipateEids(Integer[] allEids, Integer[] eids);

    //通过cid查询参加考核的人员信息
    List<Employee> selectParticipateEmpolyeeByCid(Integer cid);

    List<Employee> showUsernameAndDepartNameByCid(Integer cid);

    //上传人员分组情况
    Integer uploadEidsByGid(Integer gid, Integer[] eids);

    //通过eid查看是否完成评分
    void showWhetherCount(Integer eid);

    //更新count值
    void updateCount(Integer eid);

    void updateCountOne();

    List<Employee> showComEvaZero(Integer cid);
    List<Employee> showComEvaOne(Integer cid);

    List<Employee> selectParticipateEmployeeByCidAndRolesMed(Integer cid);
    List<Employee> selectParticipateEmployeeByCidAndRolesSub(Integer cid);

    //通过eid查gid
    Integer selectGidByEid(Integer eid);

}
