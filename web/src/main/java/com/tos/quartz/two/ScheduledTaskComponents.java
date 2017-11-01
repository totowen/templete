package com.tos.quartz.two;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * @author qq136
 * @date 2017/10/31.
 */
@Component
public class ScheduledTaskComponents {

    /**
     * 初始化启动停机操作
     *      注意: MBR系统先停止产水泵、污泥回流泵，然后关闭各个气动阀门，最后停止曝气风机。
     * @throws Exception
     */
    @PostConstruct
    public void initStop() throws Exception {
        System.out.println("开启停止所有开关工作");
        System.out.println("初始化所有状态值");
        //1系统开机时间 sysBootTime
        //2任务状态
        //3系统开机状态初始化值0 sysType = 0(关闭)，1(开启)
    }

    /**
     * 检查是否能够开启MBR系统
     */
//    @Scheduled(fixedRate = 5000)
    public void init() throws Exception {
        //检查所有开关状态是否为关闭状态
        //任务状态初始化是0 taskType = 0(产水),1(反洗),2(在线清洗)
        //所有开关状态开关状态
        Integer allValveType = 0;
        //系统开机状态
        Integer sysType = 0;
        //任务状态
        Integer taskType = 0;
        //开机运行时长(分钟)
        long sysRunningTime = 0L;
        //24小时（分钟）
        long twenty_four = 24*60;
        if(0==allValveType && 0 == sysType && 0 == taskType ){
            System.out.println("初始化成功，开始进入产水系统");
            //记录开机状态 sysType = 1
            //记录产水开始时间 contributeWaterStartTime
            //记录产水任务开始状态 contributeWaterTaskType = 1;
            System.out.println("远程开启产水阀 + 开启产水泵开关");
            //记录MBR开启时间 sysBootTime
        }else if(sysRunningTime>twenty_four){
            //检测当前任务状态
            if(0 == taskType){
                taskType = 2;
                System.out.println("远程关闭产水泵 + 关闭产水阀");
            }else if(1 == taskType){
                taskType = 2;
                System.out.println("远程关闭所有反水阀门");
            }
        }
    }

    /**
     * 返回值状态
     * 返回Valve 集合
     * 1 目标产水开启成功状态返回 1001
     * 2 目标产水关闭成功状态返回 1002
     * 3 目标反洗阀开启成功状态返回 1003
     * 4 目标反洗阀关闭成功状态返回 1004
     *     --》所有反洗阀门都关闭 10041
     * 5 目标CIP药洗水泵开启成功状态返回 1005
     * 6 目标CIP药洗水泵关闭成功状态返回 1006
     */
    @Scheduled(fixedRate = 5000)
    public void result() throws Exception {

    }

}
