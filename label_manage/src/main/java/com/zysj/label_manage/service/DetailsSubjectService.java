package com.zysj.label_manage.service;/**
 * Created by 尚先生 on 2019/10/8.
 */

import com.zysj.label_manage.entity.ObjectTagClass;
import com.zysj.label_manage.entity.TagDic;
import com.zysj.label_manage.entity.TagObjectTag;
import com.zysj.service_common.common.utils.R;

import java.util.List;

/**
 * @author 尚先生
 * @date 2019/10/8 13:44
 */

public interface DetailsSubjectService {

    /**
     *  查询相应主体下的标签子节点
     * @param  TAG_CLASS_ID 标签类目的id
     * @param  OBJECT_ID 主体id
     * @return  R
     */

    R getTagNode(String OBJECT_ID,String TAG_CLASS_ID);
    /**
     *  查询相应主体下的详情
     * @param  OBJECT_ID 主键的id
     * @return  R
     */

    R getSubjectDetails(String OBJECT_ID);
    /**
     *  查询相应类目下的数据
     *  @param  OBJECT_ID 主体id
     * @param  TAG_CLASS_ID 类目id
     * @return  R
     */

    R getTagDetails(String OBJECT_ID,String TAG_CLASS_ID);

    /**
     *  添加字典表数据
     * @param list
     * @return
     */
    R addTagDicInfo(List<TagDic>  list);

    /**
     * 修改字典表数据
     * @param list
     * @return
     */
    R editTagDicInfo(List<TagDic> list);
    /**
     * 修改标签表数据（添加字典表的id到标签）
     * @param tagObjectTag
     * @return
     */
    R editModifyLabelsInfo(TagObjectTag tagObjectTag);
    /**
     * 获取字典表数据信息
     * @param dic_name
     * @return 字典 表名称
     */
    R getDictionaryList(String dic_name);
    /**
     * 获取字典表中该名称是否包含
     * @param dic_name
     * @return 字典 表名称
     */
    R getDictionaryTableList(String dic_name);
    /**
     * 获取标签code是否包含
     * @param tag_code 标签code
     * @param objectId 主体id
     * @param tag_id 标签id
     * @return 字典 表名称
     */
    R getDictionaryTagList(String tag_code,String objectId,String tag_id);
    /**
     * 获取字典表数据信息
     * @param dic_name 字典表名称
     * @return 字典表信息
     */
    R getDictionaryListInfo(String dic_name);
    /**
     * 获取主体 标签 关系 的关联
     * @return 字典 表名称
     */
    R getObjectTagList();
    /**
     * 根据单个主体获取主体 标签 关系 的关联
     * @param objectId
     * @return
     */
    R getObjectTagInfoList(String objectId);

    /**
     * 新增标签类目
     * @param objectTagClass
     * @return
     */
    R addLabelCategory(ObjectTagClass objectTagClass);
}
