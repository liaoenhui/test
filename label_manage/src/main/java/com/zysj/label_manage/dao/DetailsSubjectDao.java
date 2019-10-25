package com.zysj.label_manage.dao;/**
 * Created by 尚先生 on 2019/10/8.
 */


import com.zysj.label_manage.entity.ObjectTagClass;
import com.zysj.label_manage.entity.TagDic;
import com.zysj.label_manage.entity.TagObjectTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/10/8 13:42
 */
@Mapper
public interface DetailsSubjectDao {
    /**
     * 返回标签类目信息跟前端显示列表
     * @param TAG_CLASS_ID 类目id
     * @param  OBJECT_ID 主体id
     * @return
     */
    List<Map<String, Object>> getTagNode(@Param("OBJECT_ID")String OBJECT_ID, @Param("TAG_CLASS_ID")String TAG_CLASS_ID);
    /**
     * 返回主体详情
     * @param OBJECT_ID
     * @return
     */
    List<Map<String, Object>> getSearchTable(String OBJECT_ID);
    /**
     * 返回主体下的标签
     * @param OBJECT_ID
     * @return
     */
    List<Map<String, Object>> getSearchTag(String OBJECT_ID);
    /**
     * 返回类目中的数据
     * @param TAG_CLASS_ID
     * @param OBJECT_ID 主体id
     * @return
     */
    List<Map<String, Object>> getTagDetails(@Param("OBJECT_ID")String OBJECT_ID,@Param("TAG_CLASS_ID")String TAG_CLASS_ID);

    /**
     * 新增字典表数据
     * @param list
     */
    void addTagDicInfo( List<TagDic> list );

    /**
     *  修改字典表数据
     * @param list
     */
    void editTagDicInfo(List<TagDic> list);
    /**
     *  修改标签表数据（添加字典表的id到标签）
     * @param tagObjectTag
     */
    void editModifyLabelsInfo(TagObjectTag tagObjectTag);
    /**
     * 获取字典表数据信息(精准)
     * @param dic_name
     * @return 数据库类型
     */
    List<Map<String, Object>> getDictionaryList(String dic_name);
    /**
     * 获取字典表是否包含该字典名称
     * @param dic_name 字典名称
     * @return 数据库类型
     */
    List<Map<String, Object>> getDictionaryTableList(String dic_name);
    /**
     * 获取标签表是否包含该标签code
     * @param tag_code 标签code
     * @param objectId 主体id
     * @param tag_id  标签id 可传可不传
     * @return 01
     */
    List<Map<String, Object>> getDictionaryTagList(@Param("tag_code")String tag_code,@Param("objectId")String objectId,@Param("tag_id")String tag_id);
    /**
     * 获取字典表数据信息
     * @param dic_name
     * @return 数据库类型
     */
    List<Map<String, Object>> getDictionaryListInfo(String dic_name);
    /**
     * 获取主体 以及主体关系 和标签 关系(关系图谱部分)
     * @return
     */
    List<Map<String, Object>> getObjectTagList();
    /**
     * 获取主体  和标签 关系(关系图谱部分)
     * @return
     */
    List<Map<String, Object>> getSubjectTagList();


    /**
     * 新增标签类目
     * @param objectTagClass
     */
    void addLabelCategory(ObjectTagClass objectTagClass);
}
