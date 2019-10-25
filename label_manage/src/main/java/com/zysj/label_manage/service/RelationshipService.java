package com.zysj.label_manage.service;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.zysj.label_manage.entity.ObjectInner;
import com.zysj.service_common.common.utils.R;

/**
 * @author 尚先生
 * @date 2019/9/24 9:14
 */

public interface RelationshipService {

    /**
     * 新增关系
     * @param objectInner
     * @return
     */
    R addRelationShipSourceInfo(ObjectInner objectInner);

    /**
     *  查询主体下的关系
     * @param objectId  主键id
     * @return
     */
    R getInternalRelationsInfo(String objectId);
    /**
     *  查询内关系中是否有该关系code
     * @param fieldName  关系code
     * @return
     */
    R getWhetherField(String fieldName);
}
