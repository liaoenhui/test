package com.zysj.label_data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : Created by Unicorn
 * @date : Created in 2019/9/9
 */
@Component
public class MongoDBDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Map<String, Object>> getList() {
        Query query = new Query(Criteria.where("id").is("1"));
        return mongoTemplate.findOne(query, List.class);
    }
}
