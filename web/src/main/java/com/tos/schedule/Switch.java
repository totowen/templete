package com.tos.schedule;

import lombok.Data;

import java.util.Date;

/**
 * @author qq136
 * @date 2017/10/30.
 * 开关实体
 */
@Data
public class Switch {

    private String name;

    /**
     * 开启 1 ； 关闭 0
     */
    private String type;

    /**
     * 开关时刻记录
     */
    private Date getTime;

    /**
     * 每次开关后需要推迟的时间记录，开启则推迟9分钟（后执行关闭），关闭则推迟1分钟（后执行开启）
     */
    private Date delayTime;

    /**
     * 动作状态（产水，反洗）
     */
    private String action;

    public Switch(String name, String type, Date getTime, Date delayTime,Action action) {
        this.name = name;
        this.type = type;
        this.getTime = getTime;
        this.delayTime = delayTime;
        this.action = action.getAction() ;
    }



}
