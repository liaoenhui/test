package com.zysj.task_mananage.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements BaseJob {

    private static Logger _log = LoggerFactory.getLogger(MyJob.class);


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
