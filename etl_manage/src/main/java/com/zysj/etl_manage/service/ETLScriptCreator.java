package com.zysj.etl_manage.service;

import com.zysj.etl_manage.config.RabbitMQConfig;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ETLScriptCreator {

    @Autowired
    private EtlNeedsService etlNeedsService;
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_ETLSCRIPT)
    public void receiveMessageScript(TagSyncEntity syncObj) {
        System.out.println("【receiveTopic1监听到脚本更新命令】" + syncObj);
        etlNeedsService.etlGenerateService(syncObj);
    }

//    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_DATASYNC)
//    public void receiveMessageSync(TagSyncEntity syncObj) {
//        System.out.println("【receiveTopic2监听数据同步命令】,主体[" + syncObj+"]");
//    }




}
