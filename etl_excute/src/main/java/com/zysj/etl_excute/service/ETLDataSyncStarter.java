package com.zysj.etl_excute.service;

import com.zysj.etl_excute.config.RabbitMQConfig;
import com.zysj.etl_excute.service.impl.EtlExcuteServiceImpl;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ETLDataSyncStarter {
    @Autowired
    private EtlExcuteServiceImpl etlExcuteService;
    @Value("${zkRootPath}")
    private String ETL_PATH_ROOT;
    // queues是指要监听的队列的名字
//    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_ETLSCRIPT)
//    public void receiveMessageScript(TagSyncEntity syncObj) {
//        System.out.println("【receiveTopic1监听到脚本更新命令】" + syncObj);
//    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_DATASYNC)
    public void receiveMessageSync(TagSyncEntity syncObj) {
        //"/etl/1/2"
        System.out.println("【receiveTopic2监听数据同步命令】,主体[" + syncObj+"]");
        etlExcuteService.executeEtlJob(ETL_PATH_ROOT+syncObj.getObjectId()+"/"+syncObj.getTableId());
    }

}
