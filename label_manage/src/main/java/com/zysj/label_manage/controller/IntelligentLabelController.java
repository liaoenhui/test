package com.zysj.label_manage.controller;/**
 * Created by 尚先生 on 2019/10/9.
 */

import com.zysj.label_manage.service.IntelligentLabelService;
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
 * @date 2019/10/9 15:20
 */
@RestController
@RequestMapping("intelligentLabel")
@Api(tags = "智能标签Api")
public class IntelligentLabelController {
    @Autowired
    IntelligentLabelService intelligentLabelService;
    /**
     * 获取类目列表
     */
    @RequestMapping(value = "/getObjectList", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有主体")
    public R ObjectList() {

        return intelligentLabelService.getObjectList();

    }
    /**
     * 根据主体id获取到对应的标签
     * @return
     */

    @ApiOperation(value = "根据主体id获取到对应的标签")
    @ApiImplicitParam(name = "ObjectId",value = "主体id",required = true,dataType="String",paramType = "query")
    @RequestMapping(value = {"/getSubjectLabel"}, method = RequestMethod.GET)
    public R getSubjectLabel(@RequestParam(value="ObjectId") String ObjectId){
        return intelligentLabelService.getSubjectLabel(ObjectId);
    }



}
