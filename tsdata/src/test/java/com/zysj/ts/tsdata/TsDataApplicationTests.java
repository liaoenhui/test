package com.zysj.ts.tsdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zysj.ts.tsdata.config.RabbitConfig;
import com.zysj.ts.tsdata.dao.TagDataMapper;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import com.zysj.ts.tsdata.service.SyncStarterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsDataApplicationTests {
    @Autowired
    private TagDataMapper tagSyncMapper;

    @Autowired
    private SyncStarterService syncStarterService;
    @Test
    public void contextLoads() {
    }


    @Test
    public void testSyncObject(){
       List<TagSyncEntity> tss =  tagSyncMapper.selectList(new QueryWrapper<TagSyncEntity>().eq("sync_script","0"));
        System.out.println(tss);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void startSyncScriptTask(){
        List<TagSyncEntity> tss =  tagSyncMapper.selectList(new QueryWrapper<TagSyncEntity>().eq("sync_script","0"));
//        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        tagSyncMapper.initAllSyncTasks();
        for(TagSyncEntity tse:tss) {
            System.out.println("发送脚本更新通知,同步主体【"+tse.getObjectId()+"】");
            this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, RabbitConfig.TOPIC_ETLSCRIPT, tse);
        }
    }
    @Test
    public void startSyncDataTask(){
        List<TagSyncEntity> tss =  tagSyncMapper.selectList(new QueryWrapper<TagSyncEntity>().eq("sync_data","0"));
//        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        tagSyncMapper.initAllSyncTasks();
        for(TagSyncEntity tse:tss) {
            System.out.println("发送数据同步通知,同步主体【"+tse.getObjectId()+"】");
            this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, RabbitConfig.TOPIC_DATASYNC, tse);
        }
    }
    @Test
    public void testScriptTask(){
        syncStarterService.startSyncScriptTask();
    }
    @Test
    public void testSyncDataTask(){
        syncStarterService.startSyncDataTask();
    }
    @Test
    public void testInitAllSyncTasks(){
        tagSyncMapper.initAllSyncTasks();
    }


    @Test
    public void testPGSql(){

        String condition = "[{'key':'key1','opt':'like','value':'value11169','out':['key1','key2']}]";
        JSONArray map = JSON.parseObject(condition, JSONArray.class);
//        List<Map<String,String>> result =  tagSyncMapper.testPgSql();
//        System.out.println(result);
//        String json = JSONObject.toJSONString(result);
//        System.out.println(json);
    }


    @Test
    public void testPGSqlWithParam(){//searchType:random  orderd,使用random的分页查询比较快，排序化的查询在数据量较大时会很慢
        String condition = "{obj:'hstore_test2',searchType:'random',rows:{limit:10,offset:0},cons:[{'key':'key1','dataType':'string','opt':'like','value':'hstore_test2value130'}" +
//                ",{'key':'key2','opt':'eq','value':'13231321'}" +
                ",{'key':'key2','dataType':'string','opt':'between','end':'999'}" +
                ",{'key':'key3','dataType':'date','opt':'between','start':'2019-10-01','end':'2019-10-10'}]" +
                ",'out':['key1','key2','key3','item_id']" +
                ",'joins':[{'obj':'hstore_ref1',joinTable:'t2','joinKey':'hstore_ref1',cons:[{'key':'key_ref1','dataType':'string','opt':'like','value':'key_refvalue1302'},],out:['key_ref1']}" +
                ",{'obj':'tb_obj_68d66ccab221452698a3df91d13eab99',joinTable:'tb_obj_285bfbd89949446c9228b2771df34af6','joinKey':'OBJECT_ID',cons:[],out:[]}]}";

        String con2 = "{'joins':[{'obj':'tb_obj_285bfbd89949446c9228b2771df34af6','objId':'285bfbd89949446c9228b2771df34af6','joinKey':'TAG_ID','out':['COLUMN_NAME']}" +
                ",{'obj':'tb_obj_6e8aa73f17b84b829f3a44414c44edf3','objId':'6e8aa73f17b84b829f3a44414c44edf3','joinKey':'TAG_ID','out':['TAG_CODE']}" +
                ",{'obj':'tb_obj_68d66ccab221452698a3df91d13eab99','objId':'68d66ccab221452698a3df91d13eab99','joinKey':'OBJECT_ID','joinTable':'tb_obj_6e8aa73f17b84b829f3a44414c44edf3'}" +
                ",{'obj':'tb_obj_68d66ccab221452698a3df91d13eab99','objId':'68d66ccab221452698a3df91d13eab99','joinKey':'OBJECT_ID','joinTable':'tb_obj_285bfbd89949446c9228b2771df34af6','cons':[{'opt':'like','dataType':'string','value':'标签','key':'DESCRIPTION'}]" +
                ",'out':['DESCRIPTION']}],'obj':'tb_obj_2ea9fab127a04c6ca0e84db41a541e96','objId':'2ea9fab127a04c6ca0e84db41a541e96'" +
                ",'cons':[{'opt':'like','dataType':'string','value':'tb_tag','key':'TABLE_ID'}" +
                "        ,{'opt':'eq','dataType':'string','value':'REF_OBJECT_ID','key':'TAG_CODE'}]" +
                ",'out':['TABLE_ID','TAG_NAME','TAG_CODE']}";
        System.out.println(con2);
        JSONObject map = JSON.parseObject(con2, JSONObject.class);
        List<Map<String,Object>> result =  tagSyncMapper.testPgSql(map);
        System.out.println(result);
        String json = JSONObject.toJSONString(result);
        System.out.println(json);
    }
}
