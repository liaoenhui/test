package com.zysj.label_manage.dao;

import com.zysj.label_manage.entity.ObjectClass;
import com.zysj.label_manage.entity.TagObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
@Mapper
public interface LabelsourceDao {

    /**
     * 获取数据源列表
     * @param OBJECT_CLASS_ID 主体类目id
     * @return
     */
    List<TagObject> getLabelsourceList(String OBJECT_CLASS_ID);

    /**
     * 新增数据源
     * @param tagObject 数据源实体类
     */
    void addSubjectSourceInfo(TagObject tagObject);
    /**
     * 返回类目信息跟前端显示列表
     * @param OBJECT_CLASS_ID 主体类目id
     * @return 类目信息跟前端显示列表
     */
    List<Map<String, Object>> getlevelNode(String OBJECT_CLASS_ID);
    /**
     * 新建类目
     * @param objectClass 类目实体类
     *
     */
     void addLevelNodeInfo(ObjectClass objectClass);
    /**
     * 获取所有主体
     *@return 主体列表
     */
    List<Map<String, Object>>  getLabelSources();

    /**
     * 获取单个数据源信息
     * @param OBJECT_ID 主体id
     * @return 单个数据源的详细信息
     */
    List<Map<String, Object>> getLabelsourceInfo(String OBJECT_ID);

    /**
     * 删除主体数据
     * @param objectId
     */
    void deleteLabelSourceInfo(String objectId);

    /**
     * 删除主体类目
     * @param objectClassId 类目id
     */
    void deleteLevelNodeInfo(String objectClassId);
    /**
     * 判断主体下是否存在标签以及关系
     * @param objectId 主体id
     * @return
     */
    List<Map<String, Object>> getRelationshipLabels(String objectId);
    /**
     * 判断主体类目下是否存在类目以及主体
     * @param objectClassId 类目id
     * @return
     */
    List<Map<String, Object>> getLevelNodeInfo(String objectClassId);
}
