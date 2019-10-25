package com.zysj.dssync;

import com.zysj.dssync.dao.TagDatasourceMapper;
import com.zysj.dssync.dao.metadata.MetadataMapper;
import com.zysj.dssync.dn.DatasouceConnector;
import com.zysj.dssync.entity.Datasource;
import com.zysj.dssync.entity.metadata.ColumnMeta;
import com.zysj.dssync.entity.metadata.TableMeta;
import com.zysj.dssync.service.DatasourceDetector;
import com.zysj.dssync.service.metadata.MetadataRetriever;
import org.apache.http.client.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DssyncApplicationTests {
    @Value("${test}")
    private String mybatisLocation;
    @Autowired
    private TagDatasourceMapper tagDatasourceMapper;
    @Autowired
    private MetadataMapper metadataMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testListAll() {
//       tagDatasourceMapper.listAll();
       List<Datasource> dsl = tagDatasourceMapper.listNeedSync();
       for(Datasource ds:dsl){
           Connection c = DatasouceConnector.createConnection(ds);
           if(c==null){
                ds.setDsSyncState(3);
               System.out.println("无法连接ds :"+ds);
               tagDatasourceMapper.updateState(ds);
           }else{
               try {
                   DatabaseMetaData dbMetaData = c.getMetaData();
                   List<TableMeta> tml = MetadataRetriever.getAllTables(dbMetaData,ds);
                   metadataMapper.insertBatchTableMeta(tml);
                   metadataMapper.mergeTableMeta();
                   ds.setDsSyncState(1);
                   tagDatasourceMapper.updateState(ds);
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
    }
    @Test
    public void columnMetaSync() {
//       tagDatasourceMapper.listAll();
        List<Datasource> dsl = tagDatasourceMapper.listNeedSync();
        for(Datasource ds:dsl){
            Connection c = DatasouceConnector.createConnection(ds);
            if(c==null){
                ds.setDsSyncState(3);
                tagDatasourceMapper.updateState(ds);
            }else{
                try {
                    DatabaseMetaData dbMetaData = c.getMetaData();
                    List<ColumnMeta> cml = MetadataRetriever.getAllColumns(dbMetaData, ds.getDsEname(), ds.getDsId());
                    metadataMapper.insertBatchColumnMeta(cml);
                    metadataMapper.mergeColumnMeta();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void mergeTableMeta() {
        metadataMapper.mergeTableMeta();
    }
    @Test
    public void mergeColumnMeta() {
        metadataMapper.mergeColumnMeta();
    }
    @Test
    public void testUpdateState() {
       Datasource ds = new Datasource();
       ds.setDsId("1");
        ds.setDsSyncState(1);
        tagDatasourceMapper.updateState(ds);
    }


}
