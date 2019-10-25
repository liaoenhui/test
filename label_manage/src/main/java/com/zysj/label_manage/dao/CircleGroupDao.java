package com.zysj.label_manage.dao;/**
 * Created by 尚先生 on 2019/10/15.
 */

import com.zysj.label_manage.entity.TbGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 圈群部分
 * @author 尚先生
 * @date 2019/10/15 16:10
 */
@Mapper
public interface CircleGroupDao {
    /**
     * 根据主体id查询到关系
     * @param obJctId
     * @return
     */
    List<Map<String, Object>> getSubjectTagCategoryList(String obJctId);
    /**
     * 返回关系下的主标签
     * @param OBJECT_ID
     * @param mt
     * @return
     */
    Map<String, Object> getLabelOfRelationship(@Param("OBJECT_ID")String OBJECT_ID,@Param("obJctId")String obJctId,@Param("mt")int mt);


    /**
     * 返回标签下关联的主体
     * @param subjectId 主体id
     * @param relationshipId 关系id
     * @return
     */
    List<Map<String, Object>> getTagSubjectCategoryList(@Param("subjectId")   String subjectId, @Param("relationshipId") String relationshipId);

    /**
     * 新增群体
     * @param tbGroup
     */
    void addGroup(TbGroup tbGroup);
    /**
     * 修改群体
     * @param tbGroup
     */
    void updateEditTbGroupInfo(TbGroup tbGroup);
    /**
     *  模糊查询群体
     *@param groupName  群体名称
     *@return 返回模糊查询到的群体
     */
    List<Map<String, Object>> getTbGroupLikeName(@Param("groupName")String groupName);

    /**
     * 删除群体数据
     * @param groupId
     */
    void deleteTbGroupInfo(String groupId);
}
