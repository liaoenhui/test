package com.zysj.ts.tssync.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zysj.ts.tssync.config.RabbitConfig;
import com.zysj.ts.tssync.dao.TagSyncMapper;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncStarterService {
    @Autowired
    private TagSyncMapper tagSyncMapper;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    private final Integer SINGAL_MQ_SENT = 2;
    @Scheduled(cron = "0 0/1 * * * ?")
    public void startSyncScriptTask(){
        List<TagSyncEntity> tss =  tagSyncMapper.selectList(new QueryWrapper<TagSyncEntity>().eq("sync_script","0"));
//        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        for(TagSyncEntity tse:tss) {
            System.out.println("发送通知,同步信息：【"+tse.getObjectId()+","+tse.getTableId()+"】");
            this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, RabbitConfig.TOPIC_ETLSCRIPT, tse);
            tse.setSyncScript(SINGAL_MQ_SENT);
            tagSyncMapper.updateById(tse);
        }
    }
    @Scheduled(cron = "0 0 0 0/1 * ?")
    public void startSyncDataTask(){
        List<TagSyncEntity> tss =  tagSyncMapper.selectList(new QueryWrapper<TagSyncEntity>().eq("sync_data","0"));
//        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        for(TagSyncEntity tse:tss) {
            System.out.println("发送通知,同步信息：【"+tse.getObjectId()+","+tse.getTableId()+"】");
            this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, RabbitConfig.TOPIC_DATASYNC, tse);
            tse.setSyncData(SINGAL_MQ_SENT);
            tagSyncMapper.updateById(tse);
        }
    }
}
