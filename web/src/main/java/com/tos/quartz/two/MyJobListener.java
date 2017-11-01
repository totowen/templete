package com.tos.quartz.two;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author qq136
 * @date 2017/10/31.
 */
public class MyJobListener implements JobListener {

    @Override
    public String getName() {
        return "MyJobListener";
    }
    /**
     * Scheduler 在 JobDetail 即将被执行，但又被 TriggerListener 否决了时调用这个方法。
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext arg0) {
        System.out.println("Job监听器：MyJobListener.jobExecutionVetoed()");
    }
    /**
     * Scheduler 在 JobDetail 将要被执行时调用这个方法。
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext arg0) {
        System.out.println("Job监听器：MyJobListener.jobToBeExecuted() "+arg0.getJobDetail().getKey().getName());

    }
    /**
     * Scheduler 在 JobDetail 被执行之后调用这个方法。
     */
    @Override
    public void jobWasExecuted(JobExecutionContext arg0,
                               JobExecutionException arg1) {
        System.out.println("Job监听器：MyJobListener.jobWasExecuted()");

    }

}
