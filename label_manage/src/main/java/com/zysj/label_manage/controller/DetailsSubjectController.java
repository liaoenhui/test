package com.zysj.label_manage.controller;/**
 * Created by 尚先生 on 2019/9/26.
 */

import com.alibaba.fastjson.JSON;
import com.zysj.label_manage.entity.ObjectTagClass;
import com.zysj.label_manage.entity.TagDic;
import com.zysj.label_manage.entity.TagObjectTag;
import com.zysj.label_manage.service.DetailsSubjectService;
import com.zysj.service_common.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 尚先生
 * @date 2019/9/26 13:53
 */
@RestController
@RequestMapping("detailsSubject")
@Api(tags = "主体详情APi")
public class DetailsSubjectController {
    @Autowired
    DetailsSubjectService detailsSubjectService;

    /**
     * 获取类目列表
     */
    @RequestMapping(value = "/getTagNode", method = RequestMethod.GET)

    @ApiImplicitParams({

            @ApiImplicitParam(name = "OBJECT_ID",value = "其中为主体id",required = true,dataType="String",paramType = "query"),
            @ApiImplicitParam(name = "TAG_CLASS_ID",value = "其中最后跟的是父节点的id 获取所有父节点 需要传0",required = true,dataType="String",paramType = "query")
    })
    @ApiOperation(value = "获取节点数据")
    public R TagNode(@RequestParam(value = "OBJECT_ID")  String OBJECT_ID,@RequestParam(value = "TAG_CLASS_ID")  String TAG_CLASS_ID) {

        return detailsSubjectService.getTagNode(OBJECT_ID,TAG_CLASS_ID);

    }
    /**
     * 获取主体详情
     */
    @RequestMapping(value = "/getSubjectDetails/{OBJECT_ID}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "OBJECT_ID",value = "主体id",required = true,dataType="String",paramType = "query")
    @ApiOperation(value = "获取主体详情")
    public R SubjectDetails(@PathVariable("OBJECT_ID")  String OBJECT_ID) {

        return detailsSubjectService.getSubjectDetails(OBJECT_ID);

    }
    /**
     * 获取类目下的标签数据
     */
    @RequestMapping(value = "/getTagDetails", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "OBJECT_ID", value = "主体id", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "TAG_CLASS_ID", value = "所属类目id", required = true, dataType = "String",paramType = "query")

    })
    @ApiOperation(value = "获取类目下的标签数据")
    public R TagDetails(@RequestParam(value="OBJECT_ID") String OBJECT_ID,@RequestParam(value = "TAG_CLASS_ID")  String TAG_CLASS_ID) {

        return detailsSubjectService.getTagDetails(OBJECT_ID,TAG_CLASS_ID);

    }
    /**
     * 获取字典表列表（模糊）
     */
    @RequestMapping(value = {"/getDictionaryList"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取字典表列表数据")

    @ApiImplicitParam(name = "dic_name",value = "字典表名称",required = false,dataType="String",paramType = "query")
    public R getDictionaryList(@RequestParam(value="dic_name",required = false) String dic_name){
        return detailsSubjectService.getDictionaryList(dic_name);
    }
    /**
     * 获取字典表是否包括该名称
     */
    @RequestMapping(value = {"/getDictionaryTableList"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取字典表是否包括该名称")
    @ApiImplicitParam(name = "dic_name",value = "字典表名称",required = false,dataType="String",paramType = "query")
    public R getDictionaryTableList(@RequestParam(value="dic_name",required = false) String dic_name){
        return detailsSubjectService.getDictionaryTableList(dic_name);
    }
    /**
     * 获取标签表是否包含该标签code
     */
    @RequestMapping(value = {"/getDictionaryTagList"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取标签表是否包含该标签code(10.24修改)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tag_code", value = "标签code",  dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "objectId", value = "主体id",  dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "tag_id", value = "标签id",required = false, dataType = "String",paramType = "query"),
    })
    public R getDictionaryTagList(@RequestParam(value="tag_code") String tag_code,@RequestParam(value="objectId") String objectId,@RequestParam(value="tag_id",required = false) String tag_id){
        return detailsSubjectService.getDictionaryTagList(tag_code,objectId,tag_id);
    }
    /**
     * 获取字典表列表(精准)
     */
    @RequestMapping(value = {"/getDictionaryListInfo"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取字典表列表数据(精准)")
    @ApiImplicitParam(name = "dic_name",value = "字典表名称",required = false,dataType="String",paramType = "query")
    public R getDictionaryListInfo(@RequestParam(value="dic_name",required = false) String dic_name){
        return detailsSubjectService.getDictionaryListInfo(dic_name);
    }
    /**
     * 获取关联图谱(所有主体的关联图谱)
     * @return  R 关系图谱 node links
     */
    @RequestMapping(value = {"/getObjectTagList"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取关联图谱数据(所有主体的关联图谱)")
    public R ObjectTagList(){
        return detailsSubjectService.getObjectTagList();
    }
    /**
     * 根据主体id获取单个关联图谱
     * @param objectId 主体id
     * @return R 关系图谱 node links
     */
    @RequestMapping(value = {"/getObjectTagInfoList"}, method = RequestMethod.GET)
    @ApiOperation(value = "根据主体id获取单个关联图谱数据")
    @ApiImplicitParam(name = "objectId", value = "主体id", required = true, dataType = "String",paramType = "query")
    public R ObjectTagInfoList(@RequestParam(value="objectId")String objectId){
        return detailsSubjectService.getObjectTagInfoList(objectId );
    }
    /**
     * 新增字典表数据
     * @param params 字典表实体
     * @return R
     */

    @ApiOperation(value = "新增字典表数据")
    @RequestMapping(value = {"/addTagDicInfo"}, method = RequestMethod.POST)
    @ApiImplicitParam(name = "params", value = "字典表实体TagDic", required = true, dataType = "String",paramType = "body")
    @ResponseBody
    public R addTagDicInfo(@RequestBody  String params){
        //去掉第一个 "
        //TODO 需要进行修改
        System.out.println(params);
//        JSONObject sConJO = JSONObject.parseObject(params );
//        Object convertedCons=sConJO.get("params");
//
//        String returnJson =JSONObject.toJSONString(convertedCons);
      String  paramr=params.replaceAll("\\\\", "");
        if(paramr.indexOf("\"")==0) {paramr = paramr.substring(1,paramr.length()); }
        if(paramr.lastIndexOf("\"")==(paramr.length()-1)){ paramr = paramr.substring(0,paramr.length()-1);}
        paramr =paramr.substring(11,paramr.length()-2);
        System.out.println(paramr);
        List<TagDic> nodes = JSON.parseArray(paramr, TagDic.class);
        return detailsSubjectService.addTagDicInfo(nodes);
    }

    /**
     * 修改字典表
     * @param params 字典表实体
     * @return R
     */
    @ApiOperation(value = "修改字典表")
    @RequestMapping(value = {"/editTagDicInfo"}, method = RequestMethod.POST)
    @ApiImplicitParam(name = "params", value = "字典表实体TagDic", required = true, dataType = "String",paramType = "body")
    @ResponseBody
    public R editTagDicInfo(@RequestBody String  params){
        System.out.println(params);
        //去掉第一个 "
        String  paramr=params.replaceAll("\\\\", "");
        if(paramr.indexOf("\"")==0) {paramr = paramr.substring(1,paramr.length()); }
        if(paramr.lastIndexOf("\"")==(paramr.length()-1)){ paramr = paramr.substring(0,paramr.length()-1);}
        paramr =paramr.substring(11,paramr.length()-2);
        System.out.println(paramr);
        List<TagDic> nodes = JSON.parseArray(paramr, TagDic.class);
        return detailsSubjectService.editTagDicInfo(nodes);
    }
    /**
     * 修改标签表 (添加字典表的id到标签)
     * @param tagObjectTag 标签表实体
     * @return R
     */
    @ApiOperation(value = "修改标签表(添加字典表的id到标签)")
    @RequestMapping(value = {"/editModifyLabelsInfo"}, method = RequestMethod.POST)

    @ApiImplicitParam(name = "tagObjectTag", value = "标签表实体tb_tag_object_tag", required = true, dataType = "TagObjectTag")

    public R editModifyLabelsInfo(@RequestBody TagObjectTag tagObjectTag){
        return detailsSubjectService.editModifyLabelsInfo(tagObjectTag);
    }

    /**
     * 新增标签类目
     * @param objectTagClass 标签类目实体
     * @return R
     */
    @ApiOperation(value = "新增标签类目")
    @RequestMapping(value = {"/addLabelCategory"}, method = RequestMethod.POST)
    @ApiImplicitParam(name = "objectTagClass", value = "标签类目实体td_tag_object_tag_class", required = true, dataType = "ObjectTagClass")
    public R addLabelCategory(@RequestBody ObjectTagClass objectTagClass){
        return detailsSubjectService.addLabelCategory(objectTagClass);
    }

}
