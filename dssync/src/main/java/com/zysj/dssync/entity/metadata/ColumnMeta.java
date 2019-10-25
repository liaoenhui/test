package com.zysj.dssync.entity.metadata;

import java.util.Objects;

public class ColumnMeta {
    private String CS_ID;
    private String CS_ENAME;
    private String CS_CNAME;
    private String CS_TYPE;
    private String CS_LENGTH;
    private String CS_ISNULL;
    private String CS_ISPRIMARY;
    private String CS_CHARSET;
    private String TABLE_ID;
    private String CS_UPDATETIME;
    private String CS_TABLE_NAME;

    public String getCS_TABLE_NAME() {
        return CS_TABLE_NAME;
    }

    public void setCS_TABLE_NAME(String CS_TABLE_NAME) {
        this.CS_TABLE_NAME = CS_TABLE_NAME;
    }

    @Override
    public String toString() {
        return "ColumnMeta{" +
                "CS_ID='" + CS_ID + '\'' +
                ", CS_ENAME='" + CS_ENAME + '\'' +
                ", CS_CNAME='" + CS_CNAME + '\'' +
                ", CS_TYPE='" + CS_TYPE + '\'' +
                ", CS_LENGTH='" + CS_LENGTH + '\'' +
                ", CS_ISNULL='" + CS_ISNULL + '\'' +
                ", CS_ISPRIMARY='" + CS_ISPRIMARY + '\'' +
                ", CS_CHARSET='" + CS_CHARSET + '\'' +
                ", TABLE_ID='" + TABLE_ID + '\'' +
                ", CS_UPDATETIME='" + CS_UPDATETIME + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnMeta that = (ColumnMeta) o;
        return Objects.equals(CS_ID, that.CS_ID) &&
                Objects.equals(CS_ENAME, that.CS_ENAME) &&
                Objects.equals(CS_CNAME, that.CS_CNAME) &&
                Objects.equals(CS_TYPE, that.CS_TYPE) &&
                Objects.equals(CS_LENGTH, that.CS_LENGTH) &&
                Objects.equals(CS_ISNULL, that.CS_ISNULL) &&
                Objects.equals(CS_ISPRIMARY, that.CS_ISPRIMARY) &&
                Objects.equals(CS_CHARSET, that.CS_CHARSET) &&
                Objects.equals(TABLE_ID, that.TABLE_ID) &&
                Objects.equals(CS_UPDATETIME, that.CS_UPDATETIME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CS_ID, CS_ENAME, CS_CNAME, CS_TYPE, CS_LENGTH, CS_ISNULL, CS_ISPRIMARY, CS_CHARSET, TABLE_ID, CS_UPDATETIME);
    }

    public String getCS_ID() {
        return CS_ID;
    }

    public void setCS_ID(String CS_ID) {
        this.CS_ID = CS_ID;
    }

    public String getCS_ENAME() {
        return CS_ENAME;
    }

    public void setCS_ENAME(String CS_ENAME) {
        this.CS_ENAME = CS_ENAME;
    }

    public String getCS_CNAME() {
        return CS_CNAME;
    }

    public void setCS_CNAME(String CS_CNAME) {
        this.CS_CNAME = CS_CNAME;
    }

    public String getCS_TYPE() {
        return CS_TYPE;
    }

    public void setCS_TYPE(String CS_TYPE) {
        this.CS_TYPE = CS_TYPE;
    }

    public String getCS_LENGTH() {
        return CS_LENGTH;
    }

    public void setCS_LENGTH(String CS_LENGTH) {
        this.CS_LENGTH = CS_LENGTH;
    }

    public String getCS_ISNULL() {
        return CS_ISNULL;
    }

    public void setCS_ISNULL(String CS_ISNULL) {
        this.CS_ISNULL = CS_ISNULL;
    }

    public String getCS_ISPRIMARY() {
        return CS_ISPRIMARY;
    }

    public void setCS_ISPRIMARY(String CS_ISPRIMARY) {
        this.CS_ISPRIMARY = CS_ISPRIMARY;
    }

    public String getCS_CHARSET() {
        return CS_CHARSET;
    }

    public void setCS_CHARSET(String CS_CHARSET) {
        this.CS_CHARSET = CS_CHARSET;
    }

    public String getTABLE_ID() {
        return TABLE_ID;
    }

    public void setTABLE_ID(String TABLE_ID) {
        this.TABLE_ID = TABLE_ID;
    }

    public String getCS_UPDATETIME() {
        return CS_UPDATETIME;
    }

    public void setCS_UPDATETIME(String CS_UPDATETIME) {
        this.CS_UPDATETIME = CS_UPDATETIME;
    }
}
