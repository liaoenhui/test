package com.zysj.label_manage.entity.tagquery;/**
 * Created by 尚先生 on 2019/10/18.
 */

/**
 * @author 尚先生
 * @date 2019/10/18 15:01
 */

public class TagCondition {
        private String key;
        private String dataType;
        private String opt;
        private String value;
        private String start;
        private String end;
        private String[] ins;
    public TagCondition(){
        super();
    }
    public TagCondition(String key, String dataType, String opt, String value) {
        this.key = key;
        this.dataType = dataType;
        this.opt = opt;
        this.value = value;
    }

    public TagCondition(String key, String dataType, String opt, String start, String end) {
        this.key = key;
        this.dataType = dataType;
        this.opt = opt;
        this.start = start;
        this.end = end;
    }

    public TagCondition(String key, String dataType, String opt, String[] ins) {
        this.key = key;
        this.dataType = dataType;
        this.opt = opt;
        this.ins = ins;
    }

    public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getOpt() {
            return opt;
        }

        public void setOpt(String opt) {
            this.opt = opt;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String[] getIns() {
            return ins;
        }

        public void setIns(String[] ins) {
            this.ins = ins;
        }
}
