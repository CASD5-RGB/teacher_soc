package com.lu.assess.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: helu
 * @date: 2022/7/21 21:56
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("score")
public class Score implements Serializable {
    @ExcelIgnore
    private Integer sid;
    @Excel(name = "工号",orderNum = "1",width = 20)
    private Integer jobNum;
    @Excel(name = "姓名",orderNum = "2",width = 20)
    private String username;
    @Excel(name = "员工互评得分",orderNum = "3",width = 20,type=10)
    private Double empScore;
    @Excel(name = "单位班子评价得分",orderNum = "4",width = 20,type=10)
    private Double leaderScore;
    @Excel(name = "综合评价得分",orderNum = "5",width = 20,type=10)
    private Double compreScore;
    @Excel(name = "年终绩效",orderNum = "6",width = 20,type=10)
    private Double yearPer;
    @Excel(name = "评价等次",orderNum = "7",replace = {"优秀_0","良好_1","合格_2","不合格_3"},width = 20)
    private Integer hier;


}
