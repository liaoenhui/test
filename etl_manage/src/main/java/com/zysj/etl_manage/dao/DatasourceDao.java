package com.zysj.etl_manage.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
@Mapper
public interface DatasourceDao {

    /*
        获取数据源列表
     */
    List<Map<String, Object>> getDatasourceList();

    /*
        新增数据源
     */
    void addDatasourceInfo(Map<String, Object> params);

    /*
        修改数据源
     */
    void editDatasourceInfo(Map<String, Object> params);

    /*
        获取单个数据源信息
     */
    Map<String,Object> getDatasourceInfo(Map<String, Object> params);
}
