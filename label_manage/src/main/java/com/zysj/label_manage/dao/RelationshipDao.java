package com.zysj.label_manage.dao;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.zysj.label_manage.entity.ObjectInner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/9/24 9:12
 */
@Mapper
public interface RelationshipDao {

    /**
     *  增加关系
     * @param objectInner  关系实体类
     *
     */
    void  addRelationShipSourceInfo(ObjectInner objectInner);

    /**
     *  查询主体标识
     * @param MainId  主体id
     * @return 返回该主体下有几个主体标识
     */
    List<Map<String, Object>> getInsideMainIdentification(String MainId);

    /**
     *  查询主体下的关系（内关系部分）
     * @param objectId  主键id
     * @return  对应的内关系列表
     */
    List<Map<String, Object>> getInternalRelationsInfo(String objectId);
    /**
     *  查询主体下的关系code 是否存在
     * @param fieldName  主体关系code
     * @return （0，1）0为不存在 1为存在
     */
    List<Map<String, Object>>  getWhetherField(String fieldName);
}
