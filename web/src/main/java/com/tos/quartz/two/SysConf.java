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
 * 系统配置参数
 */
@Entity
@Data
public class SysConf implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 最近一次系统开机时间
     */
    @Column
    private Date sysBootTime;

    /**
     * 最近一次系统关机时间
     */
    @Column
    private Date sysOffTime;

    /**
     * 系统开机状态
     * 初始化值0 sysType = 0(关闭)，1(开启)
     */
    @Column
    private Integer sysType;

    /**
     * 所有开关状态开关状态
     *  只要有一个阀门是开启状态，该值状态就为1，否则为0
     */
    @Column
    private Integer allValveType;

    /**
     * 任务状态
     *
     * 任务状态初始化是0 taskType = 0(产水),1(反洗),2(在线清洗)
     */
    @Column
    private Integer taskType;

    /**
     * 产水运行开始时间
     */
    @Column
    private Date contributeWaterStartTime;

    /**
     * 产水运行结束时间
     */
    @Column
    private Date contributeWaterEndTime;

    /**
     * 产水任务开始状态初始值为0
     * 1代表开始状态，0代表关闭状态
     */
    @Column
    private Integer contributeWaterTaskType;

    /**
     * 反洗任务开始状态初始值为0
     * 1代表开始状态，0代表关闭状态
     */
    @Column
    private Integer backwaskTaskType;

    /**
     * 记录每1小时停止产水后的叠加数
     */
    @Column
    private Integer contributeWaterOneHourNumber;

    /**
     * 记录每1小时停止反水阀后的叠加数
     */
    @Column
    private Integer backwashOneHourNumber;

    /**
     * 反洗阀数量
     */
    @Column
    private Integer backwashMum;
}
