package com.zysj.task_mananage.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author lixin(1309244704 @ qq.com)
 * @version V1.0
 * @ClassName: NewJob
 * @Description: TODO()
 * @date 2018年3月15日 上午10:02:34
 */
public class NewJob implements BaseJob {

    private static Logger _log = LoggerFactory.getLogger(NewJob.class);

    public NewJob() {

    }

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        _log.error("New Job执行时间: " + new Date());

    }
}  