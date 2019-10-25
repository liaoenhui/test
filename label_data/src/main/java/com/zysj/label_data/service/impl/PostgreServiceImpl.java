package com.zysj.label_data.service.impl;

import com.zysj.label_data.service.PostgreService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * mongodb demo测试
 * @author : Created by Unicorn
 * @date : Created in 2019/9/9
 */
@Service
public class PostgreServiceImpl implements PostgreService {


    @Override
    public boolean createTable(Map<String, Object> params) {

        return false;
    }
}

