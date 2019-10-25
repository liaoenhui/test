package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/9/23.
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/9/23 15:57
 */
@Data
public class DataSource {
    @ApiModelProperty(value = "主键id",hidden = true)
    String DS_ID;
    @ApiModelProperty(value = "数据源英文名")
   String DS_ENAME;
    @ApiModelProperty(value = "数据源中文名")
   String DS_CNAME;
    @ApiModelProperty(value = "数据源类型：0:mysql,1:oracle:2:hive,3:odps")
   int DS_TYPE;
    @ApiModelProperty(value = "数据源连接IP")
    String DS_IP;
    @ApiModelProperty(value = "数据源连接端口")
    String DS_PORT;
    @ApiModelProperty(value = "数据源连接用户名")
    String DS_USERNAME;
    @ApiModelProperty(value = "数据源连接密码")
    String DS_PASSWORD;
    @ApiModelProperty(value = "数据源连接ENDPOINT")
    String DS_ENDPOINT;
    @ApiModelProperty(value = "数据源连接ACCESSKEY（密文存储，对称加密）")
    String DS_ACCESSKEY;
    @ApiModelProperty(value = "数据源连接ACCESS_SECRET（密文存储，对称加密）")
    String DS_ACCESS_SECRET;
    @ApiModelProperty(value = "数据库版本id")
    String DS_VERSION_ID;
    @ApiModelProperty(value = "操作人")
    String DS_OPERATOR;
    @ApiModelProperty(value = "更新时间")
    String DS_UPDATETIME;



}
