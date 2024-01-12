package com.lu.assess.controller;

import com.lu.assess.controller.ex.*;
import com.lu.assess.service.ex.*;
import com.lu.assess.until.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author: helu
 * @date: 2022/7/14 17:57
 * @description:
 */
public class BaseController {
    public static final Integer OK=200;

    @ExceptionHandler({ServiceException.class, CommitException.class})
    public JsonResult<Void> exceptionHandler(Throwable e){

        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof JobNumDuplicateException){
            result.setStatus(4000);
            result.setMessage("该用户已存在");
        }
        else if (e instanceof InsertException){
            result.setStatus(4001);
            result.setMessage("插入异常");
        }
        else if (e instanceof EmployeeNotFoundException){
            result.setStatus(4002);
            result.setMessage("该员工不存在");
        }
        else if (e instanceof DeleteException){
            result.setStatus(4003);
            result.setMessage("删除异常");
        }
        else if (e instanceof UpdateException){
            result.setStatus(4004);
            result.setMessage("更新异常");
        }
        else if (e instanceof CollegeNotFoundException){
            result.setStatus(4005);
            result.setMessage("该学院不存在");
        }
        else if (e instanceof DepartHaveException){
            result.setStatus(4006);
            result.setMessage("给部门已存在");
        }
        else if (e instanceof NotGradeException){
            result.setStatus(4007);
            result.setMessage("还未被评价");
        }
        else if (e instanceof SelectException){
            result.setStatus(4008);
            result.setMessage("查询异常");
        }
        else if (e instanceof PasswordNotMatchException){
            result.setStatus(4009);
            result.setMessage("密码错误");
        }
        else if (e instanceof NewPasswordException){
            result.setStatus(4012);
            result.setMessage("请再次确认新密码");
        }
        else if (e instanceof EqualsPasswordException){
            result.setStatus(4013);
            result.setMessage("新密码与原密码相同");
        }
        else if (e instanceof FinishException){
            result.setStatus(4010);
            result.setMessage("请勿重复提交");
        }
        else if (e instanceof GradeExcption){
            result.setStatus(4011);
            result.setMessage("不符合评分规则，请重新评分");
        }
        else if (e instanceof GroupNotFoundException){
            result.setStatus(4014);
            result.setMessage("该小组不存在");
        }
        else if (e instanceof NotDataException){
            result.setStatus(4015);
            result.setMessage("全体员工还未评价");
        }
        else if (e instanceof NotFinishAssessException){
            result.setStatus(4016);
            result.setMessage("还有员工未完成评价");
        }
        else if (e instanceof NotFinishException){
            result.setStatus(4017);
            result.setMessage("未完成评价,请勿提交");
        }
        else if (e instanceof NullResultException){
            result.setStatus(4018);
            result.setMessage("还未计算结果,不能导出");
        }
        else if (e instanceof CommitErrorException){
            result.setStatus(4019);
            result.setMessage("提交失败，请重新提交");
        }
        else if (e instanceof CommitException){
            result.setStatus(4020);
            result.setMessage("分配指标数超过学院指标数");
        }
        return result;
    }

    //获取session中的数据
    protected final Integer getEidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("eid").toString());
    }

    protected final Integer getJobNumFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("jobNum").toString());
    }

    protected final Integer getCidFromSession(HttpSession session){

        return Integer.valueOf(session.getAttribute("cid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
    protected final String getRolesFromSession(HttpSession session){
        return session.getAttribute("roles").toString();
    }
}
