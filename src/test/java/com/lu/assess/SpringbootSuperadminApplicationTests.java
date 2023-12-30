package com.lu.assess;

import com.lu.assess.mapper.*;
import com.lu.assess.pojo.*;
import com.lu.assess.service.*;
import com.lu.assess.until.EncryptUtil;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
class SpringbootSuperadminApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private CollegeQuotaMapper collegeQuotaMapper;
    @Autowired
    private CollegeQuotaService collegeQuotaService;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupService groupService;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getConnection());
    }


    @Test
    public void getAll(){
       List<Employee> list= employeeMapper.getAll();
        for (int i = 0; i < list.size(); i++) {
            val employee = list.get(i);

            //将取出数据中的password进行加密
            String encrypt = EncryptUtil.encrypt(employee.getPassword());
            //将加密之后的数据重新添加到DB中去
            employee.setPassword(encrypt);
            employeeMapper.update(employee.getEid(),encrypt);
        }

    }


    @Test
    public void insertEmployee() {
        Employee employee = new Employee();
        employee.setJobNum(110120);
        employee.setUsername("lvyongfei");
        employee.setPassword("123456");
        employee.setRoles("superAdmin");
        employee.setCid(1);
        employee.setSex(1);
        employee.setDid(1);
        employee.setGid(1);
        employee.setComEva(1);
        Integer row = employeeMapper.insertEmployee(employee);
        System.out.println(row);
    }

    @Test
    public void findEmployeeByJobNum() {
        Employee employee = employeeMapper.findEmployeeByJobNum(110120);
        System.out.println(employee);
    }

    @Test
    public void Insert() {
        Employee employee = new Employee();
        employee.setJobNum(110124);
        employee.setUsername("liu02");
        employee.setPassword("123456");
        employee.setRoles("colAdmin");
        employeeService.insertEmployee(employee);
    }

    @Test
    public void deleteEmployeeByEid() {
//        Integer row = employeeMapper.deleteEmployeeByEid(3);
//        System.out.println(row);
        System.out.println(employeeMapper.findByEid(1));
    }

    @Test
    public void showEmployeeInfo() {
        List<Employee> employees = employeeMapper.showEmployeeInfo();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void updateEmployeeInfo() {
        employeeService.updateEmployeeInfo(1, 203034, "李媛1", 0, 1, 1,"med");
    }

    @Test
    public void selectCollege() {


    }

    @Test
    public void findCollegeByCid() {
        College row = collegeMapper.findCollegeByCid(12);
        System.out.println(row);
    }

    @Test
    public void selectDepart() {
        List<Depart> departs = departMapper.selectDepart();
        for (Depart depart : departs) {
            System.out.println(depart);
        }
    }

    @Test
    public void insertCollege() {
        Integer row = collegeMapper.insertCollege("2学院");
        System.out.println(row);
    }

    @Test
    public void insertDepart() {
        Integer row = departMapper.insertDepart(1, "计科21教研室");
        System.out.println(row);
    }

    @Test
    public void deleteCollege() {
        Integer row = collegeMapper.deleteCollegeByCid(13);
        System.out.println(row);
    }

    @Test
    public void deleteCollegeByCid() {
        collegeService.deleteCollegeByCid(12);
    }

    @Test
    public void updateCollegeByCid() {
        Integer row = collegeMapper.updateCollegeByCid(11, "112学院");
        System.out.println(row);
    }

    @Test
    public void deleteDepartByDid() {
        Integer row = departMapper.deleteDepartByDid(1);
        System.out.println(row);
    }

    @Test
    public void updateDepart() {
        Integer row = departMapper.updateDepartByDid(4, "计科11");
        System.out.println(row);
    }

    @Test
    public void insertQuota() {
        Integer row = collegeQuotaMapper.insertQuota(1, 12.1, 3, 2);
        System.out.println(row);
    }

    @Test
    public void updateQuota() {
        Integer row = collegeQuotaMapper.updateQuota(1, 13.3, 2, 5);
        System.out.println(row);
    }

    @Test
    public void selectQuotaByCid() {
        CollegeQuota collegeQuota = collegeQuotaMapper.selectQuotaByCid(1);
        System.out.println(collegeQuota);
    }

    @Test
    public void insertAndUpdateQuota() {
        CollegeQuota collegeQuota = new CollegeQuota();
        collegeQuota.setCid(2);
        collegeQuota.setTotalAnnual(16.0);
        collegeQuota.setColExceNum(3);
        collegeQuota.setColGoodNum(6);

    }

    @Test
    public void showGradeByJobNumed() {
        List<Grade> grades = gradeMapper.showGradeByJobNumed(110125);
        for (Grade grade : grades) {

            System.out.println(grade);
        }
//        Calendar cal = Calendar.getInstance();
//
//        //int year = cal.get(Calendar.YEAR);
//        int year = cal.get(Calendar.YEAR);
//        String year1 = Integer.toString(year);
//        System.out.println(year1+"年");
    }

    @Test
    public void test1() {
//        int a = 10;
//        Double b = Double.valueOf(a)/4;
//        System.out.println(b);
        //保留2位小数
        double one = 30.10;
        String str = String.format("%.2f", one);
        //double four = Double.parseDouble(str);
        Double four = Double.valueOf(str);
        System.out.println(four);
    }

//    @Test
//    public void showJobNumed() {
//        List<Grade> grades = gradeMapper.showJobNum();
//        for (Grade grade : grades) {
//            System.out.println(grade);
//        }
//    }

    @Test
    public void showempAvg() {
        Double empGradeAvg = gradeMapper.empGradeAvg(110128);
        System.out.println(empGradeAvg);
    }

    @Test
    public void showleaderAvg() {
        Double empGradeAvg = gradeMapper.leaderGradeAvg(110128);
        System.out.println(empGradeAvg);
    }

    @Test
    public void insertFirst() {
        Score score = new Score();
        score.setJobNum(110130);
        score.setUsername("刘先生");
        score.setEmpScore(90.21);
        score.setLeaderScore(91.22);
        score.setCompreScore(88.01);
        Integer row = scoreMapper.insertFirst(score);
        System.out.println(row);

    }



    @Test
    public void sumComprtScore() {
        Double result = scoreMapper.sumComprtScore(2);
        System.out.println(result);
    }

    @Test
    public void selectJobNumAndCompreScore() {
        List<Score> scores = scoreMapper.selectJobNumAndCompreScore(2);
        for (Score score : scores) {
            System.out.println(score);
        }

    }

    @Test
    public void insertYearPerByJobNum() {
        Integer row = scoreMapper.updateYearPerByJobNum(110130, 118.1);
        System.out.println(row);
    }

    @Test
    public void selectCid() {
        List<CollegeQuota> colleges = collegeQuotaMapper.selectCid();
        for (CollegeQuota college : colleges) {
            System.out.println(college.getCid());
        }
    }

//    @Test
//    public void insertYearPerByJobNum1() {
//        scoreService.updateYearPerByJobNum();
//    }

//    @Test
//    public void updateHierByJobNum() {
//        Integer row = scoreMapper.updateHierByJobNum(110130, 0);
//        System.out.println(row);
//    }

    @Test
    public void updateHierByJobNum1() {
//        scoreService.updateHierByJobNum();
//        List<CollegeQuota> collegeQuotas = collegeQuotaMapper.selectCid();
//        for (CollegeQuota collegeQuota : collegeQuotas) {
//            //获取学院id
//            Integer cid = collegeQuota.getCid();
//            //通过cid查询指标
//            CollegeQuota collegeQuota1 = collegeQuotaMapper.selectQuotaByCid(cid);
//            //获取学院优秀指标
//            Integer colExceNum = collegeQuota1.getColExceNum();
//            //获取学院良好指标
//            Integer colGoodNum = collegeQuota1.getColGoodNum();;
//            //根据学院id选出工号，综合评价得分，降序
//            List<Score> scores = scoreMapper.selectJobNumAndCompreScore(cid);
//            for(int i = 0; i<scores.size();i++){
//                Integer jobNum = scores.get(i).getJobNum();
//
//                if (i<colExceNum){
//                    Integer row = scoreMapper.updateHierByJobNum(jobNum, 0);
//                    if (row!=1){
//                        throw new InsertException("插入异常");
//                    }
//                }
//                else if (i<(colExceNum+colGoodNum)){
//                    Integer row = scoreMapper.updateHierByJobNum(jobNum, 1);
//                    if (row!=1){
//                        throw new InsertException("插入异常");
//                    }
//                }
//                else {
//                    Integer row = scoreMapper.updateHierByJobNum(jobNum, 2);
//                    if (row!=1){
//                        throw new InsertException("插入异常");
//                    }
//                }
//            }
//
//        }

    }

    @Test
    public void showAssessResult() {
        List<Score> scores = scoreMapper.showAssessResult(2);
        for (Score score : scores) {
            System.out.println(score);
        }
    }

    @Test
    public void findCollegeByCid1() {
        College college = collegeService.findCollegeByCid(2);
        System.out.println(college);
    }

    @Test
    public void login() {
        Employee employee = employeeService.login(110124, "123456");
        System.out.println(employee);
    }

    @Test
    public void insertEmpGrade() {
        Grade grade = new Grade();
        grade.setJobNumed(110129);
        grade.setJobNum(110129);
        grade.setEmpGrade(88);
        grade.setRoles("user");
        grade.setUsername("王三");
        Integer row = gradeMapper.insertEmpGrade(grade);
        System.out.println(row);

    }

    @Test
    public void updateComEva() {
        Integer row = employeeMapper.updateComEva(1, 0);
        System.out.println(row);
    }

    @Test
    public void selectQuotaByGid() {
        Group group = groupMapper.selectQuotaByGid(1);
        System.out.println(group);
    }

    @Test
    public void showEmployeeByGid() {
        List<Employee> employees = employeeMapper.showEmployeeByGid(1);
        for (Employee employee : employees) {

            System.out.println(employee);
        }
    }

    @Test
    public void updatePassword() {
        Integer row = employeeMapper.updatePassword("123", 5);
        System.out.println(row);
    }

    @Test
    public void updatePassword1() {
        employeeService.updatePassword(5, "123456", "12346", "12345");

    }

//    @Test
//    public void deleteGroup() {
//        groupService.deleteGroup();
//    }

    @Test
    public void ti() {
        Integer row = groupService.insertGroup("111", 1);
        System.out.println(row);
    }

    @Test
    public void ti1() {
//        Integer[] eids = {1,2,41,42};
//        Integer row = employeeMapper.uploadNotParticipateEids(eids);
//        System.out.println(row);

//        List<Employee> employees = employeeService.selectParticipateEmpolyeeByCid(1);
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }

        List<Grade> grades = gradeService.showGradeByJobNumed(203056);
        for (Grade grade : grades) {
            System.out.println(grade);
        }

    }

    @Test
    public void tt(){
        Double result = scoreMapper.sumComprtScore(1);
        System.out.println(result);
    }

    private Double changeResult(Double num){
        String  str = String.format("%.2f",num);
        Double result = Double.parseDouble(str);
        return result;
    }

    @Test
    public void tt1(){
        List<Employee> participates = employeeService.showUsernameAndDepartNameByCid(1);
        for (Employee participate : participates) {
            System.out.println(participate);
        }
    }

    @Test
    public void showWhetherCount(){
        Integer count = employeeMapper.showWhetherCount(41);
        System.out.println(count);
    }

    @Test
    public void updateCount(){
        employeeService.updateCount(41);
    }



//    @Test
//    public void finish(){
//        System.out.println(gradeMapper.finishAssessNum());
//    }

    @Test
    public void showComEvaZero1(){
//        List<Employee> employees = employeeMapper.showComEvaZero(1);
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
//        Integer length20 = Math.toIntExact(Math.round(0.2 * 7));
//        System.out.println(length20);

//        employeeMapper.updateDepart(10);
        List<Employee> employees = employeeMapper.showEmployeeByGid(66);
        for (Employee employee : employees) {

            System.out.println(employee);
        }

    }

    @Test
    public void cq(){
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<College> list3 = new ArrayList<>();
        List<College> colleges = collegeMapper.selectCollege();
        List<CollegeQuota> collegeQuotas = collegeQuotaMapper.selectCid();

        for (College college : colleges) {
            list1.add(college.getCid());
        }
        for (CollegeQuota collegeQuota : collegeQuotas) {
            list2.add(collegeQuota.getCid());
        }
        list1.removeAll(list2);

        for (Integer cid : list1) {
            College collegeByCid = collegeMapper.findCollegeByCid(cid);
            list3.add(collegeByCid);
        }
        for (College college : list3) {
            System.out.println(college);
        }
    }

    @Test
    public void testListener(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"234");
        String s = map.get(1);
        System.out.println(s);
    }

    @Test
    public void testCol(){
        List<College> colleges = collegeMapper.selectCollege();
        colleges.forEach(System.out::println);
    }

    @Test
    public void testNu(){
        int employeesNum = 2;
        int num = Math.toIntExact(Math.round(0.2 * employeesNum));
        if (num==0){
            num = 1;
        }
        System.out.println(num);
    }

    @Test
    public void testMed(){
        List<Employee> employees = employeeService.selectParticipateEmployeeByCidAndRolesMed(1);
        employees.forEach(System.out::println);
    }

    @Test
    public void testSelect(){
        List<Employee> employees = employeeMapper.selectEmployeeByCid(6);
        employees.forEach(System.out::println);
    }

    @Test
    public void testFindByEid(){
        System.out.println(employeeMapper.findByEid(593));
    }

    @Test
    public void testInsert(){
        Integer row = employeeMapper.updateCountOne();

    }

    @Test
    public void testuploadEidsByGid(){
//        Integer eids[] = {3,4};
//        Integer row = employeeService.uploadEidsByGid(2, eids);
//        System.out.println(row);
//        List<Employee> employees = employeeService.selectParticipateEmployeeByCidAndRolesMed(25);
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }

    }

    @Test
    public void testComEva(){
//        List<Employee> employees = employeeMapper.showComEvaZero(24);
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
        System.out.println(gradeMapper.ColFinishAssessNum(20));
    }

    @Test
    public void testInsertFirst(){
        List<Employee> employees = employeeMapper.selectEmployeeByCid(8);
        System.out.println(employees);
    }

    @Test
    public void testSelectGidByCidAndGroupName(){
//        System.out.println(groupMapper.selectGidByCidAndGroupName(1, "员工组"));
//        String  str = String.format("%.2f",12.0);
//        System.out.println(str);
//        Double result = Double.parseDouble(str);
//        System.out.println(result);
//        List<Employee> employees = employeeMapper.showUsernameAndDepartNameByCid(9);
//        employees.forEach(System.out::println);

        System.out.println(employeeMapper.countNum(9));
        System.out.println(gradeMapper.finishAssessNum(9));
    }




}
