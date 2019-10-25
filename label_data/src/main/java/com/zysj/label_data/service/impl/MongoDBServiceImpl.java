package com.zysj.label_data.service.impl;

import com.zysj.label_data.service.MongoDBService;
import com.zysj.label_data.utils.PostgreSQLUtils;
import org.springframework.stereotype.Service;

/**
 * mongodb demo测试
 *
 * @author : Created by Unicorn
 * @date : Created in 2019/9/9
 */
@Service
public class MongoDBServiceImpl implements MongoDBService {

    public static void main(String[] args) {
        String sql ="CREATE TABLE COMPANY02 " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";


       PostgreSQLUtils.createTable(sql);

    }
}

