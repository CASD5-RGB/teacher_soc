package com.lu.assess.controller;

import com.lu.assess.pojo.Depart;
import com.lu.assess.service.DepartService;
import com.lu.assess.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/18 15:47
 * @description:
 */
@RestController
@RequestMapping("/depart")
@CrossOrigin
public class DepartController extends BaseController{
    @Autowired
    private DepartService departService;

    @RequestMapping("/show_depart")
    public JsonResult<List<Depart>> showDepartByCid(Integer cid){
        List<Depart> data = departService.showDepartByCid(cid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("/add_depart")
    public JsonResult<Void> insertDepart(Integer cid,String departName){
        departService.insertDepart(cid,departName);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/delete_depart")
    public JsonResult<Void> deleteDepart(Integer did){
        departService.deleteDepart(did);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/update_depart")
    public JsonResult<Void> updateDepart(Integer did,String departName){
        departService.updateDepart(did,departName);
        return new JsonResult<>(OK);
    }
}
