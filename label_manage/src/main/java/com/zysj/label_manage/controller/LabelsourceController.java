package com.zysj.label_manage.controller;

import com.zysj.label_manage.entity.ObjectClass;
import com.zysj.label_manage.entity.TagObject;
import com.zysj.label_manage.service.LabelsourceService;
import com.zysj.service_common.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
@RestController
@RequestMapping("labelsource")
@Api(tags = "标签模型数据api")
public class LabelsourceController {


    @Autowired
    private LabelsourceService labelsourceService;


    /**
     * 获取标签模型列表数据
     */

    @RequestMapping(value = {"/getLabelSourceList/{OBJECT_CLASS_ID}"}, method = RequestMethod.GET)
    @ApiImplicitParam(name = "OBJECT_CLASS_ID",value = "其中最后跟的是父节点的id 需要根据父节点id查到下面的所有子id",required = true,dataType="String",paramType = "path")
    @ApiOperation(value = "获取标签模型列表数据")

    public R getLabelSourceList(@PathVariable("OBJECT_CLASS_ID")  String OBJECT_CLASS_ID){
       List<TagObject> tagObjectList  = labelsourceService.getLabelsourceList( OBJECT_CLASS_ID);
      int count=tagObjectList.size();

        return R.ok().put("list",tagObjectList).put("count",count);
    }

    /**
     * 获取类目列表
     */
    @RequestMapping(value = "/getlevelNode/{OBJECT_CLASS_ID}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "OBJECT_CLASS_ID",value = "其中最后跟的是父节点的id 获取所有父节点 需要传0",required = true,dataType="String",paramType = "path")
    @ApiOperation(value = "获取节点数据")
    public R levelNode(@PathVariable("OBJECT_CLASS_ID")  String OBJECT_CLASS_ID) {

            return labelsourceService.getlevelNode(OBJECT_CLASS_ID);


    }

    /**
     * 新增主体
     * @param tagObject
     * @return
     */
    @RequestMapping(value = {"/addLabelSourceInfo"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增主体 ")
    @ApiImplicitParam(name = "tagObject", value = "主体实体tb_tag_object", required = true, dataType = "TagObject")
    public R addLabelSourceInfo(@RequestBody TagObject tagObject){
        return labelsourceService.addSubjectSourceInfo(tagObject);
    }

    /**
     * 新增类目
     * @param objectClass
     * @return
     */
    @RequestMapping(value = {"/addLevelNode"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增主体类目 ")
    @ApiImplicitParam(name = "objectClass", value = "主体实体td_tag_object_class", required = true, dataType = "ObjectClass")
    public R addLevelNode(@RequestBody ObjectClass objectClass){
        String str = objectClass.getPARENT_ID();

       if (str.equals("")||str==null){
           return R.ok().put("list","parent_ID为空请再次确认");
       }else {
           return labelsourceService.addLevelNodeInfo(objectClass);
       }



    }
    /**
     * 获取所有主体
     * @return
     */

    @RequestMapping(value = {"/getLabelSources"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取所有主体信息供列表选择")
    public R getLabelSources(){
        return labelsourceService.getLabelSources();
    }
    /**
     * 获取单个主体信息
     * @return
     */

    @RequestMapping(value = {"/getLabelSourceInfo/{OBJECT_ID}"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取单个主体信息")
    @ApiImplicitParam(name = "OBJECT_ID",value = "关联主体id",dataType="String",paramType = "path")
    public R getLabelSourceInfo(@PathVariable("OBJECT_ID")  String OBJECT_ID){
        return labelsourceService.getLabelsourceInfo(OBJECT_ID);
    }
    /**
     * 删除主体
     * @param objectId
     * @return
     */
    @RequestMapping(value = {"/deleteLabelSourceInfo"}, method = RequestMethod.POST)
    @ApiOperation(value = "删除主体 (10.24)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", value = "关系Code ", required = true, dataType = "String",paramType = "query")
    })
    public R deleteLabelSourceInfo(@RequestParam(value="objectId")String objectId){
        return labelsourceService.deleteLabelSourceInfo(objectId);
    }
    /**
     * 删除主体类目
     * @param objectClassId
     * @return
     */
    @RequestMapping(value = {"/deleteLevelNodeInfo"}, method = RequestMethod.POST)
    @ApiOperation(value = "删除主体类目 (10.24)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectClassId", value = "主体类目id ", required = true, dataType = "String",paramType = "query")
    })
    public R deleteLevelNodeInfo(@RequestParam(value="objectClassId")String objectClassId){
        return labelsourceService.deleteLevelNodeInfo(objectClassId);
    }
}
