package com.zysj.dssync.entity.metadata;

public class TableMeta {
    private String TABLE_ID;
    private String TABLE_ENAME;
    private String TABLE_CNAME;
    private String TABLE_CHARSET;
    private String DS_ID;
    private String TABLE_UPDATETIME;

    public String getTABLE_ID() {
        return TABLE_ID;
    }

    public void setTABLE_ID(String TABLE_ID) {
        this.TABLE_ID = TABLE_ID;
    }

    public String getTABLE_ENAME() {
        return TABLE_ENAME;
    }

    public void setTABLE_ENAME(String TABLE_ENAME) {
        this.TABLE_ENAME = TABLE_ENAME;
    }

    public String getTABLE_CNAME() {
        return TABLE_CNAME;
    }

    public void setTABLE_CNAME(String TABLE_CNAME) {
        this.TABLE_CNAME = TABLE_CNAME;
    }

    public String getTABLE_CHARSET() {
        return TABLE_CHARSET;
    }

    public void setTABLE_CHARSET(String TABLE_CHARSET) {
        this.TABLE_CHARSET = TABLE_CHARSET;
    }

    public String getDS_ID() {
        return DS_ID;
    }

    public void setDS_ID(String DS_ID) {
        this.DS_ID = DS_ID;
    }

    public String getTABLE_UPDATETIME() {
        return TABLE_UPDATETIME;
    }

    public void setTABLE_UPDATETIME(String TABLE_UPDATETIME) {
        this.TABLE_UPDATETIME = TABLE_UPDATETIME;
    }
}
