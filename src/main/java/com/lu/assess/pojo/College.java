package com.lu.assess.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/15 15:39
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class College implements Serializable {
    private Integer cid;
    private String collegeName;

}
