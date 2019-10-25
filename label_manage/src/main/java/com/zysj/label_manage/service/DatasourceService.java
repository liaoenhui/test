package com.zysj.label_manage.service;

import com.zysj.label_manage.entity.DataSource;
import com.zysj.service_common.common.utils.R;

/**
 * 数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
public interface DatasourceService {
    /**
     * 获取数据源列表
     * @return
     */
    R getDatasourceList();

    /**
     * 获取数据源版本信息
     * @return 数据源版本
     */
    R getDatasourceEditionList(String dataTypename);
    /**
     * 获取数据源类型信息
     * @return 数据源类型
     */
    R getDatasourceTypeList();

    /**
     * 新增数据源
     * @param dataSource
     * @return
     */

    R addDatasourceInfo(DataSource dataSource);

    /**
     * 修改数据源
     * @param dataSource
     * @return
     */

    R editDatasourceInfo(DataSource dataSource);

    /**
     * 根据数据源英文模糊搜索
     * @param name
     * @return 返回模糊查询到的数据源英文名和数据源id
     */
    R getDatasource(String name);

    /**
     * 获取单个数据源信息
     * @param ds_ID
     * @return
     */
    R getDatasourceInfo(String ds_ID);
}
