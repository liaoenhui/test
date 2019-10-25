package com.zysj.task_mananage.dao;

import com.zysj.task_mananage.entity.JobAndTriggerDto;
import javafx.scene.control.Pagination;

import java.util.List;

/**
 * @author lixin(1309244704 @ qq.com)
 * @version V1.0
 * @ClassName: JobAndTriggerMapper
 * @Description: TODO()
 * @date 2018年3月15日 上午10:02:48
 */
public interface JobAndTriggerMapper {

    List<JobAndTriggerDto> getJobAndTriggerDetails(Pagination page);

    JobAndTriggerDto getJobAndTriggerDto();
}
