package com.lu.assess.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.lu.assess.controller.ex.NullResultException;
import com.lu.assess.pojo.Score;
import com.lu.assess.service.*;
import com.lu.assess.until.JsonResult;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: helu
 * @date: 2022/7/26 22:54
 * @description:
 */
@RestController
@RequestMapping("/score")
@CrossOrigin
public class ScoreController extends BaseController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private CollegeQuotaService collegeQuotaService;
//    @Autowired
//    private GroupService groupService;

    @Transactional
    @RequestMapping("/count")
    public JsonResult<Void> countResult(Integer cid, Integer eid) {
//        Integer cid = getCidFromSession(session);
//        Integer eid = getEidFromSession(session);

        employeeService.showWhetherCount(eid);
        scoreService.insertFirst(cid, eid);
        scoreService.updateYearPerByJobNum(cid);
        scoreService.updateHierByJobNum(cid);
        employeeService.updateCount(eid);

//        groupService.testInsert_1();
//        groupService.testInsert_2();
        return new JsonResult<>(OK);
    }

    @RequestMapping("/show_result")
    public JsonResult<Map<String, Object>> showAssessResult(Integer cid) {
        try {
            Integer colExceNum = collegeQuotaService.selectQuotaByCid(cid).getColExceNum();
            Integer colGoodNum = collegeQuotaService.selectQuotaByCid(cid).getColGoodNum();
            List<Score> result = scoreService.showAssessResult(cid);
            Map<String, Object> map = new HashMap<>();
            map.put("colExceNum", colExceNum);
            map.put("colGoodNum", colGoodNum);
            map.put("result", result);

            return new JsonResult<>(OK, map);
        } catch (Exception e) {
            throw new NullResultException();
        }
    }

    @RequestMapping("/export_result")
    public JsonResult<Void> exportResult(HttpServletResponse response, HttpServletRequest request, Integer cid) throws IOException {
        List<Score> data = scoreService.showAssessResult(cid);
        if (data.isEmpty()) {
            throw new NullResultException("还未计算结果,不能导出");
        }
        //获取学院名称
        String collegeName = collegeService.findCollegeByCid(cid).getCollegeName();
        //获取当前年份
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        year = year-1;
        String year1 = Integer.toString(year);
        //导入excel表格
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(year1 + "年" + collegeName + "考核结果及绩效分配汇总", "考核信息"), Score.class, data);
       // response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(year1 + "年" + collegeName + "考核结果及绩效分配汇总.xls", "UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        workbook.write(os);
        os.close();
        workbook.close();
        return new JsonResult<>(OK);
    }
}
