package com.lu.assess.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.io.Serializable;
import java.util.List;

/**
 * @author: helu
 * @date: 2023/6/10 17:53
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vo {
    List<Centre> lists;
    Employee employee;
//    Integer eid;
}
