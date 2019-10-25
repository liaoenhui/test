package com.zysj.ts.tssync.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("tb_object_syncopt")
public class TagSyncEntity implements Serializable {

    private static final long serialVersionUID = 6320286653505337901L;

    //主键
    private Integer optId;
    /**
     * 主体id
     */
    private String objectId;

    /**
     * 表id
     */
    private String tableId;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 同步脚本是否需要更新，0需要更新，1 已更新
     */
    private Integer syncScript;

    /**
     * 脚本最后更新时间
     */
    private String scriptSyncTime;

    /**
     * 标签数据是否需要更新，0 需要同步 1 已同步 2 周期同步
     */
    private Integer syncData;

    /**
     * 标签数据最后同步时间
     */
    private String dataSyncTime;

    /**
     * 主体是否已更新，1有更新 0无更新；当最新脚本生成后，修改为0
     */
    private Integer isupdated;

    /**
    *同步任务执行时长,单位：秒
     */
    private Integer syncTake;


    public TagSyncEntity() {
    }

    public Integer getOptId() {
        return optId;
    }

    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getSyncScript() {
        return syncScript;
    }

    public void setSyncScript(Integer syncScript) {
        this.syncScript = syncScript;
    }

    public String getScriptSyncTime() {
        return scriptSyncTime;
    }

    public void setScriptSyncTime(String scriptSyncTime) {
        this.scriptSyncTime = scriptSyncTime;
    }

    public Integer getSyncData() {
        return syncData;
    }

    public void setSyncData(Integer syncData) {
        this.syncData = syncData;
    }

    public String getDataSyncTime() {
        return dataSyncTime;
    }

    public void setDataSyncTime(String dataSyncTime) {
        this.dataSyncTime = dataSyncTime;
    }

    public Integer getIsupdated() {
        return isupdated;
    }

    public void setIsupdated(Integer isupdated) {
        this.isupdated = isupdated;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getSyncTake() {
        return syncTake;
    }

    public void setSyncTake(Integer syncTake) {
        this.syncTake = syncTake;
    }

    @Override
    public String toString() {
        return "TagSyncEntity{" +
                "objectId='" + objectId + '\'' +
                ", tableId='" + tableId + '\'' +
                ", tableName='" + tableName + '\'' +
                ", syncScript=" + syncScript +
                ", scriptSyncTime='" + scriptSyncTime + '\'' +
                ", syncData=" + syncData +
                ", dataSyncTime='" + dataSyncTime + '\'' +
                ", isupdated=" + isupdated +
                ", syncTake=" + syncTake +
                '}';
    }
}
