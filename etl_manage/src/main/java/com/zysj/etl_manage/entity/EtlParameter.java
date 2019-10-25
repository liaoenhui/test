package com.zysj.etl_manage.entity;/**
 * Created by 尚先生 on 2019/9/9.
 */

import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/9/9 9:21
 * ETL需要的一些参数
 */
@Data
public class EtlParameter {
    /**
     * tagName
     * 标签名
     */
    private String TAG_NAME;
    /**
     * DATASOURCE_NAME
     * 数据源名称
     */
    private String DATASOURCE_NAME;
    /**
     * TABLE_NAME
     * 来源表名
     */
    private String TABLE_NAME;
    /**
     * COLUMN_NAME
     * 来源字段
     */
    private String COLUMN_NAME;
    /**
     * TABLE_ID
     * 表id 原来创建mongodb的 Collections
     */
    private String TABLE_ID;
    /**
     *  OBJECT_NAME
     *主体名称 原来生成存放json的文件夹的名字
     */
    private String OBJECT_NAME;

    /**
     *  DS_TYPE
     *数据源类型：0:mysql,1:oracle:2:hive,3:odps
     */
    private int DS_TYPE;
    /**
     * dsId
     * 数据源连接IP
     */
    private String DS_IP;
    /**
     * OBJECT_ID
     * 主题ID
     */
    private String OBJECT_ID;
    /**
     * dsPort
     *数据源连接端口
     */
    private String DS_PORT;
    /**
     * dsUseName
     *数据源连接用户名
     */
    private String DS_USERNAME;
    /**
     * dsPassWoRd
     *数据源连接密码
     */
    private String DS_PASSWORD;
    /**
     * SH_ID
     *状态表id 用于更新状态
     */
    private String SH_ID;
    /**
     * tagId
     *标签id 原来生成json文件的名字
     */
    private String TAG_ID;

    /**
     * csType
     *字段类型
     */
    private String CS_TYPE;
    /**
     * IS_MAIN_TAG
     *是否主标签 0 否 1是
     */
    private int IS_MAIN_TAG;


    public String getTAG_NAME() {
        return TAG_NAME;
    }

    public void setTAG_NAME(String TAG_NAME) {
        this.TAG_NAME = TAG_NAME;
    }

    public String getDATASOURCE_NAME() {
        return DATASOURCE_NAME;
    }

    public void setDATASOURCE_NAME(String DATASOURCE_NAME) {
        this.DATASOURCE_NAME = DATASOURCE_NAME;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getCOLUMN_NAME() {
        return COLUMN_NAME;
    }

    public void setCOLUMN_NAME(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public String getTABLE_ID() {
        return TABLE_ID;
    }

    public void setTABLE_ID(String TABLE_ID) {
        this.TABLE_ID = TABLE_ID;
    }

    public String getOBJECT_NAME() {
        return OBJECT_NAME;
    }

    public void setOBJECT_NAME(String OBJECT_NAME) {
        this.OBJECT_NAME = OBJECT_NAME;
    }

    public int getDS_TYPE() {
        return DS_TYPE;
    }

    public void setDS_TYPE(int DS_TYPE) {
        this.DS_TYPE = DS_TYPE;
    }

    public String getDS_IP() {
        return DS_IP;
    }

    public void setDS_IP(String DS_IP) {
        this.DS_IP = DS_IP;
    }

    public String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(String OBJECT_ID) {
        this.OBJECT_ID = OBJECT_ID;
    }

    public String getDS_PORT() {
        return DS_PORT;
    }

    public void setDS_PORT(String DS_PORT) {
        this.DS_PORT = DS_PORT;
    }

    public String getDS_USERNAME() {
        return DS_USERNAME;
    }

    public void setDS_USERNAME(String DS_USERNAME) {
        this.DS_USERNAME = DS_USERNAME;
    }

    public String getDS_PASSWORD() {
        return DS_PASSWORD;
    }

    public void setDS_PASSWORD(String DS_PASSWORD) {
        this.DS_PASSWORD = DS_PASSWORD;
    }

    public String getSH_ID() {
        return SH_ID;
    }

    public void setSH_ID(String SH_ID) {
        this.SH_ID = SH_ID;
    }

    public String getTAG_ID() {
        return TAG_ID;
    }

    public void setTAG_ID(String TAG_ID) {
        this.TAG_ID = TAG_ID;
    }

    public String getCS_TYPE() {
        return CS_TYPE;
    }

    public void setCS_TYPE(String CS_TYPE) {
        this.CS_TYPE = CS_TYPE;
    }

    public int getIS_MAIN_TAG() {
        return IS_MAIN_TAG;
    }

    public void setIS_MAIN_TAG(int IS_MAIN_TAG) {
        this.IS_MAIN_TAG = IS_MAIN_TAG;
    }
}
