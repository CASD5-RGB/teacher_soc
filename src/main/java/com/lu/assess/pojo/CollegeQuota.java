package com.lu.assess.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: helu
 * @date: 2022/7/18 16:41
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollegeQuota implements Serializable {
    private Integer qid;
    private Integer cid;
    private Double totalAnnual;
    private Integer colExceNum;
    private Integer colGoodNum;
}
