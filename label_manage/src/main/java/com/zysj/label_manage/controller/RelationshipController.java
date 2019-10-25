package com.zysj.label_manage.controller;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.zysj.label_manage.entity.ObjectInner;
import com.zysj.label_manage.service.RelationshipService;
import com.zysj.service_common.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 尚先生
 * @date 2019/9/24 9:11
 */
@RestController
@RequestMapping("relationShip")
@Api(tags = "关系模型数据api")
public class RelationshipController {
    @Autowired
    RelationshipService relationshipService;

    /**
     * 新增关系
     * @param objectInner 关系实体
     * @return
     */

    @RequestMapping(value = {"/addRelationShipSourceInfo"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增关系")
    @ApiImplicitParam(name = "objectInner", value = "实体tb_tag_object_inner", required = true, dataType = "ObjectInner")
    public R addRelationShipSourceInfo(@RequestBody ObjectInner objectInner){

        return relationshipService.addRelationShipSourceInfo(objectInner);
    }

    /**
     * 内关系 列表
     * @param objectId 主体id
     * @return
     */
    @RequestMapping(value = {"/getInternalRelationsInfo/{objectId}"}, method = RequestMethod.GET)
    @ApiOperation(value = "内关系")
    @ApiImplicitParam(name = "objectId", value = "主体的id", required = true, dataType = "String",paramType = "path")
    public R getInternalRelationsInfo(@PathVariable("objectId") String objectId){

        return relationshipService.getInternalRelationsInfo(objectId);
    }

    /**
     * 查询是否存在该关系Code
     * @param fieldName 关系code
     * @return
     */
    @RequestMapping(value = {"/getWhetherField"}, method = RequestMethod.GET)
    @ApiOperation(value = "查询是否存在该关系Code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fieldName", value = "关系Code ", required = true, dataType = "String",paramType = "query")
    })
    public R getWhetherField( @RequestParam(value="fieldName") String fieldName){

        return relationshipService.getWhetherField(fieldName);

    }

}
