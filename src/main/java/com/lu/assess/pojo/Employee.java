package com.lu.assess.pojo;

import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: helu
 * @date: 2022/7/14 15:44
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private Integer eid;
    @NotNull
    private Integer jobNum;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String roles;
    @NotNull
    private Integer cid;
    @NotNull
    private Integer sex;
    private Integer did;
    private Integer gid;
    private Integer participate; 		//0为参与，1为不参与
    private Integer comEva;             //0为完成，1为未完成
    private Integer count;             //0为完成计算，1为未完成计算
    private College college;
    private Depart depart;


}
