package com.zysj.label_manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zysj.label_manage.dao.DatasourceDao;
import com.zysj.label_manage.dao.TagDataConsulDao;
import com.zysj.label_manage.entity.TagDic;
import com.zysj.label_manage.entity.TbGroup;
import com.zysj.label_manage.service.CircleGroupService;
import com.zysj.label_manage.service.DetailsSubjectService;
import com.zysj.label_manage.service.TagDataConsulService;
import com.zysj.service_common.common.utils.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelManageApplicationTests {
    @Autowired
    private Environment env;

    @Autowired
    private DatasourceDao datasourceDao;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private CircleGroupService circleGroupService;

    @Autowired
    private TagDataConsulService tagDataConsulService;

    @Autowired
    private DetailsSubjectService detailsSubjectService;
    @Test
    public void contextLoads() {

        List<Map<String, Object>> datasourceList = datasourceDao.getDatasourceList();
        for (Map<String, Object> map : datasourceList) {
            System.out.println(map);
        }
    }
    @Test
    public void testProperties(){
        System.out.println(discoveryClient.getInstances("tag_data"));
    }

    @Test
    public void testAssemblt(){

        String objectId = "68d66ccab221452698a3df91d13eab99";
        R obj = detailsSubjectService.getTagDetails(objectId,"1");
        R rel = circleGroupService.getSubjectTagCategoryList(objectId);
        System.out.println(obj);
        System.out.println(rel);

        JSONArray objTags =getTags(obj);
        JSONObject objTag1 = (JSONObject) objTags.get(0);
        JSONObject objTag2 = (JSONObject) objTags.get(1);

        JSONArray rels = (JSONArray) JSONArray.toJSON(rel.get("list"));
        JSONObject relJo = (JSONObject) rels.get(0);
        JSONObject mt = (JSONObject) relJo.get("MT");
        R relTagsObj = detailsSubjectService.getTagDetails(relJo.getString("OBJECT_ID"),"1");
        JSONArray relTags =getTags(relTagsObj);

        JSONObject relTag1 = (JSONObject) relTags.get(0);
//        JSONObject relTag2 = (JSONObject) relTags.get(1);


//        JSONObject convertedConJO = (JSONObject) JSONObject.toJSON(convertedCon);
//        test(mt,relTags,objectId,objTags.toArray(new JSONObject[]{}));
    }
    @Test
    public void testFront(){
        //condition 前端
        //285bfbd89949446c9228b2771df34af6
//        String refObjectId = "285bfbd89949446c9228b2771df34af6";
        String sConditions = "{objId:'2ea9fab127a04c6ca0e84db41a541e96',MT:[" +
                "{tagCode: 'TAG_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID:'2ea9fab127a04c6ca0e84db41a541e96'}," +
                "{tagCode: 'TAG_ID',objId: '6e8aa73f17b84b829f3a44414c44edf3',REF_OBJECT_ID:'2ea9fab127a04c6ca0e84db41a541e96'}," +
                "{tagCode: 'OBJECT_ID',objId: '6e8aa73f17b84b829f3a44414c44edf3',REF_OBJECT_ID:'68d66ccab221452698a3df91d13eab99'}," +
                "{tagCode: 'OBJECT_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID:'68d66ccab221452698a3df91d13eab99'}]," +
//                "RT:[{tagCode: 'OBJECT_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID;'68d66ccab221452698a3df91d13eab99'}," +
//                "    {tagCode: 'TAG_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID;'2ea9fab127a04c6ca0e84db41a541e96'}]," +
                "conditions:[" +
                "           {objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TABLE_ID',opt:'like',dataType:'string',value:'tb_tag'}," +
                "           {objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TAG_CODE',opt:'eq',dataType:'string',value:'REF_OBJECT_ID'}," +
                "           {objId:'68d66ccab221452698a3df91d13eab99',tagCode:'DESCRIPTION',opt:'like',dataType:'string',value:'标签'}" +
                "           {objId:'285bfbd89949446c9228b2771df34af6',tagCode:'TAG_NAME',opt:'like',dataType:'string',value:'标签'}" +
                "]," + //like  eq between  in
                "out:[{objId:'68d66ccab221452698a3df91d13eab99',tagCode:'DESCRIPTION'},{objId:'285bfbd89949446c9228b2771df34af6',tagCode:'COLUMN_NAME'},{objId:'6e8aa73f17b84b829f3a44414c44edf3',tagCode:'TAG_CODE'},{objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TABLE_ID'},{objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TAG_NAME'},{objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TAG_CODE'}]," +
                "}";
        System.out.println("+++++++++++++++++sConditions+++++++++++++++++++++"+sConditions);
        R r = tagDataConsulService.circleGroup(sConditions);
        System.out.println(r);
    }

    @Autowired
    TagDataConsulDao tagDataConsulDao;

    private void test(JSONObject mt,JSONArray relTags,String objectId,JSONObject ... tag){
        JSONObject obj = new JSONObject();
        String jsonnames = null;
        JSONObject jo = new JSONObject();
        jo.put("obj", "tb_obj_"+objectId);

        String arr[] = new String[tag.length];

        for(int i=0;i<tag.length;i++){
            //3、把里面的对象转化为JSONObject
            arr[i] = tag[i].getString("TAG_CODE");
            // 4、把里面想要的参数一个个用.属性名的方式获取到
//            System.out.println(job.get("otitle")+":"+job.get("source")) ;
        }

        jo.put("out",arr);
        JSONArray ja = new JSONArray();
        JSONObject con = new JSONObject();
        con.put("key","DESCRIPTION");
        con.put("opt","like");
        con.put("value","主体");
        con.put("dataType","string");
        ja.add(con);
        jo.put("cons",ja);
        JSONArray joins = new JSONArray();
        JSONObject join = new JSONObject();
        join.put("obj","tb_obj_"+mt.get("OBJECT_ID"));
        join.put("joinKey",mt.get("TAG_CODE"));
        String[] relOuts = new String[ relTags.size()];
        for(int i=0;i<relTags.size();i++){
            JSONObject rjo = (JSONObject)relTags.get(i);
            relOuts[i] = rjo.getString("TAG_CODE");
        }
        join.put("out",relOuts);
        joins.add(join);
        jo.put("joins",joins);

//关系选了
        System.out.println("++++++++++++++++++转成的json格式字符串：" + jo.toJSONString());

        System.out.println(tagDataConsulDao.tsDataQuery(jo.toJSONString()));
    }
@Test
public  void addGroupTest(){
    String sConditions = "{objId:'2ea9fab127a04c6ca0e84db41a541e96',MT:[" +
            "{tagCode: 'TAG_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID:'2ea9fab127a04c6ca0e84db41a541e96'}," +
            "{tagCode: 'TAG_ID',objId: '6e8aa73f17b84b829f3a44414c44edf3',REF_OBJECT_ID:'2ea9fab127a04c6ca0e84db41a541e96'}," +
            "{tagCode: 'OBJECT_ID',objId: '6e8aa73f17b84b829f3a44414c44edf3',REF_OBJECT_ID:'68d66ccab221452698a3df91d13eab99'}," +
            "{tagCode: 'OBJECT_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID:'68d66ccab221452698a3df91d13eab99'}]," +
//                "RT:[{tagCode: 'OBJECT_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID;'68d66ccab221452698a3df91d13eab99'}," +
//                "    {tagCode: 'TAG_ID',objId: '285bfbd89949446c9228b2771df34af6',REF_OBJECT_ID;'2ea9fab127a04c6ca0e84db41a541e96'}]," +
            "conditions:[" +
            "           {objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TABLE_ID',opt:'like',dataType:'string',value:'tb_tag'}," +
            "           {objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TAG_CODE',opt:'eq',dataType:'string',value:'REF_OBJECT_ID'}," +
            "           {objId:'68d66ccab221452698a3df91d13eab99',tagCode:'DESCRIPTION',opt:'like',dataType:'string',value:'标签'}" +
//                "           {objId:'285bfbd89949446c9228b2771df34af6',tagCode:'TAG_NAME',opt:'like',dataType:'string',value:'标签'}" +
            "]," + //like  eq between  in
            "out:[{objId:'68d66ccab221452698a3df91d13eab99',tagCode:'DESCRIPTION'},{objId:'285bfbd89949446c9228b2771df34af6',tagCode:'COLUMN_NAME'},{objId:'6e8aa73f17b84b829f3a44414c44edf3',tagCode:'TAG_CODE'},{objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TABLE_ID'},{objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TAG_NAME'},{objId:'2ea9fab127a04c6ca0e84db41a541e96',tagCode:'TAG_CODE'}]," +
            "}";
    TbGroup tbGroup =new TbGroup();
    tbGroup.setGroup_NAME("中国");
    tbGroup.setObject_ID("2ea9fab127a04c6ca0e84db41a541e96");
    tbGroup.setConditions(sConditions);
    System.out.println(circleGroupService.addGroup(tbGroup));
}



    private  JSONArray getTags(R r ){
//        JSONObject jo = null;//JSONObject.parseObject(r);
        JSONObject jo = (JSONObject) JSONObject.toJSON(r);
        JSONArray objTags = (JSONArray) jo.get("list");
        return objTags;
    }
}
