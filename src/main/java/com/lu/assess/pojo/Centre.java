package com.lu.assess.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: helu
 * @date: 2022/7/29 11:26
 * @description:
 */
@Data
public class Centre implements Serializable{
    //被评价人工号
    private Integer jobNumed;
    //评价分数
    private Integer sco;
}
