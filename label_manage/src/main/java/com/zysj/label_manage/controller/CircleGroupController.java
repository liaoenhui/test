package com.zysj.label_manage.controller;/**
 * Created by 尚先生 on 2019/10/15.
 */

import com.zysj.label_manage.entity.TbGroup;
import com.zysj.label_manage.service.CircleGroupService;
import com.zysj.service_common.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 尚先生
 * @date 2019/10/15 16:09
 */
@RestController
@RequestMapping("circleGroup")
@Api(tags = "圈群部分Api")
public class CircleGroupController {
    @Autowired
    CircleGroupService circleGroupService;


    /**
     * 根据主体id查询到关系
     */
    @RequestMapping(value = "/getSubjectTagCategoryList", method = RequestMethod.GET)
    @ApiImplicitParam(name = "obJctId",value = "主体id",required = true,dataType="String",paramType = "query")
    @ApiOperation(value = "获取主体列表在/intelligentLabel/getObjectList  根据主体以及类目获取到标签在/detailsSubject/getTagDetails 主体获取标签的类目在/detailsSubject/getTagNode  该方法为根据主体id查询到关系")
    public R SubjectTagCategoryList(@RequestParam(value="obJctId")  String obJctId) {
        return circleGroupService.getSubjectTagCategoryList(obJctId);
    }

    /**
     * 根据标签获取到关联主体
     */
    @RequestMapping(value = "/getTagSubjectCategoryList", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectId", value = "主体id", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "relationshipId", value = "关系id",  dataType = "String",paramType = "query")
    })
    @ApiOperation(value = " 该方法为根据标签id找到对应的主体")
    public R TagSubjectCategoryList(@RequestParam(value="subjectId")  String subjectId,@RequestParam(value="relationshipId")  String relationshipId ) {
        return circleGroupService.getTagSubjectCategoryList(subjectId,relationshipId);
    }

    /**
     * 新增群体
     * @param tbGroup 群体的实体
     * @return
     */
    @ApiOperation(value = "新增群体")
    @RequestMapping(value = {"/addDatasourceInfo"}, method = RequestMethod.POST)
    public R addDatasourceInfo(@ApiParam @RequestBody TbGroup tbGroup){
        return circleGroupService.addGroup(tbGroup);
    }
    /**
     * 修改群体
     * @param tbGroup
     * @return
     */
    @ApiOperation(value = "修改群体")
    @RequestMapping(value = {"/updateEditTbGroupInfo"}, method = RequestMethod.POST)
    public R updateEditTbGroupInfo(@ApiParam @RequestBody TbGroup tbGroup){
        return circleGroupService.updateEditTbGroupInfo(tbGroup);
    }

    /**
     * 模糊查询群体
     * @param groupName
     * @return
     */
    @ApiOperation(value = "模糊查询群体名称")
    @RequestMapping(value = {"/getTbGroupLikeName"}, method = RequestMethod.GET)
    @ApiImplicitParam(name = "groupName", value = "群体名称", required = true, dataType = "String",paramType = "query")
    public R getTbGroupLikeName( @RequestParam(value="groupName") String groupName){
        return circleGroupService.getTbGroupLikeName(groupName);
    }
    /**
     *删除群体
     * @param groupId
     * @return
     */
    @ApiOperation(value = "删除群体")
    @RequestMapping(value = {"/deleteTbGroupInfo"}, method = RequestMethod.GET)
    @ApiImplicitParam(name = "groupId", value = "根据群体id进行删除", required = true, dataType = "String",paramType = "query")
    public R deleteTbGroupInfo(@RequestParam(value="groupId") String groupId){
        return circleGroupService.deleteTbGroupInfo(groupId);
    }
}
