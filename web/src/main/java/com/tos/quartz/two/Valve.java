package com.tos.quartz.two;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qq136
 * @date 2017/10/31.
 *
 * 开关实体
 */
@Entity
@Data
public class Valve implements Serializable{


    @Id
    @GeneratedValue
    private Long id;

    /**
     * 阀门名称
     */
    @Column
    private String name;

    /**
     * 最近一次开启时间
     */
    @Column
    private Date startTime;

    /**
     * 最近一次关闭时间
     */
    @Column
    private Date endTime;

    /**
     * 开关状态 初始值为0
     * 1(开启)，0(关闭)
     */
    @Column
    private Integer onOffState;

}
