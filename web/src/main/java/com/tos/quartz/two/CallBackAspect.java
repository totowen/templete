package com.tos.quartz.two;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author qq136
 * @date 2017/10/31.
 *
 * 在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
 * 在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容
 * 在切入点前的操作，按order的值由小到大执行
 * 在切入点后的操作，按order的值由大到小执行
 */
@Aspect
@Component
public class CallBackAspect {

    private Logger logger = Logger.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.tos.controller.*.*(..))")
    public void webLog() {
    }

    /**
     *
     * @param joinPoint
     * @throws Throwable
     *
     * @Order(5)
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

    }

    /**
     *
     * @param ret
     * @throws Throwable
     *
     * @Order(10)
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
/*

        //获取开启状态
        int state = 1002;
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
        Integer backwashMum = 0;

        //监听触发
        if(1001==state && 0 == taskType){
            System.out.println("开始执行9分钟后的运行逻辑 ：关闭产水泵 + 关闭产水阀 ");
        }else if(1002==state && 0 == taskType){
            //记录产水运行结束时间  contributeWaterEndTime
            contributeWaterEndTime = new Date();
            //计算开机运行时长 sysRunningTime =  new Date - sysBootTime
            long l = (new Date()).getTime() - sysBootTime.getTime();
            sysRunningTime = TimeUnit.MINUTES.toMinutes(l);
            //进入反水的逻辑为 result = sysRunningTime/60, result>contributeWaterOneHourNumber 且 result>backwashOneHourNumber
            long result = sysRunningTime / 60;

            if(result<=contributeWaterOneHourNumber){
                System.out.println("开始执行1分钟后的运行逻辑 ：开启产水阀 + 开启产水泵");//定时任务
            }else if(result>contributeWaterOneHourNumber && result>backwashOneHourNumber){
                //记录一次1小时的 contributeWaterOneHourNumber ++
                contributeWaterOneHourNumber ++;
                //记录任务状态为 taskType = 1;
                taskType = 1;
                //记录产水任务结束状态 contributeWaterTaskType = 0;
                contributeWaterTaskType = 0;
                if(contributeWaterOneHourNumber%2!=0){
                    System.out.println("开始执行对应产水阀的反洗");//定时任务
                }else if(contributeWaterOneHourNumber%2==0){
                    System.out.println("开始执行对应产水阀的反洗");//定时任务
                }
            }
        }else if(1002==state && 2==taskType){
            //获取目标反洗阀数量 backwashMum
            System.out.println("远程开启1#反水阀");

        }


        //监听触发
        if(1003==state && 1 == taskType){
            //记录反洗任务开始状态 backwaskTaskType = 1
            backwaskTaskType = 1;
            System.out.println("开始执行1分钟后关闭反水阀的任务");//定时任务
        }else if(1004==state && 1 == taskType){
            //记录一次1小时的 backwashOneHourNumber ++
            backwashOneHourNumber ++;
            //记录反洗任务结束状态 backwaskTaskType = 0
            backwaskTaskType = 0;
            System.out.println("远程开启产水阀 + 开启产水泵开关");
        }else if(1003==state && 2 == taskType){
           */
/* //开启1#反水阀  backwashNo
            System.out.println("打开CIP药洗水泵");
            if(有两个以上阀门开启，关闭数字小的反洗阀门，保留一个){
                System.out.println("关闭除最大编号外的其他阀门");
            }else if(只有一个阀门，){

            }*//*

        }else if(1004==state && 2 == taskType){
            */
/*//*
/1分钟后开启下一个反水阀 backwashNo ++ 还要判断是否为最后一个阀门
            if(如果是最后一个阀门){
                System.out.println("开启1分钟后关闭backwashNo  的阀门");//定时任务
            }else if(所有阀门都关闭了){
                System.out.println("开启关闭CIP清洗泵");//定时任务
            }else{
                System.out.println("开启1分钟后开启backwashNo ++ 的阀门");//定时任务
            }*//*

        }

        //监听触发
        if(1004==state && 2 == taskType){
            //1分钟后开启下一个反水阀 backwashNo ++
            System.out.println("开启1分钟后开启backwashNo ++ 的阀门");//定时任务
        }
*/


        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

}
