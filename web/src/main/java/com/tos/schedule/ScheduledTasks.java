package com.tos.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by ALIENWARE on 2017/3/13.
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    long date = System.currentTimeMillis();

    Switch pneumatic1 =  new Switch("1#","0",new Date(),new Date(),Action.PRODUCEWATER);

    Switch pneumatic2 =  new Switch("2#","0",new Date(),new Date(),Action.PRODUCEWATER);

    Switch pneumatic3 =  new Switch("3#","0",new Date(),new Date(),Action.PRODUCEWATER);

    Switch pneumatic4 =  new Switch("4#","0",new Date(),new Date(),Action.PRODUCEWATER);

    Switch pump =  new Switch("水泵","0",new Date(),new Date(),Action.PRODUCEWATER);

    /**
     * 定时任务开启执行
     */
    private int flag = 0;

//    @Autowired
//    AddJob addJob;

    @PostConstruct
    public void inits() throws Exception {
//        addJob.execute();
    }


    /**
     * 产水任务初始化
     */
//    @Scheduled(fixedRate = 5000)
    public void init() throws Exception {
        if(flag==0){
            flag = 1;
            System.out.println("init 现在时间：" + dateFormat.format(date));
            open();
        }
//        addJob.execute();

    }




//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        if(flag==0){
            System.out.println("现在时间：" + dateFormat.format(date));
            flag = 1;
           //假定有个共享时间，从这个共享时间开始判断任务是否进行
           //给初始化时间
            Date date = new Date(this.date);
            //设置推迟多少时间后（秒）
            Date time = getTaskDate(date, 10);
            //设置定时任务触发时间，和任务的时间间隔
            startCron(time);
       }

    }

    private Date getTaskDate(Date date,int seconds){
        Calendar Cal=Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(java.util.Calendar.SECOND,seconds);
        Date time = Cal.getTime();
        System.out.println("date:"+dateFormat.format(time));
        return time;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public String startCron(Date date) {
        future = threadPoolTaskScheduler.schedule(new AfterNineRunnable(),date);
        System.out.println("DynamicTask.startCron()");
        return "startCron";
    }

    public String afterNine(Date date){
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new AfterNineRunnable(),date);
        System.out.println("DynamicTask.produceWater()");
        return "produceWater";
    }

    public String afterOne(Date date){
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new AfterOneRunnable(),date);
        System.out.println("DynamicTask.backWashing()");
        return "backWashing";
    }

    public String stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTask.stopCron()");
        return "stopCron";
    }

    public String startCron10() {
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTask.startCron10()");
        return "changeCron10";
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("DynamicTask.MyRunnable.run()，" + dateFormat.format(new Date()));
//            stopCron();
        }
    }

    private class AfterNineRunnable implements  Runnable {
        @Override
        public void run() {
            System.out.println("9分钟后开始执行关闭操作，" + dateFormat.format(new Date()));
            close();
        }
    }



    private class AfterOneRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("1分钟后开始执行关闭操作，" + dateFormat.format(new Date()));
            //开启产水气动阀 + 开启水泵，并返回是否开关状态
            open();
        }
    }

    private void open() {
        //开启产水气动阀 + 开启水泵，并返回是否开关状态
        pneumatic1.setType("1");
        pneumatic2.setType("1");
        pneumatic3.setType("1");
        pneumatic4.setType("1");
        pump.setType("1");
        //返回开启成功执行后，记录开关时间
        Date date = new Date();
        pneumatic1.setGetTime(date);
        pneumatic2.setGetTime(date);
        pneumatic3.setGetTime(date);
        pneumatic4.setGetTime(date);
        pump.setGetTime(date);
        //如果是开启动作则 推迟时间为9分钟后执行 关闭水泵 + 关闭产水气动阀
        //设置推迟多少时间后（秒）
        Date time = getTaskDate(date, 9*10);
        pneumatic1.setDelayTime(time);
        pneumatic2.setDelayTime(time);
        pneumatic3.setDelayTime(time);
        pneumatic4.setDelayTime(time);
        pump.setDelayTime(time);
        //设置定时任务触发时间，和任务的时间间隔
        afterNine(time);
    }

    private void close() {
        //关闭水泵 + 关闭产水气动阀 ，并返回是否关闭状态
        pump.setType("0");
        pneumatic1.setType("0");
        pneumatic2.setType("0");
        pneumatic3.setType("0");
        pneumatic4.setType("0");
        //返回开启成功执行后，记录开关时间
        Date date = new Date();
        pneumatic1.setGetTime(date);
        pneumatic2.setGetTime(date);
        pneumatic3.setGetTime(date);
        pneumatic4.setGetTime(date);
        pump.setGetTime(date);
        //如果是开启动作则 推迟时间为9分钟后执行 关闭水泵 + 关闭产水气动阀
        //设置推迟多少时间后（秒）
        Date time = getTaskDate(date, 6);
        pneumatic1.setDelayTime(time);
        pneumatic2.setDelayTime(time);
        pneumatic3.setDelayTime(time);
        pneumatic4.setDelayTime(time);
        pump.setDelayTime(time);
        //设置定时任务触发时间，和任务的时间间隔
        afterOne(time);
    }

}
