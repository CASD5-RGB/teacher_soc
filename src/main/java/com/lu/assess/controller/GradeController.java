package com.lu.assess.controller;

import com.lu.assess.controller.ex.CommitErrorException;
import com.lu.assess.controller.ex.FinishException;
import com.lu.assess.controller.ex.GradeExcption;
import com.lu.assess.controller.ex.NotFinishException;
import com.lu.assess.pojo.*;
import com.lu.assess.service.*;
import com.lu.assess.until.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: helu
 * @date: 2022/7/19 20:39
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/grade")
@CrossOrigin
public class GradeController extends BaseController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CollegeQuotaService collegeQuotaService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/show_grade")
    public JsonResult<List<Grade>> showGradeByJobNumed(Integer jobNumed) {
        List<Grade> data = gradeService.showGradeByJobNumed(jobNumed);
        return new JsonResult<>(OK, data);
    }

    //员工之间互评提交接口
    @Transactional
    @PostMapping("/insert_grade")
    public JsonResult<Void> insertEmpAndLeaderGrade(@RequestBody Vo vo) {

        List<Centre> lists = vo.getLists();
        Employee employee = vo.getEmployee();
        Integer eid = employee.getEid();

//        Integer eid = getEidFromSession(session);
        Integer comEva = employeeService.findEmployeeByEid(eid).getComEva();

        if (lists.isEmpty()) {
            throw new CommitErrorException("提交失败");
        }
        //若之前已完成评价
        if (comEva == 0) {
            throw new FinishException("请勿重复提交");
        }

        //检查是否对所在小组所有人员评分
        for (Centre list : lists) {
            Integer sco = list.getSco();
            if (sco == null) {
                throw new NotFinishException("未完成评价,请勿提交");
            }
        }

        //获取评价人信息
        String role = employee.getRoles();
        String username = employee.getUsername();
        Integer jobNum = employee.getJobNum();
        Integer cid = employee.getCid();
//        String role = getRolesFromSession(session);
//        String username = getUsernameFromSession(session);
//        Integer jobNum = getJobNumFromSession(session);
//        Integer cid = getCidFromSession(session);

//        if (role.equals("user")){
//            Integer gid = employeeService.findEmployeeByEid(eid).getGid();
//            Group group = groupService.selectQuotaByGid(gid);
//            Integer groExceNum = group.getGroExceNum();
//            Integer groGoodNum = group.getGroGoodNum();
//            Integer exce = 0;
//            Integer good = 0;
//            for (Centre list : lists) {
//                Integer sco = list.getSco();
//                if (sco>90){
//                    exce++;
//                }
//                else if (sco>80){
//                    good++;
//                }
//            }
//            if (exce!=groExceNum || good!=groGoodNum){
//                throw new GradeExcption("不符合评分规则，请重新评分");
//            }
//        }

//        if (role.equals("colAdmin")){
//            Integer cid = employeeService.findEmployeeByEid(eid).getCid();
//            CollegeQuota collegeQuota = collegeQuotaService.selectQuotaByCid(cid);
//            Integer colExceNum = collegeQuota.getColExceNum();
//            Integer colGoodNum = collegeQuota.getColGoodNum();
//            Integer exce = 0;
//            Integer good = 0;
//            for (Centre list : lists) {
//                Integer sco = list.getSco();
//                if (sco==null){
//                    throw new NotFinishException("未完成评价,请勿提交");
//                }
//                if (sco>90){
//                    exce++;
//                }
//                else if (sco>80){
//                    good++;
//                }
//            }
////            if (exce!=colExceNum || good!=colGoodNum){
////                throw new GradeExcption("不符合评分规则，请重新评分");
////            }
//
//        }

        Grade grade = new Grade();
        for (Centre list : lists) {
            Integer jobNumed = list.getJobNumed();
            //分数
            Integer sco = list.getSco();

            grade.setJobNumed(jobNumed);
            grade.setJobNum(jobNum);
            grade.setUsername(username);
            grade.setRoles(role);
            grade.setCid(cid);
//            if (role.equals("user")){
//                grade.setEmpGrade(sco);
//                gradeService.insertEmpGrade(grade);
//            }
//            else if (role.equals("colAdmin")){
//                grade.setLeaderGrade(sco);
//                gradeService.insertLeaderGrade(grade);
//            }
            grade.setEmpGrade(sco);
            gradeService.insertEmpGrade(grade);

        }
        employeeService.updateComEva(eid, 0);
        return new JsonResult<>(OK);
    }

    //领导班子对普通员工评分提交接口
    //对员工组评分提交接口
    @Transactional
    @PostMapping("/insert_grade_emp")
    public JsonResult<Void> insertLeaderGradeForEmp(@RequestBody Vo vo) {

        List<Centre> lists = vo.getLists();
        int size = lists.size();

        if(vo.getEmployee().getRoles().equals("user")){

            if (size != groupService.CountNumForUser(employeeService.selectGidByEid(vo.getEmployee().getEid()),vo.getEmployee().getCid())){
                throw new GradeExcption("评分人数不符合");
            }

        }
        else {
            if (size != groupService.CountNumForAll(vo.getEmployee().getCid())){
                throw new GradeExcption("评分人数不符合");
            }
        }
        Employee employee = vo.getEmployee();
        Integer eid = employee.getEid();

        Integer comEva = employeeService.findEmployeeByEid(eid).getComEva();

        if (lists.isEmpty()) {
            throw new CommitErrorException("提交失败");
        }

        //若之前已完成评价
        if (comEva == 0) {
            throw new FinishException("请勿重复提交");
        }

        //检查是否对所在小组所有人员评分
        for (Centre list : lists) {
            Integer sco = list.getSco();
            if (sco == null) {
                throw new NotFinishException("未完成评价,请勿提交");
            }
        }

        //获取评价人信息
        String role = employee.getRoles();
        String username = employee.getUsername();
        Integer jobNum = employee.getJobNum();
        Integer cid = employee.getCid();
//        String role = getRolesFromSession(session);
//        String username = getUsernameFromSession(session);
//        Integer jobNum = getJobNumFromSession(session);
//        Integer cid = getCidFromSession(session);

        Grade grade = new Grade();
        for (Centre list : lists) {
            Integer jobNumed = list.getJobNumed();
            //分数
            Integer sco = list.getSco();

            grade.setJobNumed(jobNumed);
            grade.setJobNum(jobNum);
            grade.setUsername(username);
            grade.setRoles(role);
            grade.setCid(cid);
            if(role.equals("colAdmin")) {
                grade.setLeaderGrade(sco);
                gradeService.insertLeaderGrade(grade);
            }
            else{
                grade.setEmpGrade(sco);
                gradeService.insertEmpGrade(grade);
            }

        }

        comEva = 0;
        employeeService.updateComEva(eid, comEva);
        return new JsonResult<>(OK);
    }

    //领导班子对中层干部评分提交接口
    @Transactional
    @RequestMapping("/insert_grade_med")
    public JsonResult<Void> insertLeaderGradeForMed(@RequestBody List<Centre> lists, HttpSession session) {
        Integer eid = getEidFromSession(session);
        Integer comEva = employeeService.findEmployeeByEid(eid).getComEva();

        if (lists.isEmpty()) {
            throw new CommitErrorException("提交失败");
        }

        //若之前已完成评价
//        if (comEva == 4 || comEva== 6 || comEva == 10 || comEva == 12){
//            throw new FinishException("请勿重复提交");
//        }

        //检查是否对所在小组所有人员评分
        for (Centre list : lists) {
            Integer sco = list.getSco();
            if (sco == null) {
                throw new NotFinishException("未完成评价,请勿提交");
            }
        }

        //获取评价人信息
        String role = getRolesFromSession(session);
        String username = getUsernameFromSession(session);
        Integer jobNum = getJobNumFromSession(session);
        Integer cid = getCidFromSession(session);

        Grade grade = new Grade();
        for (Centre list : lists) {
            Integer jobNumed = list.getJobNumed();
            //分数
            Integer sco = list.getSco();

            grade.setJobNumed(jobNumed);
            grade.setJobNum(jobNum);
            grade.setUsername(username);
            grade.setRoles(role);
            grade.setCid(cid);
            grade.setLeaderGrade(sco);
            gradeService.insertLeaderGrade(grade);

        }

        comEva = 1;
        employeeService.updateComEva(eid, comEva);
        return new JsonResult<>(OK);
    }

    //领导班子对基层干部评分提交接口
    //对基层干部组评分，提交接口
    @Transactional
    @RequestMapping("/insert_grade_sub")
    public JsonResult<Void> insertLeaderGradeForSub(@RequestBody Vo vo) {
//        Integer eid = getEidFromSession(session);
        Integer eid = vo.getEmployee().getEid();
        Integer comEva = employeeService.findEmployeeByEid(eid).getComEva();

        //若之前已完成评价
        if (comEva == 7 || comEva == 8) {
            throw new FinishException("请勿重复提交");
        }

        //检查是否对所在小组所有人员评分
        for (Centre list : vo.getLists()) {
            Integer sco = list.getSco();
            if (sco == null) {
                throw new NotFinishException("未完成评价,请勿提交");
            }
        }

        String role = vo.getEmployee().getRoles();
        String username = vo.getEmployee().getUsername();
        Integer jobNum = vo.getEmployee().getJobNum();
        Integer cid = vo.getEmployee().getCid();
        //获取评价人信息
//        String role = getRolesFromSession(session);
//        String username = getUsernameFromSession(session);
//        Integer jobNum = getJobNumFromSession(session);
//        Integer cid = getCidFromSession(session);

        Grade grade = new Grade();
        for (Centre list : vo.getLists()) {
            Integer jobNumed = list.getJobNumed();
            //分数
            Integer sco = list.getSco();

            grade.setJobNumed(jobNumed);
            grade.setJobNum(jobNum);
            grade.setUsername(username);
            grade.setRoles(role);
            grade.setCid(cid);
            if(role.equals("colAdmin")) {
                grade.setLeaderGrade(sco);
                gradeService.insertLeaderGrade(grade);
            }
            else{
                grade.setEmpGrade(sco);
                gradeService.insertEmpGrade(grade);
            }

        }

        comEva += 6;
        employeeService.updateComEva(eid, comEva);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/to_grade")
      public JsonResult<Map<String, Object>> toGrade(Integer eid,String role,Integer cid){
        //Integer eid = getEidFromSession(session);

        //String role = getRolesFromSession(session);
        Map<String, Object> map = new HashMap<>();
        if (role.equals("user")){
            Integer gid = employeeService.findEmployeeByEid(eid).getGid();
            String groupName = groupService.selectQuotaByGid(gid).getGroupName();
            Integer groExceNum = groupService.selectQuotaByGid(gid).getGroExceNum();
            Integer groGoodNum = groupService.selectQuotaByGid(gid).getGroGoodNum();
            List<Employee> employees = employeeService.showEmployeeByGid(gid);
            map.put("groupName",groupName);
            map.put("groExceNum",groExceNum);
            map.put("groGoodNum",groGoodNum);
            map.put("employees",employees);

        }

        else if (role.equals("colAdmin") || role.equals("med")){
//            Integer cid = getCidFromSession(session);
//            Integer cid = employeeService.findEmployeeByEid(eid).getCid();
            Integer colExceNum = collegeQuotaService.selectQuotaByCid(cid).getColExceNum();
            Integer colGoodNum = collegeQuotaService.selectQuotaByCid(cid).getColGoodNum();
            String collegeName = collegeService.findCollegeByCid(cid).getCollegeName();
            List<Employee> employees = employeeService.selectParticipateEmpolyeeByCid(cid);
            map.put("collegeName",collegeName);
            map.put("colExceNum",colExceNum);
            map.put("colGoodNum",colGoodNum);
            map.put("employees",employees);
        }
        return new JsonResult<>(OK,map);
    }

    /*@RequestMapping("/to_grade_med")
    public JsonResult<Map<String,Object>> toGradeMed(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Integer eid = getEidFromSession(session);
        Integer cid = getCidFromSession(session);
//        Integer cid = employeeService.findEmployeeByEid(eid).getCid();
        List<Employee> employees = employeeService.selectParticipateEmployeeByCidAndRolesMed(cid);
        int employeesNum = employees.size();
        int num = Math.toIntExact(Math.round(0.2 * employeesNum));
        if (num==0){
            num = 1;
        }

        String collegeName = collegeService.findCollegeByCid(cid).getCollegeName();
        //System.out.println(collegeName);
        collegeName = collegeName+"中基层干部";
        map.put("collegeName",collegeName);
        map.put("colExceNum",num);
        map.put("colGoodNum",num);
        map.put("employees",employees);
        return new JsonResult<>(OK,map);
    }*/

    /**
     * @RequestMapping("/to_grade_sub") public JsonResult<Map<String,Object>> toGradeSub(HttpSession session){
     * Map<String, Object> map = new HashMap<>();
     * Integer eid = getEidFromSession(session);
     * Integer cid = employeeService.findEmployeeByEid(eid).getCid();
     * List<Employee> employees = employeeService.selectParticipateEmployeeByCidAndRolesSub(cid);
     * int employeesNum = employees.size();
     * int num = Math.toIntExact(Math.round(0.2 * employeesNum));
     * if (num==0){
     * num = 1;
     * }
     * <p>
     * String collegeName = collegeService.findCollegeByCid(cid).getCollegeName();
     * collegeName = collegeName+"基层干部";
     * map.put("collegeName",collegeName);
     * map.put("colExceNum",num);
     * map.put("colGoodNum",num);
     * map.put("employees",employees);
     * return new JsonResult<>(OK,map);
     * }
     **/

    //评价人员给普通员工评分
    @RequestMapping("/to_grade_employee")
    public JsonResult<Map<String, Object>> toGradeEmployee(Integer cid, String groupName) {
        //Integer eid = getEidFromSession(session);
        //String role = getRolesFromSession(session);
        Map<String, Object> map = new HashMap<>();
        //获取gid
        Integer gid = groupService.selectGidByCidAndGroupName(cid, groupName);
        //获取良好指标
        Integer groExceNum = collegeQuotaService.selectQuotaByCid(cid).getColExceNum();
        //获取良好指标
        Integer groGoodNum = collegeQuotaService.selectQuotaByCid(cid).getColGoodNum();
        //获取员工组人员
        List<Employee> employees = employeeService.showEmployeeByGid(gid);
        //获取单位名称
        String collegeName = collegeService.findCollegeByCid(cid).getCollegeName();
        map.put("groExceNum", groExceNum);
        map.put("groGoodNum", groGoodNum);
        map.put("employees", employees);
        map.put("collegeName", collegeName);
        return new JsonResult<>(OK, map);
    }

    //评价人员给基层干部评分
    @RequestMapping("/to_grade_grassroot")
    public JsonResult<Map<String, Object>> toGradeGrassRoot(Integer cid, String groupName) {
        Map<String, Object> map = new HashMap<>();
        //获取gid
        Integer gid = groupService.selectGidByCidAndGroupName(cid, groupName);

        //获取员工组人员
        List<Employee> employees = employeeService.showEmployeeByGid(gid);
        //获取单位名称
        String collegeName = collegeService.findCollegeByCid(cid).getCollegeName();
        map.put("groExceNum", 0);
        map.put("groGoodNum", 0);
        map.put("employees", employees);
        map.put("collegeName", collegeName);
        return new JsonResult<>(OK, map);
    }
}
