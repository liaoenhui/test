package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/10/22.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/10/22 14:43
 */
@ApiModel(value = "群体表")
@Data
public class TbGroup {
    @ApiModelProperty(value = "主键id" ,hidden = true)
    private String group_ID;
    @ApiModelProperty(value = "群体名称")
    private String group_NAME;
    @ApiModelProperty(value = "所属主体ID")
    private String object_ID;
    @ApiModelProperty(value = "创建人id")
    private String create_P_ID;
    @ApiModelProperty(value = "创建人")
    private String create_P_NAME;
    @ApiModelProperty(value = "创建时间")
    private String create_TIME;
    @ApiModelProperty(value = "群体描述")
    private String description;
    @ApiModelProperty(value = "是否生成API，0否 1是")
    private String api_CREATED;
    @ApiModelProperty(value = "apiID")
    private String api_ID;
    @ApiModelProperty(value = "群体搜索条件")
    private String conditions;
    @ApiModelProperty(value = "关联主体数" ,hidden = true)
    private int  relavent_OBJECT_COUNT;
}
