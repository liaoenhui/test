package com.zysj.ts.tsdata.dao.sqlprovider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.*;

public class TagDataProvider{
    private static final String KEY_CONDITION_CONDITIONS = "cons";
    private static final String KEY_CONDITION_CONDITIONS_CONDITION = "condition";
    private static final String KEY_CONDITION_OBJECT = "obj";
    private static final String KEY_CONDITION_OUT = "out";//output

    private static final String KEY_JOINS = "joins";
    private static final String KEY_JOINS_JOIN_JOINTABLE = "joinTable";
    private static final String KEY_JOINS_JOIN_JOINKEY = "joinKey";
    private static final String SQL_COLUMN_KEY = "item_id";
    private static final String SQL_COLUMN_DATA = "data";
    private static final String SQL_DOT = ".";
    private static final String SQL_OPT_POSTGRESQL_SUBPROPERTY = "->";

    private static final String KEY_CONDITION_OPT_OPT = "opt";
    private static final String KEY_CONDITION_OPT_KEY = "key";
    private static final String KEY_CONDITION_OPT_VALUE = "value";
    private static final String KEY_CONDITION_OPT_DATATYPE = "dataType";
    private static final String KEY_CONDITION_OPT_BETWEEN = "between";
    private static final String KEY_CONDITION_OPT_BETWEEN_AND = "and";
    private static final String KEY_CONDITION_OPT_LIKE = "like";
    private static final String KEY_CONDITION_OPT_EQUAtION = "eq";
    private static final String KEY_CONDITION_OPT_IN = "in";
    private static final String KEY_CONDITION_OPT_BETWEEN_START = "start";
    private static final String KEY_CONDITION_OPT_BETWEEN_END = "end";

    private static final String KEY_CONDITION_OPT_DATATYPE_NUMBER = "number";
    private static final String KEY_CONDITION_OPT_DATATYPE_STRING = "string";


    private static final String KEY_PAGINATION_ROWS_LIMIT = "limit";
    private static final String KEY_PAGINATION_ROWS_OFFSET = "offset";
    private static final Integer PAGINATION_ROWS_OFFSET_DEFAULT = 0;
    private static final Integer PAGINATION_ROWS_LIMIT_DEFAULT = 10;
    private static final String KEY_SEARCH_TYPE = "searchType";
    private static final String KEY_SEARCH_TYPE_RANDOM = "random";
    private static final String KEY_SEARCH_TYPE_ORDERED = "order";
    private static  final String KEY_PAGINATION_ROWS = "rows";

    private static final String SQL_OPT_AS = "as";

    public String queryTagData(Map<String,Object> conditions) {
        String out = "";
        Map<String,Object> cs = (Map<String,Object>) conditions.get(KEY_CONDITION_CONDITIONS_CONDITION);
        String table = (String) cs.get(KEY_CONDITION_OBJECT);
        JSONArray ja = (JSONArray) cs.get(KEY_CONDITION_CONDITIONS);
//        List<String> columns = new ArrayList<String>();
        SQL sql= new SQL();
        sql.SELECT(wrapTable(table,SQL_COLUMN_KEY));
        out(sql,cs,table).FROM(table);
        cons(sql,ja,table);
        JSONArray joins = (JSONArray) cs.get(KEY_JOINS);
        List<List<JSONObject>> allJoinsKey = getAllJoins(joins);
        if(joins!=null&&(!joins.isEmpty())) {
            for(List<JSONObject> allJoinsFromSomeKey:allJoinsKey){
                if(allJoinsFromSomeKey==null||allJoinsFromSomeKey.size()==0) {continue;}
                int count = 0;
                String joinStrFromSomeKey = "";
                for(JSONObject joJoin:allJoinsFromSomeKey){
                    String tableJoin = joJoin.getString(KEY_CONDITION_OBJECT);
                    if (StringUtils.isEmpty(tableJoin)) continue;
                    String refJoinTable = joJoin.getString(KEY_JOINS_JOIN_JOINTABLE);

                    String joinKey = joJoin.getString(KEY_JOINS_JOIN_JOINKEY);
                    if (StringUtils.isEmpty(joinKey)) continue;
                    JSONArray consJoin = (JSONArray) joJoin.get(KEY_CONDITION_CONDITIONS);
                    out(sql, joJoin, tableJoin);
                    cons(sql, consJoin, tableJoin);
//                    String joinConditionSqlStr = null;
                    if (StringUtils.isEmpty(refJoinTable)) {
                        joinStrFromSomeKey += checkJoinCount(count,tableJoin) + wrapTable(table, SQL_COLUMN_KEY) + " = " + "(" + wrapTable(tableJoin, SQL_COLUMN_DATA) + SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(joinKey) + ")";
//                        sql.JOIN(tableJoin + " on " + wrapTable(table, SQL_COLUMN_KEY) + " = " + "(" + wrapTable(tableJoin, SQL_COLUMN_DATA) + SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(joinKey) + ")"); //hstore_test2.item_id = (hstore_ref1.data->'hstore_ref1')::int4
                    }else{
                        joinStrFromSomeKey += checkJoinCount(count,tableJoin) + wrapTable(tableJoin, SQL_COLUMN_KEY) + " = " + "(" + wrapTable(refJoinTable, SQL_COLUMN_DATA) + SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(joinKey) + ")";
//                        sql.JOIN(tableJoin + " on " + wrapTable(tableJoin, SQL_COLUMN_KEY) + " = " + "(" + wrapTable(refJoinTable, SQL_COLUMN_DATA) + SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(joinKey) + ")");
                    }
                    if((++count)==allJoinsFromSomeKey.size()){
                        sql.JOIN(joinStrFromSomeKey);
                    }
                    ;
                }
            }
        }
        limit(sql,cs,table);
        System.out.println(sql.toString());
        return sql.toString();
    }
    private String checkJoinCount(int count,String tableJoin){
        return ((count==0)?(tableJoin + " on "):" and ");
    }
    private List<List<JSONObject>> getAllJoins(JSONArray joins){
        Map<String,List<JSONObject>> allJoins = new HashMap<>();
        if(joins!=null&&(!joins.isEmpty())) {
            for (Object join : joins) {
                JSONObject joJoin = (JSONObject) join;
                String tableJoin = joJoin.getString(KEY_CONDITION_OBJECT);
                List<JSONObject> joinsOfKey = allJoins.get(tableJoin);
                if(joinsOfKey==null){
                    joinsOfKey = new ArrayList<JSONObject>();
                    allJoins.put(tableJoin,joinsOfKey);
                }
                joinsOfKey.add(joJoin);
            }
        }
        //这里将map.entrySet()转换成list
        Collection<List<JSONObject>> allJoinsList = allJoins.values();
        List<List<JSONObject>> list = new ArrayList<List<JSONObject>>(allJoins.values());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<List<JSONObject>>() {
            //升序排序
            public int compare(List<JSONObject> o1,
                               List<JSONObject> o2) {
                return Integer.compare(o1.size(),o2.size());
//                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return  list;
    }
    private SQL searchType(SQL sql, Map<String, Object> cs, String table){
        String searchType = (String) cs.get(KEY_SEARCH_TYPE);
        if(searchType==null) return sql;
        if(KEY_SEARCH_TYPE_RANDOM.equalsIgnoreCase(searchType)){
            return sql;
        }
        if(KEY_SEARCH_TYPE_ORDERED.equalsIgnoreCase(searchType)){
            return sql.ORDER_BY(table+SQL_DOT+SQL_COLUMN_KEY);
        }
        return sql;
    }
    private SQL limit(SQL sql, Map<String, Object> cs, String table) {
        JSONObject rows = (JSONObject) cs.get(KEY_PAGINATION_ROWS);
        if(rows==null) {
            sql.LIMIT(PAGINATION_ROWS_LIMIT_DEFAULT).OFFSET_ROWS(PAGINATION_ROWS_OFFSET_DEFAULT);
            return sql;
        }
        Integer limit = rows.getInteger(KEY_PAGINATION_ROWS_LIMIT);
        Integer offset = rows.getInteger(KEY_PAGINATION_ROWS_OFFSET);
        sql.LIMIT(limit).OFFSET_ROWS(offset);
        return sql;
    }

    private String wrapTable(String table,String column){
        return table+SQL_DOT+column;
    }
    private SQL out(SQL sql,Map<String,Object> cs,String table){
        if(cs.get(KEY_CONDITION_OUT)==null) return sql;
        for(Object s:(JSONArray)cs.get(KEY_CONDITION_OUT)){
            if(SQL_COLUMN_KEY.equals(s)) continue;
            sql.SELECT(wrapTable(table,SQL_COLUMN_DATA)+ SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(s.toString()) + " as "+s);
        }
        return sql;
    }
    private SQL cons( SQL sql,JSONArray cons,String table){
        if(cons==null) return sql;
        for(Object con:cons) {
            JSONObject jo = (JSONObject) con;
            String opt = (String) jo.get(KEY_CONDITION_OPT_OPT);
            String key = (String) jo.get(KEY_CONDITION_OPT_KEY);
            String value = (String) jo.get(KEY_CONDITION_OPT_VALUE);
            String dataType = (String) jo.get(KEY_CONDITION_OPT_DATATYPE);
            if (KEY_CONDITION_OPT_LIKE.equalsIgnoreCase(opt)) {
                sql.WHERE(wrapTable(table,SQL_COLUMN_DATA)+SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(key) + " like '%" + value + "%'");
            }
            if (KEY_CONDITION_OPT_EQUAtION.equalsIgnoreCase(opt)) {
                sql.WHERE(wrapTable(table,SQL_COLUMN_DATA)+SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(key) + " = " + wrapValueWithQuotion(dataType, value));
            }
            if (KEY_CONDITION_OPT_BETWEEN.equalsIgnoreCase(opt)) {
                String start = (String) jo.get(KEY_CONDITION_OPT_BETWEEN_START);
                String end = (String) jo.get(KEY_CONDITION_OPT_BETWEEN_END);
                if (start != null && end != null) {
                    sql.WHERE(wrapTable(table,SQL_COLUMN_DATA)+SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(key) + " "+KEY_CONDITION_OPT_BETWEEN+" " + wrapValueWithQuotion(dataType, start) + " "+KEY_CONDITION_OPT_BETWEEN_AND+" " + wrapValueWithQuotion(dataType, end));
                } else if (start == null) {
                    sql.WHERE(wrapTable(table,SQL_COLUMN_DATA)+SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(key) + " < " + wrapValueWithQuotion(dataType, end));
                } else if (end == null) {
                    sql.WHERE(wrapTable(table,SQL_COLUMN_DATA)+SQL_OPT_POSTGRESQL_SUBPROPERTY + wrapValueWithQuotion(key) + " > " + wrapValueWithQuotion(dataType, start));
                }
            }
        }
        return sql;
    }
    private String wrapValueWithQuotion(String dataType, String value){
        if(KEY_CONDITION_OPT_DATATYPE_NUMBER.equalsIgnoreCase(dataType)) return value;
        return wrapValueWithQuotion(value);
    }
    private String wrapValueWithQuotion(String value){
        return "'"+value+"'";
    }
}
