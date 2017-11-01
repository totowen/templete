package com.tos.quartz.one;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author qq136
 * @date 2017/10/30.
 */


/**
 * 定时任务运行工厂类
 *
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job{

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]" +dateFormat.format(new Date()));
    }

}
