package com.zysj.label_manage.controller;/**
 * Created by 尚先生 on 2019/10/16.
 */

import com.zysj.label_manage.service.TagDataConsulService;
import com.zysj.service_common.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 尚先生
 * @date 2019/10/16 14:25
 */
@RestController
@RequestMapping("tagDataConsul")
@Api(tags = "圈群pg数据库部分Api")
public class TagDataConsulController {
    @Autowired
    TagDataConsulService tagDataConsulService;

    @ApiImplicitParam(name = "sConditions", value = "json字符", required = true, dataType = "String",paramType = "query")
    @RequestMapping(value = "/getTagDataQuery", method = RequestMethod.POST)
    @ApiOperation(value = " json部分主体")
    public R getTagDataQuery(@RequestParam(value = "sConditions") String sConditions) {
        return tagDataConsulService.circleGroup(sConditions);
    }


}