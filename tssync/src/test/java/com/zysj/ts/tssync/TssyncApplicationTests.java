package com.zysj.ts.tssync;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zysj.ts.tssync.config.RabbitConfig;
import com.zysj.ts.tssync.dao.TagSyncMapper;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import com.zysj.ts.tssync.service.SyncStarterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TssyncApplicationTests {
    @Autowired
    private TagSyncMapper tagSyncMapper;

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
}
