package com.zysj.label_manage.service.impl;/**
 * Created by 尚先生 on 2019/10/22.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zysj.label_manage.dao.TagDataConsulDao;
import com.zysj.label_manage.entity.tagquery.TagConditionCreator;
import com.zysj.label_manage.entity.tagquery.TagQueryCondition;
import com.zysj.label_manage.service.TagDataConsulService;
import com.zysj.service_common.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/10/22 9:10
 */
@Service
public class TagDataConsulServiceImpl implements TagDataConsulService {
    private static String KEY_CONDITION_CONDITIONS = "conditions";
    private static String KEY_OUT = "out";
    private static String KEY_OBJECT_ID = "objId";
    private static String KEY_TAG_CODE = "tagCode";
    private static String KEY_DATA_TYPE = "dataType";
    private static String KEY_VALUE = "value";
    private static String KEY_OPT = "opt";
    private static String KEY_START = "start";
    private static String KEY_END = "end";
    private static String KEY_IN = "in";
    private static String KEY_RELATION_MAIN_TAG = "MT";
    private static String KEY_RELATION_RELATION_TAG = "RT";
    private static String KEY_RELATION_MAIN_TAG_REF_OBJECT_ID = "REF_OBJECT_ID";

    private static String KEY_MT_EXISTS_OBJECT = "KMEB_";
    @Autowired
    TagDataConsulDao tagDataConsulDao;

    /**
     * 用来解析前端的json用来查询pg库中的数据
     * @param sConditions
     * @return
     */
    @Override
    public R circleGroup(String sConditions) {
        if (sConditions==""||sConditions.isEmpty()){
        return R.ok("未接收到值！请再次确认").put("list","");
        }else {
            String returnJson = jsonTranslation(sConditions);
            List<Map<String,Object>> pgTagDataList =tagDataConsulDao.tsDataQuery(returnJson);
            if (pgTagDataList.size()==0||pgTagDataList.isEmpty()){
                return R.ok("未找到数据请再次确认是否选择正确。").put("list",pgTagDataList);
            }
            return R.ok().put("list",pgTagDataList);
        }

    }

    /**
     *用来传给pg数据库存储使用
     * @param origin 前端的原始json
     * @param groupId group id 群体id
     * @return
     */
    @Override
    public String saveAnalyzedGroup(String origin, String groupId) {

       String trans =jsonTranslation(origin);

        return tagDataConsulDao.tsDataSaveAnalyzedGroup(trans,origin,groupId);
    }


    /**
     *用来转译前度传过来的json 转成制定的格式
     * @return
     */
    public String jsonTranslation(String sConditions){
        JSONObject sConJO = JSONObject.parseObject(sConditions );
        TagQueryCondition convertedCon = new TagQueryCondition();
        convertConditions(sConJO.getString(KEY_OBJECT_ID),sConJO,convertedCon);
        convertedCon.setLimit(0);
        convertedCon.setOffset(10);
        /**
         * random ordered
         */
        convertedCon.setSearchType("ordered");
        Object convertedCons = JSONObject.toJSON(convertedCon);
        String returnJson =JSONObject.toJSONString(convertedCons);

        return returnJson;

    }


    /**
     *用来完成pg数据库的查询以及关系主体之间的方法
     * @param objectId
     * @param origin
     * @param convertedCon
     */
    private void convertConditions(String objectId, JSONObject origin, TagQueryCondition convertedCon){
        if(origin==null) {return;}
        convertedCon.setObjId(objectId);
        JSONArray conditionsJA = (JSONArray) origin.get(KEY_CONDITION_CONDITIONS);
        JSONArray mts = (JSONArray) origin.get(KEY_RELATION_MAIN_TAG);

        if(conditionsJA==null||conditionsJA.size()==0){ return;}
        Map<String,TagQueryCondition> joinMap = findRelationship(convertedCon,mts,objectId);
        for(Object c:conditionsJA){
            JSONObject conditionJO = (JSONObject) c;
            if(convertedCon.getObjId().equals(conditionJO.getString(KEY_OBJECT_ID))){
                /**
                 * 主体的条件组装
                 */
                convertedCon.addTagCondition(TagConditionCreator.createCondition(conditionJO.getString(KEY_TAG_CODE),
                        conditionJO.getString(KEY_OPT),
                        conditionJO.getString(KEY_DATA_TYPE),
                        conditionJO.getString(KEY_VALUE),
                        conditionJO.getString(KEY_START),
                        conditionJO.getString(KEY_END)));
            }else {
                String joinMapKey = conditionJO.getString(KEY_OBJECT_ID);
                TagQueryCondition rqc = joinMap.get(joinMapKey);
                if(rqc!=null){
                    rqc.addTagCondition(TagConditionCreator.createCondition(conditionJO.getString(KEY_TAG_CODE),
                            conditionJO.getString(KEY_OPT),
                            conditionJO.getString(KEY_DATA_TYPE),
                            conditionJO.getString(KEY_VALUE),
                            conditionJO.getString(KEY_START),
                            conditionJO.getString(KEY_END)));
                }
            }

        }

        JSONArray outJA = (JSONArray) origin.get(KEY_OUT);
        if(outJA==null||outJA.size()==0) {return;}
        for(Object c:outJA){
            JSONObject outJO = (JSONObject) c;
            if(convertedCon.getObjId().equals(outJO.getString(KEY_OBJECT_ID))){
                convertedCon.addOut(outJO.getString(KEY_TAG_CODE));
            }else{//todo
                String joinMapKey = outJO.getString(KEY_OBJECT_ID);
                TagQueryCondition rqc = joinMap.get(joinMapKey);
                if(rqc!=null){
                    rqc.addOut(outJO.getString(KEY_TAG_CODE));
                }
            }

        }
    }


    /**
     *用来完成pg数据库的查询以及关系主体之间的方法
     * @param objectId 所选主体Id relationId 关系ID
     * @param mts
     * @param convertedCon
     */
    private Map<String,TagQueryCondition> findRelationship(TagQueryCondition convertedCon,JSONArray mts,String objectId){//objectId 所选主体Id relationId 关系ID
        Map<String,TagQueryCondition> joinMap = new HashMap<String,TagQueryCondition>();
        Map<String,TagQueryCondition> relationMap = new HashMap<String,TagQueryCondition>();
        if (mts!=null&&!mts.isEmpty()){
            for(Object mto:mts) {
                JSONObject mt = (JSONObject) mto;
                String relationId = mt.getString(KEY_OBJECT_ID);
                String selfObjectId = mt.getString(KEY_RELATION_MAIN_TAG_REF_OBJECT_ID);
                String joinMapKey = KEY_MT_EXISTS_OBJECT+relationId+"_"+selfObjectId;
                TagQueryCondition join = joinMap.get(joinMapKey);
                if (join == null) {
                    join = new TagQueryCondition();
                    convertedCon.addJoin(join);
                    joinMap.put(joinMapKey, join);
                }
                if(mt.getString(KEY_RELATION_MAIN_TAG_REF_OBJECT_ID).equals(objectId)) {
                    join.setObjId(relationId);
                    join.setJoinKey(mt.getString(KEY_TAG_CODE));
                    relationMap.put(relationId,join);
                    relationMap.put(objectId,join);
                }else{
                    join.setObjId(selfObjectId);
                    join.setJoinKey(mt.getString(KEY_TAG_CODE));
                    join.setJoinTable(relationId);
                }
                if(join.getObjId().equals(relationId)){
                    relationMap.put(relationId,join);
                }
                if(join.getObjId().equals(selfObjectId)){
                    relationMap.put(selfObjectId,join);
                }
            }
            return relationMap;
        }else {
            return relationMap;
        }


    }
}
