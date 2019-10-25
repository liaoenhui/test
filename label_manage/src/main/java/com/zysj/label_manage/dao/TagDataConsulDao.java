package com.zysj.label_manage.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by 尚先生 on 2019/10/17.
 */

@FeignClient("tag-data")
public interface TagDataConsulDao {
    /**
     * pg数据库查询
     * @param data
     * @return
     */
    @RequestMapping("/tsdata/query")
    List<Map<String,Object>> tsDataQuery(@RequestParam(value = "data")String data);
    /**
     * pg数据库存储
     * @param trans 转换之后的json
     * @param origin 原始的json 前端传过来的
     * @param groupId 群体id
     * @return
     */
    @RequestMapping("/tsdata/saveAnalyzedGroup")
    String tsDataSaveAnalyzedGroup(@RequestParam(value = "trans")String trans,@RequestParam(value = "origin")String origin,@RequestParam(value = "groupId")String groupId);
}
