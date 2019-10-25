package com.zysj.label_manage.service;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.zysj.label_manage.entity.ObjectTable;
import com.zysj.label_manage.entity.TagObjectTag;
import com.zysj.service_common.common.utils.R;

/**
 * @author 尚先生
 * @date 2019/9/24 9:14
 */

public interface TableManagementService {


    /**
     *  查询主体下的表
     * @param objectId  主键id
     * @return
     */
    R getTableRelationsInfo(String objectId);
    /**
     *  查询主体(关系)下的关联主体
     * @param objectId  主键id
     * @return
     */
    R getRelatedSubject(String objectId);
    /**
     *
     * @return 返回主体标识列表
     */
    R getSubjectRelations();
    /**
     *
     * @return 返回标签类目列表
     */
    R getTagTableInfo();
    /**
     *  根据表id查询表下的标签以及字段
     * @param  objectId 主体id
     *  @param isBounds  是否绑定（1 绑定 0未绑定）
     * @param surfaceId  表id
     * @return
     */
    R getRelatedTags(String surfaceId,String isBounds,String objectId);
    /**
     *  模糊查询绑定字段以及未绑定字段
     *
     * @param surfaceId  表id
     * @param  tableName 字段名称
     *
     * @return
     */
    R getVagueTags(String surfaceId,String tableName);
    /**
     *  根据表id以及主体id查询表下的标签以及字段是否存在该主体
     *  @param  tableId 表id
     * @param objectId  主体id
     *
     * @return
     */
    R getMainTagInfo(String objectId,String tableId);

    /**
     *  新增 关联表数据
     * @param objectTable
     * @return
     */
    R addTableRelationsInfo(ObjectTable objectTable);
    /**
     * 删除标签 绑定数据
     * @param TagId
     * @return
     */
    R deleteTagTableInfo(String TagId);
    /**
     * 删除表绑定数据
     * @param TableId
     * @return
     */
    R deleteTableInfo(String TableId);
    /**
     *  新增 标签表数据
     * @param tagObjectTag
     * @return
     */
    R addTagTableInfo(TagObjectTag tagObjectTag);
    /**
     * 根据数据源id 和表名模糊搜索
     * @param dataId 数据源id  tableName表名字模糊查询
     * @return 返回模糊查询到的数据源英文名和数据源id
     */
    R getvagueTable(String dataId,String tableName);

    /**
     * 根据表id 和字段模糊搜索
     * @param dataId 表id  tableName字段名字模糊查询
     * @return 返回模糊查询到的字段英文名和字段id
     */
    R getTableField(String dataId);

    /**
     * 根据主体id 和表名模糊搜索
     * @param SubjectId  主体id
     * @param tableName 表名字模糊查询
     * @return
     */
    R getSearchTable(String SubjectId, String tableName);
}
