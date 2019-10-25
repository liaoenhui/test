package com.zysj.label_manage.dao;

import com.zysj.label_manage.entity.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
@Mapper
public interface DatasourceDao {



    /**
     * 获取数据源列表
     * @return
     */
    List<Map<String, Object>> getDatasourceList();

    /**
     * 获取数据库版本信息
     * @param dataTypename 数据类型 用来获取相应的版本信息
     * @return dataTypename 数据库版本
     */
    List<Map<String, Object>> getDatasourceEditionList( String dataTypename);

    /**
     * 获取数据库类型信息
     * @return 数据库类型
     */
    List<Map<String, Object>> getDatasourceTypeList();

    /**
     * 新增数据源
     * @param dataSource
     */
    void addDatasourceInfo(DataSource dataSource);

    /**
     *  模糊查询
     *@param name  数据源名称 用于模糊查询
     *@return 返回模糊查询到的数据源英文名和数据源id
     */
    List<Map<String, Object>> getDatasource(@Param("name")String name);


    /**
     * 修改数据源
     * @param dataSource
     */
    void editDatasourceInfo(DataSource dataSource);

    /**
     * 获取单个数据源信息
     * @param ds_ID
     * @return
     */
   Map<String,Object> getDatasourceInfo(String ds_ID);
}
