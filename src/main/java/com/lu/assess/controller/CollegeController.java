package com.lu.assess.controller;

import com.lu.assess.pojo.College;
import com.lu.assess.service.CollegeService;
import com.lu.assess.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/16 20:32
 * @description:
 */
@RestController
@RequestMapping("/college")
@CrossOrigin
public class CollegeController extends BaseController{
    @Autowired
    private CollegeService collegeService;

    @RequestMapping("/show_college")
    public JsonResult<List<College>> selectCollege(){
        List<College> data = collegeService.showCollege();
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("/add_college")
    public JsonResult<Void> insertCollege(String collegeName){
         collegeService.insertCollege(collegeName);
         return new JsonResult<>(OK);
    }

    @RequestMapping("/delete_college")
    public JsonResult<Void> deleteCollegeByCid(Integer cid){
        collegeService.deleteCollegeByCid(cid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/update_college")
    public JsonResult<Void> updateCollegeByCid(Integer cid,String collegeName){
        collegeService.updateCollegeByCid(cid,collegeName);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/findNotCollegeQuota")
    public JsonResult<List<College>> findNotCollegeQuota(){
        List<College> data = collegeService.findNotCollegeQuota();
        return new JsonResult<>(OK,data);
    }
}
