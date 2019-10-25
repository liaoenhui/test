package com.zysj.label_manage.controller;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.zysj.label_manage.entity.ObjectTable;
import com.zysj.label_manage.entity.TagObjectTag;
import com.zysj.label_manage.service.TableManagementService;
import com.zysj.service_common.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 尚先生
 * @date 2019/9/24 9:59
 */
@RestController
@RequestMapping("tableManagement")
@Api(tags = "表管理api")
public class TableManagementController {
    @Autowired
    TableManagementService tableManagementService;

    /**
     * 表管理 列表
     * @param objectId 主体的id
     * @return
     */
    @RequestMapping(value = {"/getTableRelationsInfo/{objectId}"}, method = RequestMethod.GET)
    @ApiOperation(value = "表管理")
    @ApiImplicitParam(name = "objectId", value = "主体的id", required = true, dataType = "String",paramType = "path")
    public R getTableRelationsInfo(@PathVariable("objectId") String objectId){

        return tableManagementService.getTableRelationsInfo(objectId);

    }

    /**
     * 主体内关系下拉显示
     * @return
     */
    @RequestMapping(value = {"/getSubjectRelations"}, method = RequestMethod.GET)
    @ApiOperation(value = "主体内关系下拉显示")
    public R getSubjectRelations(){

        return tableManagementService.getSubjectRelations();

    }

    /**
     * 关联主体下拉
     * @param objectId 主体id
     * @return
     */
    @RequestMapping(value = {"/getRelatedSubject"}, method = RequestMethod.GET)
    @ApiOperation(value = "关联主体下拉")
    @ApiImplicitParam(name = "objectId", value = "主体关系id ", required = true, dataType = "String",paramType = "query")
    public R getRelatedSubject(@RequestParam(value="objectId") String objectId){

        return tableManagementService.getRelatedSubject(objectId);

    }

    /**
     * 关联主体下拉(判断该主体是否已存在以及存在与哪个字段)
     * @param objectId 主体id
     * @param tableId 表id
     * @return
     */
    @RequestMapping(value = {"/getMainTagInfo"}, method = RequestMethod.GET)
    @ApiOperation(value = "关联主体下拉(判断该主体是否已存在以及存在与哪个字段)")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "objectId", value = "主体关系id ", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "tableId", value = "表id ", required = true, dataType = "String",paramType = "query")
    })
    public R getMainTagInfo(@RequestParam(value="objectId") String objectId,@RequestParam(value="tableId") String tableId){

        return tableManagementService.getMainTagInfo(objectId,tableId);

    }

    /**
     * 根据表id查询字段已绑定信息
     * @param  objectId 主体id
     * @param surfaceId  表id
     * @param isBounds 是否绑定（1 绑定 0未绑定）
     * @return
     */
    @RequestMapping(value = {"/getRelatedTags"}, method = RequestMethod.GET)
    @ApiOperation(value = "根据表id查询字段已绑定信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "surfaceId", value = "表id ", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "objectId", value = "主体id ", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "isBounds", value = "是否绑定（1 绑定 0未绑定） ", required = true, dataType = "String",paramType = "query")
    })
    public R getRelatedTags( @RequestParam(value="surfaceId") String surfaceId,@RequestParam(value="isBounds") String isBounds,@RequestParam(value="objectId",required = false) String objectId ){
        return tableManagementService.getRelatedTags(surfaceId,isBounds,objectId);
    }
    /**
     * 根据表id 字段名称模糊查询字段
     * @param surfaceId  表id
     * @param tableName 字段名
     * @return
     */
    @RequestMapping(value = {"/getVagueTags"}, method = RequestMethod.GET)
    @ApiOperation(value = "根据表id和字段名称模糊查询Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "surfaceId", value = "表id ", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "tableName", value = "标签字段 ", required = true, dataType = "String",paramType = "query")
    })
    public R getVagueTags( @RequestParam(value="surfaceId") String surfaceId,@RequestParam(value="tableName") String tableName ){
        return tableManagementService.getVagueTags(surfaceId,tableName);
    }
    /**
     *删除标签表数据 解绑
     * @param TagId
     * @return
     */
    @ApiOperation(value = "删除标签表数据 解绑")
    @RequestMapping(value = {"/deleteTagTableInfo"}, method = RequestMethod.GET)
    @ApiImplicitParam(name = "TagId", value = "根据标签id进行解除绑定", required = true, dataType = "String",paramType = "query")
    public R deleteTagTableInfo(@RequestParam(value="TagId") String TagId){


        return tableManagementService.deleteTagTableInfo(TagId);
    }
    /**
     *删除表数据 解绑
     * @param TableId
     * @return
     */
    @ApiOperation(value = "删除表数据 解绑")
    @RequestMapping(value = {"/deleteTableInfo"}, method = RequestMethod.GET)
    @ApiImplicitParam(name = "TableId", value = "根据标签id进行解除绑定", required = true, dataType = "String",paramType = "query")
    public R deleteTableInfo(@RequestParam(value="TableId") String TableId){


        return tableManagementService.deleteTableInfo(TableId);
    }

    /**
     * 表字段模糊查询（ 根据数据源查表 列表中的）
     * @param dataId 数据源id
     * @param tableNames 表字段模糊查询
     * @return
     */
    @RequestMapping(value = {"/getvagueTable"}, method = RequestMethod.GET)
    @ApiOperation(value = "表字段模糊查询（ 根据数据源查表 列表中的）")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "dataId", value = "数据源id ", required = true, dataType = "String",paramType = "query"),
          @ApiImplicitParam(name = "tableNames", value = "表字段模糊查询 ", required = true, dataType = "String",paramType = "query")
       })
    public R getvagueTable( @RequestParam(value="dataId") String dataId,@RequestParam(value="tableNames") String tableNames){
        return tableManagementService.getvagueTable(dataId,tableNames);

    }

    /**
     * 字段模糊查询（ 根据 表id出字段）
     * @param dataId 表id出字段
     * @return
     */
    @RequestMapping(value = {"/getTableField"}, method = RequestMethod.GET)
    @ApiOperation(value = "字段模糊查询（ 根据 表id出字段）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataId", value = "表字段id ", required = true, dataType = "String",paramType = "query"),

    })
    public R getTableField( @RequestParam(value="dataId") String dataId){
        return tableManagementService.getTableField(dataId);

    }

    /**
     * 表字段模糊查询（根据主体查表）
     * @param SubjectId  主体id
     * @param tableNames 表名称
     * @return
     */
    @RequestMapping(value = {"/getSearchTable"}, method = RequestMethod.GET)
    @ApiOperation(value = "表字段模糊查询（根据主体查表）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SubjectId", value = "主体id（OBJECT_ID） ", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "tableNames", value = "表字段模糊查询 ", required = true, dataType = "String",paramType = "query")
    })
    public R getSearchTable( @RequestParam(value="SubjectId") String SubjectId,@RequestParam(value="tableNames") String tableNames){
        return tableManagementService.getSearchTable(SubjectId,tableNames);

    }
    /**
     *新增关联表
     * @param objectTable 关联表实体
     * @return
     */
    @ApiOperation(value = "新增关联表")
    @RequestMapping(value = {"/addTableRelationsInfo"}, method = RequestMethod.POST)
    @ApiImplicitParam(name = "objectTable", value = "数据源实体tb_tag_object_table", required = true, dataType = "ObjectTable")
    public R addTableRelationsInfo(@RequestBody ObjectTable objectTable){
        return tableManagementService.addTableRelationsInfo(objectTable);
    }
    /**
     *新增标签表数据
     * @param tagObjectTag
     * @return
     */
    @ApiOperation(value = "新增标签表数据 (未绑定使用)")
    @RequestMapping(value = {"/addTagTableInfo"}, method = RequestMethod.POST)
    @ApiImplicitParam(name = "tagObjectTag", value = "数据源实体tb_tag_object_tag", required = true, dataType = "TagObjectTag")
    public R addTagTableInfo(@RequestBody TagObjectTag tagObjectTag){
        return tableManagementService.addTagTableInfo(tagObjectTag);
    }
    /**
     *新增标签表数据（所属类目下拉）
     * @return
     */
    @ApiOperation(value = "添加标签表所属类目下拉")
    @RequestMapping(value = {"/getTagTableInfo"}, method = RequestMethod.GET)
    public R getTagTableInfo(){
        return tableManagementService.getTagTableInfo();
    }
}
