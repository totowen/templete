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
public class ContributeWaterOpenJob implements Job{

    public ContributeWaterOpenJob(){
        System.out.println("ContributeWaterOpenJob创建成功");
    }

    //执行产水阀开启 + 对泵开启
    @Override
    public void execute(JobExecutionContext arg0)
            throws JobExecutionException {
        System.out.println("Hello ContributeWaterOpenJob  "+
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));

    }

}
