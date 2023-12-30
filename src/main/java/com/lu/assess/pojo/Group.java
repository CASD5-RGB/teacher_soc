package com.lu.assess.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: helu
 * @date: 2022/7/29 16:11
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {
    private Integer gid; 		//id设为自增     int    主键
    private String groupName; 	//小组名称    varchar(50)
    private Integer groExceNum; 		//小组优秀指标   int
    private Integer groGoodNum; 		//小组良好指标   int
    private Integer cid;    //小组所属学员编号
}
