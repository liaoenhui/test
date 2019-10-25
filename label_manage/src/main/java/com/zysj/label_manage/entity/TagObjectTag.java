package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/9/26.
 */

import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/9/26 16:50
 * 主体详情表
 */
@Data
public class TagObjectTag {
    private String TAG_ID;
    private String TAG_NAME;
    private String TAG_CODE;
    private String TAG_CLASS_ID;
    private String TAG_CLASS_NAME;
    private String DATASOURCE_ID;
    private String DATASOURCE_NAME;
    private String TABLE_ID;
    private String TABLE_NAME;
    private String CS_ID;
    private String COLUMN_NAME;
    private String OBJECT_ID;
    private String OBJECT_NAME;
    /**
     * DATA_TYPE 数据类型:1 string、2 枚举、3数值、4日期、5布尔
     */
    private int DATA_TYPE;
    /**
     * 当data_type为2时，可引用字典值dic_Id
     */
    private String dic_id;
    private String DESCRIPTION;
    /**
     * IS_MAIN_TAG 是否主标签 0 否 1是
     */
    private int IS_MAIN_TAG;
    private String REF_OBJECT_ID;
    private String CREATE_P_ID;
    private String CREATE_P_NAME;
    private String CREATE_TIME;
}
