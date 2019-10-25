package com.zysj.label_manage.entity.tagquery;/**
 * Created by 尚先生 on 2019/10/18.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author 尚先生
 * @date 2019/10/18 14:57
 */
//{obj:'hstore_test2',cons:[{'key':'key1','dataType':'string','opt':'like','value':'hstore_test2value130'}" +
////                ",{'key':'key2','opt':'eq','value':'13231321'}" +
//                ",{'key':'key2','dataType':'string','opt':'between','end':'999'}" +
//                ",{'key':'key3','dataType':'date','opt':'between','start':'2019-10-01','end':'2019-10-10'}]" +
//                ",'out':['key1','key2','key3','item_id']" +
//                ",'joins':[{'obj':'hstore_ref1','joinKey':'hstore_ref1',cons:[{'key':'key_ref1','dataType':'string','opt':'like','value':'key_refvalue1302'}],out:['key_ref1']}
public class TagQueryCondition {
    private String obj;
    private String objId;
    private List<TagCondition> cons;
    private List<String> out;
    private List<TagQueryCondition> joins;
    private String joinKey;
    private String joinTable;
    private Integer limit ;
    private Integer offset;
    private String searchType;
    private static final String OBJ_PREFIX = "tb_obj_";

    public String getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = OBJ_PREFIX+joinTable;
    }

    public TagQueryCondition addOut(String outStr ){
        if(out==null){out = new ArrayList<String>();}
        out.add(outStr);
        return this;
    }
    public TagQueryCondition addJoin(TagQueryCondition tqc){
        if(joins==null){joins = new ArrayList<TagQueryCondition>();}
        joins.add(tqc );
        return this;
    }
    public TagQueryCondition addTagCondition(TagCondition tc){
        if(cons==null){cons = new ArrayList<TagCondition>();}
        cons.add(tc );
        return this;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
        setObj(OBJ_PREFIX+objId);
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public List<TagCondition> getCons() {
        return cons;
    }

    public void setCons(List<TagCondition> cons) {
        this.cons = cons;
    }

    public List<String> getOut() {
        return out;
    }

    public void setOut(List<String> outs) {
        this.out = outs;
    }

    public List<TagQueryCondition> getJoins() {
        return joins;
    }

    public void setJoins(List<TagQueryCondition> joins) {
        this.joins = joins;
    }

    public String getJoinKey() {
        return joinKey;
    }

    public void setJoinKey(String joinKey) {
        this.joinKey = joinKey;
    }
}
