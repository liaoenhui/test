package com.zysj.etl_manage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

//    //topic
//    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE_ETLSCRIPT = "q.etlscript";
    public static final String TOPIC_QUEUE_DATASYNC = "q.datasync";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String TOPIC_ETLSCRIPT = "elt.scriptcreate";
    public static final String TOPIC_DATASYNC = "elt.datasync";
    //fanout
//    public static final String FANOUT_QUEUE_ETL_SCRIPT = "fanout.etlscript";
//    public static final String FANOUT_QUEUE2 = "fanout.queue2";
//    public static final String FANOUT_EXCHANGE = "fanout.exchange";

//    //redirect模式
//    public static final String DIRECT_QUEUE1 = "direct.queue1";
//    public static final String DIRECT_EXCHANGE = "direct.exchange";
//    public static final String DIRECT_QUEUE2 = "direct.queue2";

    /**
     * Topic模式
     *
     * @return
     */
    @Bean
    public Queue topicQueueETLScript() {
        return new Queue(TOPIC_QUEUE_ETLSCRIPT);
    }
    @Bean
    public Queue topicQueueDataSync() {
        return new Queue(TOPIC_QUEUE_DATASYNC);
    }
//    @Bean
//    public Queue topicQueueDataSync() {
//        return new Queue(TOPIC_DATASYNC);
//    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBindingETLScript() {
        return BindingBuilder.bind(topicQueueETLScript()).to(topicExchange()).with(TOPIC_ETLSCRIPT);
    }
    @Bean
    public Binding topicBindingDatasync() {
        return BindingBuilder.bind(topicQueueDataSync()).to(topicExchange()).with(TOPIC_DATASYNC);
    }

    /**
     * Fanout模式
     * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     *
     * @return
     */
//    @Bean
//    public Queue fanoutQueue1() {
//        return new Queue(FANOUT_QUEUE_ETL_SCRIPT);
//    }
//
//    @Bean
//    public Queue fanoutQueue2() {
//        return new Queue(FANOUT_QUEUE2);
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//
//    @Bean
//    public Binding fanoutBinding1() {
//        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding fanoutBinding2() {
//        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
//    }

    /**
     * direct模式
     * 消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
     *
     * @return
     */
//    @Bean
//    public Queue directQueue1() {
//        return new Queue(DIRECT_QUEUE1);
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange(DIRECT_EXCHANGE);
//    }
//
//    @Bean
//    public Binding directBinding1() {
//        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("direct.pwl");
//    }

}