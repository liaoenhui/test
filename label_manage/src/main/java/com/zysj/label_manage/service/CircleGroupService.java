package com.zysj.label_manage.service;/**
 * Created by 尚先生 on 2019/10/15.
 */

import com.zysj.label_manage.entity.TbGroup;
import com.zysj.service_common.common.utils.R;

/**
 * 圈群部分
 * @author 尚先生
 * @date 2019/10/15 16:11
 */

public interface CircleGroupService {
    /**
     *  查询相应主体下的类目
     * @param  obJctId 主键的id
     * @return  R
     */

    R getSubjectTagCategoryList(String obJctId);
    /**
     *  根据标签获取到关联主体
     * @param  subjectId 主键的id
     * @param relationshipId 关系id
     * @return  R
     */

    R getTagSubjectCategoryList( String subjectId,String relationshipId );


    /**
     * 新增群体
     * @param tbGroup
     */
    R addGroup(TbGroup tbGroup);

    /**
     * 修改群体
     * @param tbGroup
     * @return
     */

    R updateEditTbGroupInfo(TbGroup tbGroup);

    /**
     * 根据群体名称模糊搜索
     * @param groupName  群体名称
     *@return 返回模糊查询到的群体
     */
    R getTbGroupLikeName(String groupName);
    /**
     * 删除群体
     * @param groupId
     * @return
     */
    R deleteTbGroupInfo(String groupId);
}
