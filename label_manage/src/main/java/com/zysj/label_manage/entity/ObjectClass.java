package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/9/24.
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/9/24 13:21
 */
@Data
public class ObjectClass {
    @ApiModelProperty(value = "主键id")
    String OBJ_CLASS_ID;
    @ApiModelProperty(value = "所属类目")
    String OBJ_CLASS_NAME;
    @ApiModelProperty(value = "创建人id")
    String CREATE_P_ID;
    @ApiModelProperty(value = "创建人")
    String CREATE_P_NAME;
    @ApiModelProperty(value = "创建时间")
    String CREATE_TIME;
    @ApiModelProperty(value = "类目描述")
    String DESCRIPTION;
    @ApiModelProperty(value = "父节点id")
    String PARENT_ID;

    public String getOBJ_CLASS_ID() {
        return OBJ_CLASS_ID;
    }

    public void setOBJ_CLASS_ID(String OBJ_CLASS_ID) {
        this.OBJ_CLASS_ID = OBJ_CLASS_ID;
    }

    public String getOBJ_CLASS_NAME() {
        return OBJ_CLASS_NAME;
    }

    public void setOBJ_CLASS_NAME(String OBJ_CLASS_NAME) {
        this.OBJ_CLASS_NAME = OBJ_CLASS_NAME;
    }

    public String getCREATE_P_ID() {
        return CREATE_P_ID;
    }

    public void setCREATE_P_ID(String CREATE_P_ID) {
        this.CREATE_P_ID = CREATE_P_ID;
    }

    public String getCREATE_P_NAME() {
        return CREATE_P_NAME;
    }

    public void setCREATE_P_NAME(String CREATE_P_NAME) {
        this.CREATE_P_NAME = CREATE_P_NAME;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(String PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }
}
