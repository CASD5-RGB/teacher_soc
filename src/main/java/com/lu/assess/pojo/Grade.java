package com.lu.assess.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: helu
 * @date: 2022/7/19 16:28
 * @description: 得分表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Integer rid;
    private  Integer cid;
    private Integer jobNumed;
    private Integer jobNum;
    private String username;
    private String roles;
    private Integer empGrade;
    private Integer leaderGrade;
}
