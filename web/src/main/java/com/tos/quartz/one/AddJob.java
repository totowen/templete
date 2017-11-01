package com.tos.quartz.one;
import com.tos.quartz.two.MyJobListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

/**
 * @author qq136
 * @date 2017/10/30.
 * 具体的添加任务
 */
@Service
public class AddJob {

    com.tos.quartz.one.QuartzConfig QuartzConfig=new QuartzConfig();

    public void execute() throws Exception{
        Scheduler scheduler = QuartzConfig.scheduler();
        //注册监听器
        MyJobListener myJobListener=new MyJobListener();
        scheduler.getListenerManager()
                .addJobListener(myJobListener, allJobs());

        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(){
            private static final long serialVersionUID = 2596601829579886713L;
            {
                ScheduleJob job = new ScheduleJob();
                job.setJobId("10001");
                job.setJobGroup("组1");
                job.setJobName("任务一");
                job.setJobStatus("1");
                job.setCronExpression("0/5 * * * * ?");
                job.setDesc("数据导入任务");
                this.add(job);

                ScheduleJob job1 = new ScheduleJob();
                job1.setJobGroup("组2");
                job1.setJobName("任务二");
                job1.setJobId("10002");
                job1.setJobStatus("1");
                job1.setCronExpression("0/8 * * * * ?");
                job1.setDesc("数据导入任务");
                this.add(job1);
            }
        };
        for (ScheduleJob job : jobList) {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在，创建一个
            if (trigger==null) {
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());

                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());

                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();

                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
    }
}
