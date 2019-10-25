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
public class ObjectInner {
    @ApiModelProperty(value = "标签唯一标识")
    String INNER_ID;
    @ApiModelProperty(value = "表名")
    String INNER_NAME;
    @ApiModelProperty(value = "关系code")
    String INNER_CODE;
    @ApiModelProperty(value = "关系表id")
    String TABLE_ID;
    @ApiModelProperty(value = "关系表名")
    String TABLE_NAME;
    @ApiModelProperty(value = "主体标识字段")
    String OBJECT_COLUMN;
    @ApiModelProperty(value = "关联字段")
    String REF_COLUMN;
    @ApiModelProperty(value = "关联字段中文名")
    String REF_COLUMN_NAME;
    @ApiModelProperty(value = "关联主体id")
    String OBJECT_ID;
    @ApiModelProperty(value = "关联主体名称")
    String OBJECT_NAME;
    @ApiModelProperty(value = "标签描述")
    String DESCRIPTION;
    @ApiModelProperty(value = "创建人id")
    String CREATE_P_ID;
    @ApiModelProperty(value = "创建人")
    String CREATE_P_NAME;
    @ApiModelProperty(value = "创建时间")
    String CREATE_TIME;


}
