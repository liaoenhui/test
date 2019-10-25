package com.zysj.label_manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.zysj.label_manage.dao.DatasourceDao;
import com.zysj.label_manage.entity.DataSource;
import com.zysj.label_manage.service.DatasourceService;
import com.zysj.service_common.common.utils.DateUtils;
import com.zysj.service_common.common.utils.R;
import com.zysj.service_common.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
@Service
public class DatasourceServiceImpl implements DatasourceService {

    @Autowired
    private DatasourceDao datasourceDao;

    /**
     * 获取数据源
     * @return
     */
    @Override
    public R getDatasourceList() {
        List<Map<String,Object>> list = datasourceDao.getDatasourceList();

        if (list != null && list.size() != 0) {
            return R.ok().put("list",JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }

    /**
     * 获取数据库版本信息
     * @return map
     */
    @Override
    public R getDatasourceEditionList(String dataTypename) {
        List<Map<String,Object>> list = datasourceDao.getDatasourceEditionList(dataTypename);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空请联系维护人员添加数据库版本信息").put("list",list);
        }
    }
    /**
     * 获取数据库类型信息
     * @return map
     */
    @Override
    public R getDatasourceTypeList() {
        List<Map<String,Object>> list = datasourceDao.getDatasourceTypeList();

        if (list != null && list.size() != 0) {
            return R.ok().put("list",JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空请联系维护人员添加数据库版本信息").put("list",list);
        }
    }
    /**
     * 新增数据源
     * @param dataSource
     * @return
     */
    @Override
    @Transactional
    public R addDatasourceInfo(DataSource dataSource) {


            dataSource.setDS_ID(UuidUtil.get32UUID());
            dataSource.setDS_UPDATETIME(DateUtils.formatDate(new Date()));

            try {
                datasourceDao.addDatasourceInfo(dataSource);
                return R.ok();
            }catch (Exception e){
                // 事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
                return R.error("新增失败");
            }



    }


    /**
     * 修改数据源
     * @param dataSource
     * @return
     */
    @Override
    public R editDatasourceInfo(DataSource dataSource) {

        dataSource.setDS_UPDATETIME(DateUtils.formatDate(new Date()));
        try {
            datasourceDao.editDatasourceInfo(dataSource);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("修改失败");
        }

    }

    /**
     * 模糊查询数据源
     * @param name
     * @return
     */
    @Override
    public R getDatasource(String name) {
        List<Map<String,Object>> list = datasourceDao.getDatasource(name);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("请查看是否输入正确").put("list",list);
        }

    }


    /**
     * 获取单个数据源信息
     * @param ds_ID
     * @return
     */
    @Override
    public R getDatasourceInfo(String ds_ID) {
        Map<String,Object> list = datasourceDao.getDatasourceInfo(ds_ID);
        if (list != null && list.size() != 0) {
            return R.ok().put("list",JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }
}
