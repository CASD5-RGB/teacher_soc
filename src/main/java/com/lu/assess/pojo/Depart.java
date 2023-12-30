package com.lu.assess.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: helu
 * @date: 2022/7/15 20:54
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Depart implements Serializable {
    private Integer did;
    private Integer cid;
    private String departName;
}
