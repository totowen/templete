package com.tos.scheduled;

import com.tos.quartz.two.SysConf;
import com.tos.quartz.two.SysConfRepository;
import com.tos.quartz.two.Valve;
import com.tos.quartz.two.ValveRepository;
import com.tos.schedule.ScheduledTasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author qq136
 * @date 2017/10/30.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTests {

    @Autowired
    private ValveRepository valveRepository;

    @Autowired
    private SysConfRepository sysConfRepository;

    @Test
    public void test1() {

        List<Valve> valveList = new ArrayList<Valve>();
        for (int i = 0; i < 4; i++) {
            Valve valve = new Valve();
            valve.setName(i + 1 + "#");
            valve.setOnOffState(0);
            valveList.add(valve);
        }
        valveRepository.save(valveList);

    }

    @Test
    public void test2() {

        SysConf sysConf = new SysConf();

        sysConf.setSysBootTime(null);
        sysConf.setSysOffTime(null);
        sysConf.setSysType(0);
        sysConf.setAllValveType(0);
        sysConf.setTaskType(0);
        sysConf.setContributeWaterStartTime(null);
        sysConf.setContributeWaterEndTime(null);
        sysConf.setContributeWaterTaskType(0);
        sysConf.setBackwaskTaskType(0);
        sysConf.setContributeWaterOneHourNumber(0);
        sysConf.setBackwashOneHourNumber(0);
        sysConf.setBackwashMum(4);

        sysConfRepository.save(sysConf);

    }

    @Test
    public void taskTest() throws InterruptedException {

        //获取开启状态
        int state = 1001;
        //获取任务状态
        int taskType = 0;
        //获取系统开机时间
        Date sysBootTime = new Date();
        //开机运行时长(分钟)
        long sysRunningTime = 0L;
        //产水运动结束时间
        Date contributeWaterEndTime = null;
        //记录每1小时停止产水后的叠加数
        Integer contributeWaterOneHourNumber = 0;
        //记录每1小时停止反水阀后的叠加数
        Integer backwashOneHourNumber = 0;
        //产水任务开始状态初始值为0
        Integer contributeWaterTaskType = 1;
        //产水反洗任务开始状态初始值为0
        Integer backwaskTaskType = 0;
        //反洗阀数量
        Integer backwashMum = 4;
        //反洗阀编号(在线清洗使用)
        Integer backwashNo = 1;

        //产水次数记录////////////////
        int flag = 0;
        //////////////////////////////

        //24小时是否到达/////////////
        int temp = 0;
        ////////////////////////////

        while (true) {
            //监听触发
            if (1001 == state && 0 == taskType) {

                /////////////////////////////
                flag += 1;
                ////////////////////////////

                System.out.println("开始执行9分钟后的运行逻辑 ：关闭产水泵 + 关闭产水阀 " + flag);

                ////////////////////////////
                Thread.sleep(900);
                state = 1002;
                ////////////////////////////

            } else if (1002 == state && 0 == taskType) {
                //记录产水运行结束时间  contributeWaterEndTime
                contributeWaterEndTime = new Date();
                //计算开机运行时长 sysRunningTime =  new Date - sysBootTime
                long l = (new Date()).getTime() - sysBootTime.getTime();
                sysRunningTime = Long.valueOf(l / 60000);
                //进入反水的逻辑为 result = sysRunningTime/60, result>contributeWaterOneHourNumber 且 result>backwashOneHourNumber
                long result = sysRunningTime / 60;


                System.out.println("temp " + temp);
                if (temp < 4) {

//               if(result<=contributeWaterOneHourNumber){
                    //////////////////////////////////////////
                    if (flag < 10) {
                        //////////////////////////////////////////


                        System.out.println("开始执行1分钟后的运行逻辑 ：开启产水阀 + 开启产水泵" + flag);//定时任务
                        /////////////////////////////
                        Thread.sleep(100);
                        state = 1001;
                        ////////////////////////////
//               }else if(result>contributeWaterOneHourNumber && result>backwashOneHourNumber){

                        ////////////////////////////////////////////////////////////////////
                    } else if (flag >= 10) {
                        /////////////////////////////////////////////////////////////////////


                        //记录一次1小时的 contributeWaterOneHourNumber ++
                        contributeWaterOneHourNumber++;
                        //记录任务状态为 taskType = 1;
                        taskType = 1;
                        //记录产水任务结束状态 contributeWaterTaskType = 0;
                        contributeWaterTaskType = 0;
                        if (contributeWaterOneHourNumber % 2 != 0) {
                            System.out.println("开始执行对应产水阀的反洗");//定时任务

                            /////////////////////////////
                            Thread.sleep(100);
                            state = 1003;
                            ////////////////////////////

                        } else if (contributeWaterOneHourNumber % 2 == 0) {
                            System.out.println("开始执行对应产水阀的反洗");//定时任务

                            /////////////////////////////
                            Thread.sleep(100);
                            state = 1003;
                            ////////////////////////////

                        }
                    }
                } else {
                    ////////////////////////////////////
                    taskType = 2;
                    /////////////////////////////////////
                }


            } else if (1002 == state && 2 == taskType) { //当产水关闭成功状态返回，并且进入在线清洗
                //获取目标反洗阀数量 backwashMum
                System.out.println("远程开启1#反水阀");

                ////////////////////////////////////////
                state = 1003;
                ///////////////////////////////////////

            }


            //监听触发
            if (1003 == state && 1 == taskType) {
                //记录反洗任务开始状态 backwaskTaskType = 1
                backwaskTaskType = 1;
                System.out.println("开始执行1分钟后关闭反水阀的任务");//定时任务

                /////////////////////////////
                Thread.sleep(100);
                state = 1004;
                ////////////////////////////

            } else if (1004 == state && 1 == taskType) {
                //记录一次1小时的 backwashOneHourNumber ++
                backwashOneHourNumber++;
                //记录反洗任务结束状态 backwaskTaskType = 0
                backwaskTaskType = 0;
                System.out.println("远程开启产水阀 + 开启产水泵开关");

                /////////////////////////////
                state = 1001;
                taskType = 0;
                if (flag == 10) {
                    temp++;
                }
                flag = 0;
                ////////////////////////////

            } else if (1003 == state && 2 == taskType) {
                System.out.println("开启反水阀 X# 成功" + backwashNo);

                //是否为1
                if (1 == backwashNo) {

                    System.out.println("打开CIP药洗水泵");

                    /////////////////////////////////////
                    state = 1005;
                    ////////////////////////////////////

                } else {
                    System.out.println("开启关闭 backwashNo-- 的阀门");

                    ///////////////////////////////////////
                    state = 1004;
                    ///////////////////////////////////////
                }

            } else if (1004 == state && 2 == taskType) {
                //1分钟后开启下一个反水阀 backwashNo ++ 还要判断是否为最后一个阀门
                if (backwashNo == backwashMum) {
                    System.out.println("开启1分钟后关闭backwashNo  的阀门");//定时任务

                    /////////////////////////////////
                    state = 10041;
                    /////////////////////////////////

                } else {
                    System.out.println("开启1分钟后开启backwashNo ++ 的阀门");//定时任务

                    ///////////////////////////////////////
                    backwashNo ++;
                    state = 1003;
                    ///////////////////////////////////////
                }

            }

            //监听
            if(10041 == state && 2 == taskType){
                System.out.println("成功关闭最后一个阀门");//定时任务
                System.out.println("控制关闭CIP清洗泵");//定时任务

                ///////////////////////////////////////
                state = 1006;
                ///////////////////////////////////////
            }

            if(1006 == state && 2 == taskType){
                System.out.println("成功关闭CIP清洗泵");//定时任务
                return;
            }

            //监听触发
            if (1005 == state && 2 == taskType) {

                System.out.println("CIP药水开启成功，开启1分钟后的 backwashNo ++ 的阀门");

                ///////////////////////////////////////
                backwashNo ++;
                state = 1003;
                ///////////////////////////////////////

            }

        }

    }


}
