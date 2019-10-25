package com.zysj.label_data;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zysj.label_data.entity.MongodbTestModel;
import com.zysj.label_data.service.MongoDBService;
import com.zysj.label_data.utils.MongodbUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mongodb demo测试
 *
 * @author : Created by Unicorn
 * @date : Created in 2019/9/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTest {

    @Autowired
    private MongoDBService mongoDBService;

    @Test
    public void testMongoDB() {
        MongodbTestModel obj = new MongodbTestModel();
        String[] findKeys = {"name"};
        String[] findValues = {"zs"};
        String collectionName = "tag_datasource";
        Object findOne = MongodbUtils.findOne(obj, findKeys, findValues, collectionName);
        System.out.println(findOne);
    }


    @Test
    public void test() {
        DBObject obj = new BasicDBObject();
        obj.put("id", "1"); // userId>=2的条件
        Query query = new BasicQuery(obj.toString());
        Map<String, Object> map = new HashMap<>();
        List<? extends Map> list = MongodbUtils.select(query, map, "tag_datasource");

        for (Map map1 : list) {
            System.out.println(map1);
        }
    }
}
