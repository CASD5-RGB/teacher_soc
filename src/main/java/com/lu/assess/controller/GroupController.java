package com.lu.assess.controller;

import com.lu.assess.pojo.Group;
import com.lu.assess.service.GroupService;
import com.lu.assess.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: helu
 * @date: 2022/8/7 19:26
 * @description:
 */
@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController extends BaseController{

    @Autowired
    private GroupService groupService;

    //通过gid查询组
    @RequestMapping("/selectGroupByGid")
    public JsonResult<List<Group>> selectGroupByGid(@RequestParam("gid") Integer gid){
        List<Group> group;
        if(gid!=null){
            group = groupService.selectGroupByGid(gid);
        }
        else {
            group = groupService.selectAllGroup();
        }
        return new JsonResult<>(OK, group);
    }

    //添加组
    @RequestMapping("/insertGroup")
    public JsonResult<Integer> insertGroup(@RequestParam("group_name") String group_name,HttpSession session){

        Integer cid = getCidFromSession(session);
//        System.out.println(cid);
        Integer result = groupService.insertGroup(group_name,cid);
        return new JsonResult<Integer>(OK,result);
    }

    //查询所有组
    @RequestMapping("/selectAllGroup")
    public JsonResult<List<Group>> selectAllGroup(){
        List<Group> groups = groupService.selectAllGroup();
        return new JsonResult<>(OK,groups);
    }

    //通过学院编号cid查询组
    @RequestMapping("/selectGroupByCid")
    public JsonResult<List<Group>> selectGroupByCid(@RequestParam("cid") Integer cid){
        List<Group> groups = groupService.selectGroupByCid(cid);
        return new JsonResult<>(OK,groups);
    }

    //通过gid更新小组指标数
    @RequestMapping("/updateQuotaByGid")
    public JsonResult<Integer> updateQuotaByGid(Integer gid,
                                                Integer gro_exce_num,
                                                Integer gro_good_num){
        Integer result = groupService.updateQuotaByGid(gid,gro_exce_num,gro_good_num);
        return new JsonResult<>(OK,result);
    }

    //通过cid查询学院剩余优秀指标数
    @RequestMapping("/selectRemainderExcequotaByCid")
    public JsonResult<Integer> selectRemainderExcequotaByCid(@RequestParam("cid") Integer cid){
        Integer quota = groupService.selectRemainderExcequotaByCid(cid);
        return new JsonResult<>(OK,quota);
    }

    //通过cid查询学院剩余良好指标数
    @RequestMapping("/selectRemainderGoodquotaByCid")
    public JsonResult<Integer> selectRemainderGoodquotaByCid(@RequestParam("cid") Integer cid){
        Integer quota = groupService.selectRemainderGoodquotaByCid(cid);
        return new JsonResult<>(OK,quota);
    }

    //通过cid删除小组
    @RequestMapping("/deleteGroupByCid")
    public JsonResult<Integer> deleteGroupByCid(@RequestParam("cid") Integer cid){
        Integer res = groupService.deleteGroupByCid(cid);
        return new JsonResult<>(OK, res);
    }



}

