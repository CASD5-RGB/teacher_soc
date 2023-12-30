package com.lu.assess.controller;

import com.lu.assess.pojo.CollegeQuota;
import com.lu.assess.service.CollegeQuotaService;
import com.lu.assess.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: helu
 * @date: 2022/7/18 19:37
 * @description:
 */
@RestController
@RequestMapping("/college_quota")
@CrossOrigin
public class CollegeQuotaController extends BaseController{
    @Autowired
    CollegeQuotaService collegeQuotaService;

    @RequestMapping("/insert_update")
    public JsonResult<Void> insertAndUpdateQuota(CollegeQuota collegeQuota){
        collegeQuotaService.insertAndUpdateQuota(collegeQuota);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/show_quota")
    public JsonResult<CollegeQuota> showQuota(Integer cid){
        CollegeQuota collegeQuota = collegeQuotaService.selectQuotaByCid(cid);
        return new JsonResult<>(OK,collegeQuota);
    }
}
