package com.zysj.zookeeper;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperApplicationTests {

    BaseZookeeper zk = null;
    @Before
    public void init()  throws Exception{
        // 构造一个连接zookeeper的客户端对象
        //创建一个与服务器的连接 需要(服务端的 ip+端口号)(session过期时间)(Watcher监听注册)
        zk = new BaseZookeeper();
        zk.connectZookeeper("47.106.37.11:2181");
        System.out.println("zookeeper 创建连接");
    }


    @Test
    public void testCreate() throws Exception{
        Map<String, Object> map = new HashMap<>();

        map.put("name","张三");
        map.put("age",29);
        map.put("sex","man");

        // 参数1：要创建的节点路径  参数2：数据
        String create = zk.createNode("/etl_json/demo2.json", JSON.toJSONString(map));

        System.out.println(create);
    }

    @Test
    public void getData() throws Exception {
        String data = zk.getData("/etl_json/demo2.json");

        System.out.println(data);
    }

}
