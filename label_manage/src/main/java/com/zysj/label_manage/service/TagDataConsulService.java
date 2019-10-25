package com.zysj.label_manage.service;/**
 * Created by 尚先生 on 2019/10/16.
 */

import com.zysj.service_common.common.utils.R;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 尚先生
 * @date 2019/10/16 14:11
 */

public interface TagDataConsulService {

    /**
     * 圈群部分接口
     * @param sConditions
     * @return
     */
   R circleGroup(@RequestParam(value = "sConditions")String sConditions);
    /**
     * 圈群部分保存pg
     * @param origin
     * @param groupId
     * @return
     */
   String saveAnalyzedGroup(String origin,String groupId);

}
