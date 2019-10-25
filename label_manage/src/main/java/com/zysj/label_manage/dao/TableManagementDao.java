package com.zysj.label_manage.dao;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.zysj.label_manage.entity.ObjectTable;
import com.zysj.label_manage.entity.TagObjectTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/9/24 10:00
 */
@Mapper
public interface TableManagementDao {
    /**
     *  查询主体下的表
     * @param objectId  主键id
     * @return
     */
    List<Map<String, Object>> getTableRelationsInfo(String objectId);
    /**
     *  查询主体内关系下拉
     *
     * @return
     */
    List<Map<String, Object>> getSubjectRelations();
    /**
     *  查询标签内类目下拉
     *
     * @return
     */
    List<Map<String, Object>> getTagTableInfo();
    /**
     *  查询主体下是否有主表
     * @param objectId  主键id
     * @return
     */
    List<Map<String, Object>> getWhetherMasterTable(String objectId);
    /**
     *  查询主体(关系)下的关联主体
     * @param objectId  主键id
     * @return
     */
    List<Map<String, Object>> getRelatedSubject(String objectId);
    /**
     *  根据关系id查询到对应的主体
     * @param MAIN_OBJECT_ID  关系id
     * @param  REF_OBJECT_ID 关系id
     * @return
     */
    List<Map<String, Object>> getRelatedSubjects(@Param("MAIN_OBJECT_ID")String MAIN_OBJECT_ID,@Param("REF_OBJECT_ID")String REF_OBJECT_ID);
    /**
     *  根据关系id与表id查询标签下是否存在该主体
     * @param objectId  主体id
     * @param  tableId 表id
     * @return
     */
    List<Map<String, Object>> getMainTagInfo(@Param("objectId")String objectId,@Param("tableId")String tableId);

    /**
     * 删除解绑标签数据
     * @param TagId
     */
    void deleteTagTableInfo(String TagId);
    /**
     * 删除解绑表数据
     * @param TableId
     */
    void deleteTableInfo(String TableId);
    /**
     *  查询表下是否有标签存在 (用于解绑)
     * @param TableId  表id
     * @return
     */
    List<Map<String, Object>> getTableInfo(String TableId);
    /**
     *  查询表下是否有主标签
     * @param tagId  表id
     * @param objectId 主体id
     * @return
     */
    List<Map<String, Object>> getWhetherMasterTag(@Param("tagId")String tagId,@Param("objectId")String objectId);
    /**
     * 新增表关系
     * @param objectTable
     */
    void addTableRelationsInfo(ObjectTable objectTable);
    /**
     * 新增标签数据
     * @param tagObjectTag
     */
    void addTagTableInfo(TagObjectTag tagObjectTag);
    /**
     * 用于在数据源中模糊查询到表
     *@param dataId 数据源的ds_Id
     *@param tableName 表名
     * @return 返回模糊查询到的表名
     */
    List<Map<String, Object>> getvagueTable(@Param("dataId")String dataId,@Param("tableName")String tableName);
    /**
     * 用于标签部分列/标签查询
     *@param surfaceId 表id
     *@param tableName 字段名称
     * @return 返回模糊查询到的字段/标签
     */
    List<Map<String, Object>> getVagueTags(@Param("surfaceId")String surfaceId,@Param("tableName")String tableName);

    /**
     *  用户在数据源中找到对应的表
     *@param  dataId 表id tb_datasource_table_column
     *@return 返回模糊查询到的字段名称 和id
     */
    List<Map<String, Object>> getTableField(String dataId);

    /**
     *
     * @return 返回根据表id查询到的已绑定的字段信息
     */
    List<Map<String, Object>> getRelatedTags(@Param("surfaceId") String surfaceId,@Param("isBounds") String isBounds,@Param("objectId") String objectId);
    /**
     *  搜索ID 在表的最上面显示的部分
     *  @param SubjectId
     *  @param tableName
     * @return 返回模糊查询到的表名
     */
    List<Map<String, Object>> getSearchTable(@Param("SubjectId")String SubjectId,@Param("tableName")String tableName);

}
