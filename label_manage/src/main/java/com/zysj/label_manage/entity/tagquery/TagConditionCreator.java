package com.zysj.label_manage.entity.tagquery;/**
 * Created by 尚先生 on 2019/10/18.
 */

/**
 * @author 尚先生
 * @date 2019/10/18 15:20
 */

public class TagConditionCreator {
    private final static String OPT_LIKE = "like";
    private final static String OPT_EQ = "eq";
    private final static String OPT_BETWEEN = "between";
    private final static String OPT_IN = "in";

    public static TagCondition createCondition(String code,String opt,String dataType,String value,String start,String end,String... in ){
        switch(opt){
            case OPT_LIKE : return createLike(code,dataType,value);
            case OPT_EQ : return createEquation(code,dataType,value);
            case OPT_BETWEEN : return createBetween(code,dataType,start,end);
            case OPT_IN : return createIn(code,dataType,in);
            default:return null;
        }
    }

    public static TagCondition createLike(String code,String dataType,String value){
        return new TagCondition(code,dataType,OPT_LIKE,value);
    }
    public static TagCondition createEquation(String code,String dataType,String value){
        return new TagCondition(code,dataType,OPT_EQ,value);
    }
    public static TagCondition createBetween(String code,String dataType,String start,String end){
        return new TagCondition(code,dataType,OPT_BETWEEN,start,end);
    }
    public static TagCondition createIn(String code,String dataType,String... param ){
        return new TagCondition(code,dataType,OPT_IN,param);
    }
}
