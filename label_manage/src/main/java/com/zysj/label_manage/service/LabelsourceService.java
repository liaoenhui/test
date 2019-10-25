package com.zysj.label_manage.service;

import com.zysj.label_manage.entity.ObjectClass;
import com.zysj.label_manage.entity.TagObject;
import com.zysj.service_common.common.utils.R;

import java.util.List;

/**
 * 数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
public interface LabelsourceService {



    /**
     * 获取主体源列表
     * @param OBJECT_CLASS_ID
     * @return
     */
    List<TagObject> getLabelsourceList(String OBJECT_CLASS_ID);

    /**
     * 新增主体
     * @param tagObject
     * @return
     */

    R addSubjectSourceInfo(TagObject tagObject);

    /**
     * 新增类目
     * @param objectClass
     * @return
     */
    R addLevelNodeInfo(ObjectClass objectClass);

    /**
     *  查询相应主体下的子节点
     * @param OBJECT_CLASS_ID  主体的ID
     * @return  R
     */

    R getlevelNode(String OBJECT_CLASS_ID);


    /**
     * 获取所有主体供列表选择
     * @param
     * @return
     */
    R getLabelSources();

    /**
     * 获取单个主体信息
     * @param OBJECT_ID
     * @return
     */
    R getLabelsourceInfo(String OBJECT_ID);
    /**
     * 删除主体
     * @param objectId
     * @return
     */
    R deleteLabelSourceInfo(String objectId);

    /**
     * 删除主体类目
     * @param objectClassId
     * @return
     */
    R deleteLevelNodeInfo(String objectClassId);
}
