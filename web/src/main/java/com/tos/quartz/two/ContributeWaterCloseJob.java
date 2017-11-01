package com.tos.quartz.two;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qq136
 * @date 2017/10/31.
 */
@DisallowConcurrentExecution
public class ContributeWaterCloseJob  implements Job {

    public ContributeWaterCloseJob(){
        System.out.println("ContributeWaterCloseJob创建成功");
    }

    //对泵关闭 + 执行产水阀关闭
    @Override
    public void execute(JobExecutionContext arg0)
            throws JobExecutionException {
        System.out.println("Hello ContributeWaterCloseJob  "+
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));

    }

}
