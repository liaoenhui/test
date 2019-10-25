package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/9/23.
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/9/23 20:45
 */
@Data
public class ObjectTable {
    @ApiModelProperty(value = "主键id")
    String TABLE_ID;
    @ApiModelProperty(value = "表名")
    String TABLE_NAME;
    @ApiModelProperty(value = "表注释")
    String TABLE_CNAME;
    @ApiModelProperty(value = "数据源标识")
    String DATASOURCE_ID;
    @ApiModelProperty(value = "数据源名称")
    String DATASOURCE_NAME;
    @ApiModelProperty(value = "关联主体Id")
    String OBJECT_ID;
    @ApiModelProperty(value = "关联主体名称")
    String OBJECT_NAME;
    @ApiModelProperty(value = "是否为主表")
    int IS_MAIN_TABLE;
    @ApiModelProperty(value = "内部关系引用")
    String INNER_RELATION_REF;
    @ApiModelProperty(value = "绑定人id")
    String CREATE_P_ID;
    @ApiModelProperty(value = "绑定人")
    String CREATE_P_NAME;
    @ApiModelProperty(value = "绑定时间")
    String CREATE_TIME;
    @ApiModelProperty(value = "描述")
    String DESCRIPTION;

}
