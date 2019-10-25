package com.zysj.ts.tsdata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zysj.ts.tsdata.dao.TagDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/9/6 14:20
 */
@RestController
@RequestMapping("/tsdata")
public class TagDataQueryController {

    @Autowired
    private TagDataMapper tagDataMapper;
    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    /**
     * 企业联合奖惩详细记录；
     */
    @RequestMapping("/test")
    @ResponseBody
    public List<Map<String,Object>>  getRewardAndPunishInfo(@RequestParam String data) {
        System.out.println(data);
        JSONObject map = JSON.parseObject(data, JSONObject.class);
        return tagDataMapper.testPgSql(map);
    }


}
