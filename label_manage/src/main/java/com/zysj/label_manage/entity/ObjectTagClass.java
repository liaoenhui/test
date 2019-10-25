package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/10/9.
 */

import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/10/9 14:32
 */
@Data
public class ObjectTagClass {
    /**
     * 主键id
     */
    String TAG_CLASS_ID;
    /**
     * 所属类目
     */
    String TAG_CLASS_NAME;
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
     * 类目描述
     */
    String DESCRIPTION;
    /**
     * 父节点id
     */
    String PARENT_ID;
}
