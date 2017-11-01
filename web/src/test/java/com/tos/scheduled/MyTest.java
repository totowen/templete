package com.tos.scheduled;

import com.tos.quartz.one.QuartzConfig;
import com.tos.quartz.two.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.GroupMatcher.triggerGroupEquals;
import static org.quartz.impl.matchers.KeyMatcher.*;
import static org.quartz.impl.matchers.EverythingMatcher.*;
/**
 * @author qq136
 * @date 2017/10/31.
 */
public class MyTest {

    public static void main(String[] args) {

        try {

            //3、创建Scheduler
            Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            /**
             * 1.初始化所有开关为关闭状态
             * 2.判断当前所有开关是否为关闭状态
             *    --》是 进入开启产水阀 + 开启产水泵开关
             *        --》返回开启成功状态
             *           --》开始执行9分钟后的运行逻辑 ：关闭产水泵 + 关闭产水阀
             *               --》返回关闭成功状态
             *                  --》开始执行1分钟后的运行逻辑 ：开启产水阀 + 开启产水泵
             *               --》返回关闭不成功状态
             *        --》返回开启不成功状态
             *    --》否
             */

           /* if(判断所有开关为关闭状态){
                System.out.println("初始化成功");
            }else{
                System.out.println("调用所有关闭开关接口");
            }


            // 任务状态初始化是0 taskType = 0(产水),1(反洗),2(在线清洗)
            while (true){ //定时任务
                if(状态为清洗 并且 0 == taskType){
                    //记录产水开始时间 contributeWaterStartTime
                    //记录产水任务开始状态 contributeWaterType = 1;
                    System.out.println("远程开启产水阀 + 开启产水泵开关");
                    //记录MBR开启时间 sysStartTime
                }else if(系统时间运行到24小时){
                    //检测当前任务状态
                    if(0 == taskType){
                        taskType = 2
                        System.out.println("远程关闭产水泵 + 关闭产水阀");
                    }else if(1 == taskType){
                        taskType = 2
                        System.out.println("远程关闭所有反水阀门");
                    }
                }

                //监听触发
                if(目标产水开启成功状态返回 且 0 == taskType){
                    System.out.println("开始执行9分钟后的运行逻辑 ：关闭产水泵 + 关闭产水阀 ");
                }else if(目标产水关闭返回状态成功 且 0 == taskType){
                    //记录产水运行结束时间  contributeWaterEndTime
                    //计算开机运行时长 sysRunningTime =  new Date - sysStartTime
                    //进入反水的逻辑为 result = sysRunningTime/60, result>contributeWaterOneHourNumber 且 result>backwashOneHourNumber
                    if(result<=contributeWaterOneHourNumber){
                        System.out.println("开始执行1分钟后的运行逻辑 ：开启产水阀 + 开启产水泵");//定时任务
                    }else if(result>contributeWaterOneHourNumber 且 result>backwashOneHourNumber){
                        //记录一次1小时的 contributeWaterOneHourNumber ++
                        //记录任务状态为 taskType = 1;
                        //记录产水任务结束状态 contributeWaterType = 0;
                        if(一小时的次数为奇数倍){
                            System.out.println("开始执行对应产水阀的反洗");//定时任务
                        }else if(一小时的次数为偶数倍){
                            System.out.println("开始执行对应产水阀的反洗");//定时任务
                        }
                    }
                }else if(目标产水关闭返回状态成功 且 2 == taskType){
                    //获取目标反洗阀数量 backwashMum
                    System.out.println("远程开启1#反水阀");

                }


                //监听触发
                if(目标返回反洗阀开启 且 1 == taskType){
                    //记录反洗任务开始状态 backwaskType = 1
                    System.out.println("开始执行1分钟后关闭反水阀的任务");//定时任务
                }else if(目标返回反洗阀关闭 1 == taskType){
                    //记录一次1小时的 backwashOneHourNumber ++
                    //记录反洗任务结束状态 backwashType = 0
                    System.out.println("远程开启产水阀 + 开启产水泵开关");
                }else if(目标反洗阀开启成功 且 2 == taskType){
                    //开启1#反水阀  backwashNo
                    System.out.println("打开CIP药洗水泵");
                    if(有两个以上阀门开启，关闭数字小的反洗阀门，保留一个){
                        System.out.println("关闭除最大编号外的其他阀门");
                    }else if(只有一个阀门，){

                    }
                }else if(目标反洗阀关闭成功 且 2 == taskType){
                    //1分钟后开启下一个反水阀 backwashNo ++ 还要判断是否为最后一个阀门
                    if(如果是最后一个阀门){
                        System.out.println("开启1分钟后关闭backwashNo  的阀门");//定时任务
                    }else if(所有阀门都关闭了){
                        System.out.println("开启关闭CIP清洗泵");//定时任务
                    }else{
                        System.out.println("开启1分钟后开启backwashNo ++ 的阀门");//定时任务
                    }
                }

                //监听触发
                if(目标CIP药洗水泵开启成功 且 2 == taskType){
                    //1分钟后开启下一个反水阀 backwashNo ++
                    System.out.println("开启1分钟后开启backwashNo ++ 的阀门");//定时任务
                }

            }*/

            task("job1_1","jobGroup1",
                    "trigger1_1","triggerGroup1",90,scheduler,ContributeWaterOpenJob.class);
            task("job1_2","jobGroup1",
                    "trigger1_2","triggerGroup1",10,scheduler,ContributeWaterCloseJob.class);
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void task(String jobName,String jobGroup,String triggerName,String triggerGroup
            ,int futureDateInterval,Scheduler scheduler,Class jobClass) throws SchedulerException {
        // 1、创建一个JobDetail实例，指定Quartz
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                // 任务执行类
                .withIdentity(jobName, jobGroup)
                // 任务名，任务组
                .build();
        //2、创建Trigger
            /*SimpleScheduleBuilder builder=SimpleScheduleBuilder.simpleSchedule()
                    //设置间隔执行时间
                    .withIntervalInSeconds(5)
                    //设置执行次数
                    .repeatForever();
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity(
                    "trigger1_1","tGroup1").startNow().withSchedule(builder).build();*/
        Trigger trigger = (SimpleTrigger) newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .startAt(futureDate(futureDateInterval, DateBuilder.IntervalUnit.SECOND))
                .forJob(jobDetail.getKey()) /* job的jobKey*/
                .build();
        //添加TriggerListener监听器
        MyTriggerListener myTriggerListener=new MyTriggerListener();
        //  监听器所有的job
        //scheduler.getListenerManager().addTriggerListener(myTriggerListener, allTriggers());
        //  监听部分的job
//            scheduler.getListenerManager().addTriggerListener(myTriggerListener, keyEquals(new TriggerKey("trigger1_1","tGroup1")));
        //  监听特定组的job
        scheduler.getListenerManager().addTriggerListener(myTriggerListener, triggerGroupEquals("tGroup1"));
        // 添加JobListener监听器
        MyJobListener myJobListener=new MyJobListener();
        scheduler.getListenerManager()
                .addJobListener(myJobListener, allJobs());
        // 监听部分的job
        //scheduler.getListenerManager()
        //.addJobListener(myJobListener, keyEquals(jobKey("myJobName", "myJobGroup")));
        //监听特定组的job
        //scheduler.getListenerManager()
        //.addJobListener(myJobListener, groupEquals("myJobGroup"));

        //添加监听
        MySchedulerListener schedulerListener = new MySchedulerListener();
        scheduler.getListenerManager().addSchedulerListener(schedulerListener);

        //4、调度执行
        scheduler.scheduleJob(jobDetail, trigger);
    }


}
