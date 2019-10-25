package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/9/23.
 */

import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/9/23 16:51
 */
@Data
public class TagObject  {
    /**
     * 主键id
     */
    String OBJECT_ID;
    /**
     * 主体/关系编码
     */
    String OBJECT_CODE;
    /**
     * 主体/关系名称
     */
    String  OBJECT_NAME;
    /**
     * 所属类目id
     */
    String OBJECT_CLASS_ID;
    /**
     * 所属类目
     */
    String OBJECT_CLASS_NAME;
    /**
     * 创建人id
     */
    String CREATE_P_ID;
    /**
     * 创建人
     */
    String CREATE_P_NAME;
    /**
     * 创建时间
     */
    String CREATE_TIME;
    /**
     * 主体描述
     */
    String DESCRIPTION;
    /**
     * 0 主体 1 关系
     */
    int OBJECT_TYPE;
    /**
     * 为“关系”时，MAIN_OBJECT_ID存储第一个主体id
     */
    String MAIN_OBJECT_ID;
    /**
     * 为“关系”时，MAIN_OBJECT_ID存储关联主体id
     */
    String REF_OBJECT_ID;
    /**
     * 表名
     */
    String TableNumber;
    /**
     * 字段
     */
    String NumberOfTags;



}
