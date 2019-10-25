//package com.zysj.task_mananage.service.impl;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.zysj.task_mananage.dao.JobAndTriggerMapper;
//import com.zysj.task_mananage.entity.JobAndTriggerDto;
//import com.zysj.task_mananage.job.BaseJob;
//import com.zysj.task_mananage.service.IJobAndTriggerService;
//import com.zysj.task_mananage.utils.PageUtil;
//import javafx.scene.control.Pagination;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
///**
// * @author lixin(1309244704 @ qq.com)
// * @version V1.0
// * @ClassName: JobAndTriggerImpl
// * @Description: TODO()
// * @date 2018年3月15日 上午10:03:00
// */
//@Service
//public class JobAndTriggerImpl implements IJobAndTriggerService {
//
//    @Autowired
//    private Scheduler scheduler;
//
//
//    @Autowired
//    // 加入Qulifier注解，通过名称注入bean
//    //	@Qualifier("Scheduler")
//    private JobAndTriggerMapper jobAndTriggerMapper;
//
//
//    public Map<String, Object> getPageJob(PageUtil search) {
//        Pagination page = new Page<Object>(search.getPage(), search.getRows(), search.getSort());
//        List<JobAndTriggerDto> records = jobAndTriggerMapper.getJobAndTriggerDetails(page);
//        return search.getResultMap(page.getTotal(), records);
//    }
//
//    @Override
//    public JobAndTriggerDto getPageJobmod() {
//        return jobAndTriggerMapper.getJobAndTriggerDto();
//    }
//
//    @Override
//    public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
//        // 启动调度器
//        scheduler.start();
//
//        // 构建job信息
//        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
//                .withIdentity(jobClassName, jobGroupName).build();
//
//        // 表达式调度构建器(即任务执行的时间)
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
//
//        // 按新的cronExpression表达式构建一个新的trigger
//        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
//                .withSchedule(scheduleBuilder).build();
//
//        try {
//            scheduler.scheduleJob(jobDetail, trigger);
//            System.out.println("创建定时任务成功");
//
//        } catch (SchedulerException e) {
//            System.out.println("创建定时任务失败" + e);
//            throw new Exception("创建定时任务失败");
//        }
//
//    }
//
//    @Override
//    public void addJob(String jobClassName, String jobGroupName, String cronExpression, String jobDescription,
//                       Map<String, Object> params) throws Exception {
//
//        // 启动调度器
//        scheduler.start();
//
//        // 构建job信息
//        JobDetail jobDetail = JobBuilder.newJob(JobAndTriggerImpl.getClass(jobClassName).getClass())
//                .withIdentity(jobClassName, jobGroupName).withDescription(jobDescription).build();
//        Iterator<Entry<String, Object>> var7 = params.entrySet().iterator();
//        while (var7.hasNext()) {
//            Entry<String, Object> entry = var7.next();
//            jobDetail.getJobDataMap().put((String) entry.getKey(), entry.getValue());
//        }
//        System.out.println("jobDetail数据：--------" + jobDetail.toString());
//        // 表达式调度构建器(即任务执行的时间)
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
//
//        // 按新的cronExpression表达式构建一个新的trigger
//        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
//                .withSchedule(scheduleBuilder).build();
//
//        try {
//            scheduler.scheduleJob(jobDetail, trigger);
//            System.out.println("创建定时任务成功");
//
//        } catch (SchedulerException e) {
//            System.out.println("创建定时任务失败" + e);
//            throw new Exception("创建定时任务失败");
//        }
//    }
//
//    @Override
//    public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
//        try {
//            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
//            // 表达式调度构建器（动态修改后不立即执行）
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
//
//            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//            // 按新的cronExpression表达式重新构建trigger
//            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//
//            // 按新的trigger重新设置job执行
//            scheduler.rescheduleJob(triggerKey, trigger);
//        } catch (SchedulerException e) {
//            System.out.println("更新定时任务失败" + e);
//            throw new Exception("更新定时任务失败");
//        }
//    }
//
//    @Override
//    public void deleteJob(String jobClassName, String jobGroupName) throws Exception {
//        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
//        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
//        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
//    }
//
//    @Override
//    public void pauseJob(String jobClassName, String jobGroupName) throws Exception {
//        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
//    }
//
//    @Override
//    public void resumejob(String jobClassName, String jobGroupName) throws Exception {
//        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
//    }
//
//    public static BaseJob getClass(String classname) throws Exception {
//        Class<?> class1 = Class.forName(classname);
//        return (BaseJob) class1.newInstance();
//    }
//
//}