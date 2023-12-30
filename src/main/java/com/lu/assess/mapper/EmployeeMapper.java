package com.lu.assess.mapper;

import com.lu.assess.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/14 15:57
 * @description:
 */
public interface EmployeeMapper {
    //增加用户
    Integer insertEmployee(Employee employee);

    //查询用户
    Employee findEmployeeByJobNum(Integer JobNum);

    //删除用户
    Integer deleteEmployeeByEid(Integer eid);
    Employee findByEid(Integer eid);

    //展示所有用户信息
    List<Employee> showEmployeeInfo();

    //更该员工信息
    Integer updateEmployeeInfo(Integer eid,Integer jobNum,String username,Integer sex,Integer cid,Integer did,String roles);

    //员工评分，根据eid修改comEva
    Integer updateComEva(Integer eid,Integer comEva);

    //员工评分时展示员工
    List<Employee> showEmployeeByGid(Integer gid);

    //领导评分时展示员工
    List<Employee> showEmployeeByCid(Integer cid);

    //修改密码
    Integer updatePassword(String password, Integer eid);

    //更新字段
    void updateParticipateAndComEvaAndGid();

    //更新小组信息 用户分组
    Integer updateGidByEid(Integer eid, Integer gid);

    //读取指定小组人数
    Integer selectEmpCountByGid(Integer gid);

    //通过cid读取人员信息
    List<Employee> selectEmployeeByCid(Integer cid);

    //上传不参加评价人员名单
    Integer uploadNotParticipateEids(@Param("eids") Integer[] eids);

    //上传参加评价人员名单
    Integer uploadParticipateEids(@Param("eids") Integer[] eids);

    //通过cid查询参加考核的人员信息
    List<Employee> selectParticipateEmpolyeeByCid(Integer cid);

    //通过cid展示参与评价人员的姓名、所在教研室
    List<Employee> showUsernameAndDepartNameByCid(Integer cid);

    //上传人员分组情况
    Integer uploadEidsByGid(Integer gid, @Param("eids") Integer[] eids);

    //通过eid查看是否完成计算
    Integer showWhetherCount(Integer eid);

    //若完成计算更新count为0
    Integer updateCount(Integer eid);

    //置空数据时更新count为1
    Integer updateCountOne();

    //计算参加评价的人数
    Integer countNum(Integer cid);

    //展示完成评价的人员
    List<Employee> showComEvaZero(Integer cid);
    //展示未完成评价的人员
    List<Employee> showComEvaOne(Integer cid);

    //清空对应学院的小组信息
    Integer cleanGidByCid(Integer cid);

    void updateDepart(Integer did);

    //通过cid选出角色为med的人员
    List<Employee> selectParticipateEmployeeByCidAndRolesMed(Integer cid);

    //通过cid选出角色为sub的人员
    List<Employee> selectParticipateEmployeeByCidAndRolesSub(Integer cid);

    //查询所有数据，并将数据中password的值转换成密文
    List<Employee> getAll();

    //根据eid更新password数据
    void update(Integer eid, String encrypt);

    //通过eid查gid
    Integer selectGidByEid(Integer eid);


}
