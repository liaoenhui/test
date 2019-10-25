package com.zysj.task_mananage.controller;

import com.zysj.task_mananage.service.IJobAndTriggerService;
import com.zysj.task_mananage.utils.PageUtil;
import com.zysj.task_mananage.utils.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author lixin(1309244704 @ qq.com)
 * @version V1.0
 * @ClassName: JobController
 * @Description: TODO()
 * @date 2018年8月15日 上午10:02:00
 */
@Controller
@RequestMapping(value = "/job")
public class JobController extends BaseController {
    @Autowired
    private IJobAndTriggerService jobAndTriggerService;

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryjob(PageUtil search, HttpServletRequest request, HttpServletResponse response) {
        log.debug("queryjob");
        setHeader(response);
        search.setParams(getParams(request));
        return jobAndTriggerService.getPageJob(search);
    }

    /**
     * @param jobClassName   类名
     * @param jobGroupName   组名
     * @param cronExpression 表达式，如：0/5 * * * * ? (每隔5秒)
     * @Title: addJob
     * @Description: TODO(添加Job)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addJob(
            @RequestParam(value = "jobClassName") String jobClassName,
            @RequestParam(value = "jobGroupName") String jobGroupName,
            @RequestParam(value = "cronExpression") String cronExpression,
            HttpServletResponse response) {
        try {
            setHeader(response);
            jobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression);
            return ResponseEntity.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.FAILURE(e.getMessage());
        }
    }

    /**
     * @param jobClassName 类名
     * @param jobGroupName 组名
     * @Title: pauseJob
     * @Description: TODO(暂停Job)
     */
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity pauseJob(
            @RequestParam(value = "jobClassName") String jobClassName,
            @RequestParam(value = "jobGroupName") String jobGroupName,
            HttpServletResponse response) {
        try {
            setHeader(response);
            jobAndTriggerService.pauseJob(jobClassName, jobGroupName);
            return ResponseEntity.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.FAILURE(e.getMessage());
        }
    }

    /**
     * @param jobClassName 类名
     * @param jobGroupName 组名
     * @Title: resumeJob
     * @Description: TODO(恢复Job)
     */
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity resumeJob(
            @RequestParam(value = "jobClassName") String jobClassName,
            @RequestParam(value = "jobGroupName") String jobGroupName,
            HttpServletResponse response) {
        try {
            setHeader(response);
            jobAndTriggerService.resumejob(jobClassName, jobGroupName);
            return ResponseEntity.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.FAILURE(e.getMessage());
        }
    }

    /**
     * @param jobClassName   类名
     * @param jobGroupName   组名
     * @param cronExpression 表达式
     * @Title: rescheduleJob
     * @Description: TODO(重新设置Job)
     */
    @RequestMapping(value = "/reschedule", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity rescheduleJob(
            @RequestParam(value = "jobClassName") String jobClassName,
            @RequestParam(value = "jobGroupName") String jobGroupName,
            @RequestParam(value = "cronExpression") String cronExpression,
            HttpServletResponse response) {
        try {
            setHeader(response);
            jobAndTriggerService.updateJob(jobClassName, jobGroupName, cronExpression);
            return ResponseEntity.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.FAILURE(e.getMessage());
        }
    }

    /**
     * @param jobClassName   类名
     * @param jobGroupName   组名
     * @param cronExpression 表达式
     * @Title: deleteJob
     * @Description: TODO(删除Job)
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity deleteJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName,
                             HttpServletResponse response) {
        try {
            setHeader(response);
            jobAndTriggerService.deleteJob(jobClassName, jobGroupName);
            return ResponseEntity.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.FAILURE(e.getMessage());
        }
    }

	
	/*@PostMapping(value = "/addjob")
	public void addjob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
			jobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression);
	}

	@PostMapping(value = "/pausejob")
	public void pausejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		jobAndTriggerService.pauseJob(jobClassName, jobGroupName);
	}

	@PostMapping(value = "/resumejob")
	public void resumejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		jobAndTriggerService.resumejob(jobClassName, jobGroupName);
	}

	@PostMapping(value = "/reschedulejob")
	public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		jobAndTriggerService.updateJob(jobClassName, jobGroupName, cronExpression);
	}

	@PostMapping(value = "/deletejob")
	public void deletejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		jobAndTriggerService.deleteJob(jobClassName, jobGroupName);
	}
*/

}
