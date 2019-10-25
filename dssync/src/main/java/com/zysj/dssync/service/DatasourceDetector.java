package com.zysj.dssync.service;

import com.zysj.ds.driver.selector.DatasourceDriverSelector;
import com.zysj.dssync.dao.TagDatasourceMapper;
import com.zysj.dssync.dao.metadata.MetadataMapper;
import com.zysj.dssync.dn.DatasouceConnector;
import com.zysj.dssync.entity.Datasource;
import com.zysj.dssync.entity.metadata.ColumnMeta;
import com.zysj.dssync.entity.metadata.TableMeta;
import com.zysj.dssync.service.metadata.MetadataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

@Service
@EnableScheduling
public class DatasourceDetector {
    private static final Integer SYNC_STATE_ERROR = 3;
    private static final Integer SYNC_STATE_SUCCESS = 1;
    @Autowired
    private TagDatasourceMapper tagDatasourceMapper;
    @Autowired
    private DatasourceDriverSelector datasourceDriverSelector;
    @Autowired
    private MetadataMapper metadataMapper;
    private void syncDs(List<Datasource> dsl){
        System.out.println("===========开始同步=============");
        for(Datasource ds:dsl){
            Connection c = datasourceDriverSelector.getDatasourceDriver(ds).initConnection(ds);
            if(c==null){
                System.out.println("数据源{"+ds.getDsCname()+"}，ID:{"+ds.getDsId()+"} 无法连接，放弃同步");
                if(ds.getDsSyncState()!=SYNC_STATE_ERROR){
                    ds.setDsSyncState(SYNC_STATE_ERROR);
                    tagDatasourceMapper.updateState(ds);
                }
            }else{
                try {
                    long s = System.currentTimeMillis();
                    DatabaseMetaData dbMetaData = c.getMetaData();
                    List<TableMeta> tml = MetadataRetriever.getAllTables(dbMetaData,ds);
                    System.out.println("开始同步{"+ds.getDsCname()+"}");
                    System.out.println(ds.getDsCname()+"：同步表---------");
                    metadataMapper.insertBatchTableMeta(tml);
//                    metadataMapper.mergeTableMeta();//同步表元数据
                    System.out.println(ds.getDsCname()+"：表同步完成---------");
                    System.out.println(ds.getDsCname()+"：同步字段---------");
                    List<ColumnMeta> cml = MetadataRetriever.getAllColumns(dbMetaData, ds.getDsEname(), ds.getDsId());
                    metadataMapper.insertBatchColumnMeta(cml);
//                    metadataMapper.mergeColumnMeta();//同步字段元数据
                    metadataMapper.mergeMeta();//同步表与字段元数据
                    System.out.println(ds.getDsCname()+"：字段同步完成---------");
                    System.out.println(ds.getDsCname()+"：更新数据源状态---------");
                    ds.setDsSyncState(SYNC_STATE_SUCCESS);
                    tagDatasourceMapper.updateState(ds);
                    System.out.println(ds.getDsCname()+"：同步完成---------");
                    long e = System.currentTimeMillis();
                    System.out.println(ds.getDsCname()+"：同步共使用："+(e-s)+"ms");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void sync(){
        syncDs(tagDatasourceMapper.listNeedSync());
    }
    @Scheduled(cron = "0 0 0/8 * * ?")
    public void syncDaily(){
        syncDs(tagDatasourceMapper.listStates(new int[1]));
    }
}
