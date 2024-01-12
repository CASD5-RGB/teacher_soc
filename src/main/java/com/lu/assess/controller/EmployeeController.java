package com.lu.assess.controller;

import com.lu.assess.pojo.College;
import com.lu.assess.pojo.Depart;
import com.lu.assess.pojo.Employee;
import com.lu.assess.service.*;
import com.lu.assess.until.EncryptUtil;
import com.lu.assess.until.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: helu
 * @date: 2022/7/14 18:07
 * @description:
 */
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/employee")
@CrossOrigin
@Api(tags = "员工管理")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private DepartService departService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CollegeQuotaService collegeQuotaService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/login")
    public JsonResult<Employee> login(Integer jobNum, String password, HttpSession session) {
        Employee data = employeeService.login(jobNum, password);
        session.setAttribute("eid", data.getEid());
        session.setAttribute("jobNum", data.getJobNum());
        session.setAttribute("username", data.getUsername());
        session.setAttribute("roles", data.getRoles());
        session.setAttribute("cid", data.getCid());
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/logout")
    public JsonResult<Void> logout(HttpSession session) {
        session.removeAttribute("eid");
        session.removeAttribute("jobNum");
        session.removeAttribute("username");
        session.removeAttribute("roles");
        session.removeAttribute("cid");
        return new JsonResult<>(OK);
    }


    @RequestMapping("/show_col_dep")
    public JsonResult<Map<String, Object>> showColAndDep() {
        HashMap<String, Object> map = new HashMap<>();
        List<College> colleges = collegeService.showCollege();
        List<Depart> departs = departService.showDepart();
        map.put("college", colleges);
        map.put("depart", departs);
        return new JsonResult<>(OK, map);

    }

    @RequestMapping("/add_emp")
    public JsonResult<Void> insertEmployee(@Validated Employee employee) {

        // 对密码进行加密处理
        String encrypt = EncryptUtil.encrypt(employee.getPassword());
        employee.setPassword(encrypt);
        employeeService.insertEmployee(employee);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/delete_emp")
    public JsonResult<Void> deleteEmployee(Integer eid) {
        employeeService.deleteEmployee(eid);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/select_eid")
    public JsonResult<Employee> findEmployeeByEid(Integer eid) {
        Employee data = employeeService.findEmployeeByEid(eid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/show_emp")
    @Operation(summary = "显示所有员工信息")
    public JsonResult<List<Employee>> showEmployeeInfo() {
        List<Employee> data = employeeService.showEmployeeInfo();
        return new JsonResult<List<Employee>>(OK, data);
    }

    @RequestMapping("/update_emp")
    public JsonResult<Void> updateEmployeeInfo(Integer eid, Integer jobNum, String username, Integer sex, Integer cid, Integer did, String roles) {
        employeeService.updateEmployeeInfo(eid, jobNum, username, sex, cid, did, roles);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/update_password")
    public JsonResult<Void> updatePassword(HttpSession session, String oldPassword, String newPassword, String reNewPassword) {
        employeeService.updatePassword(getEidFromSession(session), oldPassword, newPassword, reNewPassword);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/update_delete")
    public JsonResult<Void> updateAndDelete(HttpSession session) {
        Integer eid = getEidFromSession(session);
        groupService.deleteGroupByCid(null);
        gradeService.deleteGrade();
        scoreService.deleteScore();
        collegeQuotaService.deleteCollegeQuota();
        employeeService.updateParticipateAndComEvaAndGid();
        employeeService.updateCountOne();
        return new JsonResult<>(OK);
    }

    // 读取指定小组人数
    @RequestMapping("/selectEmpCountByGid")
    public JsonResult<Integer> selectEmpCountByGid(@RequestParam("gid") Integer gid) {
        Integer num = employeeService.selectEmpCountByGid(gid);
        return new JsonResult<>(OK, num);
    }

    // 通过cid读取人员信息
    @RequestMapping("/selectEmployeeByCid")
    public JsonResult<List<Employee>> selectEmployeeByCid(@RequestParam("cid") Integer cid) {
        List<Employee> employees = employeeService.selectEmployeeByCid(cid);
        return new JsonResult<>(OK, employees);
    }

    //上传不参加打卡人员名单
    @RequestMapping("/uploadNotParticipateEids")
    public JsonResult<Integer> uploadNotParticipateEids(Integer[] allEids, Integer[] eids) {
        Integer result = employeeService.uploadNotParticipateEids(allEids, eids);
        return new JsonResult<>(OK, result);
    }

    //通过cid查询参加考核的人员信息
    @RequestMapping("/selectParticipateEmpolyeeByCid")
    public JsonResult<List<Employee>> selectParticipateEmpolyeeByCid(@RequestParam("cid") Integer cid) {
        List<Employee> employees = employeeService.selectParticipateEmpolyeeByCid(cid);
        return new JsonResult<>(OK, employees);
    }


    //上传人员分组情况
    @RequestMapping("/uploadEidsByGid")
    public JsonResult<Integer> uploadEidsByGid(@RequestParam("gid") Integer gid, Integer[] eids) {
        Integer result = employeeService.uploadEidsByGid(gid, eids);
        return new JsonResult<>(OK, result);
    }

    @RequestMapping("/showUserNameAndDepartNameByCid")
    public JsonResult<List<Employee>> showUserNameAndDepartNameByCid(Integer cid) {
        List<Employee> employees = employeeService.showUsernameAndDepartNameByCid(cid);
        return new JsonResult<>(OK, employees);
    }

    @RequestMapping("/showComEvaZero")
    public JsonResult<List<Employee>> showComEvaZero(Integer cid) {
        //Integer cid = getCidFromSession(session);
        List<Employee> data = employeeService.showComEvaZero(cid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/showComEvaOne")
    public JsonResult<List<Employee>> showComEvaOne(Integer cid) {
        //Integer cid = getCidFromSession(session);
        List<Employee> data = employeeService.showComEvaOne(cid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/showEmployeeByGid")
    public JsonResult<List<Employee>> showEmployeeByGid(Integer gid) {
        List<Employee> data = employeeService.showEmployeeByGid(gid);
        return new JsonResult<>(OK, data);
    }
}
