package com.zysj.etl_manage.entity;/**
 * Created by 尚先生 on 2019/9/10.
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/9/10 10:21
 */
@Data
@TableName("tb_object_tag_sh")
public class TagSh {
    private String SH_ID;
    private String OBJECT_ID;
    private String TABLE_ID;
    private int SH_STATUS;

    public String getSH_ID() {
        return SH_ID;
    }

    public void setSH_ID(String SH_ID) {
        this.SH_ID = SH_ID;
    }

    public String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(String OBJECT_ID) {
        this.OBJECT_ID = OBJECT_ID;
    }

    public String getTABLE_ID() {
        return TABLE_ID;
    }

    public void setTABLE_ID(String TABLE_ID) {
        this.TABLE_ID = TABLE_ID;
    }

    public int getSH_STATUS() {
        return SH_STATUS;
    }

    public void setSH_STATUS(int SH_STATUS) {
        this.SH_STATUS = SH_STATUS;
    }

    @Override
    public String toString() {
        return "TagSh{" +
                "SH_ID='" + SH_ID + '\'' +
                ", OBJECT_ID='" + OBJECT_ID + '\'' +
                ", TABLE_ID='" + TABLE_ID + '\'' +
                ", SH_STATUS=" + SH_STATUS +
                '}';
    }
}
