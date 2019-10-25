package com.zysj.ts.tssync.entity.condition;

public abstract class Condition {

    private String key;
//    private String opt;
    private String value;
    private Operation opt;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Operation getOpt() {
        return opt;
    }

    public void setOpt(Operation opt) {
        this.opt = opt;
    }

//    public abstract String
}
