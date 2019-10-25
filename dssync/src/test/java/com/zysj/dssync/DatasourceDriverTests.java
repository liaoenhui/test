package com.zysj.dssync;

import com.zysj.ds.driver.DatasourceDriver;
import com.zysj.ds.driver.selector.DatasourceDriverSelector;
import com.zysj.dssync.dao.TagDatasourceMapper;
import com.zysj.dssync.dao.metadata.MetadataMapper;
import com.zysj.dssync.dn.DatasouceConnector;
import com.zysj.dssync.entity.Datasource;
import com.zysj.dssync.entity.metadata.ColumnMeta;
import com.zysj.dssync.entity.metadata.TableMeta;
import com.zysj.dssync.service.metadata.MetadataRetriever;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceDriverTests {
    @Autowired
    private DatasourceDriverSelector datasourceDriverSelector;
    @Test
    public void DatasourceDriver() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Datasource ds = new Datasource();
        ds.setDsType(1);
        int ss = (int) ds.getDsType();
        DatasourceDriver dsd = datasourceDriverSelector.getDatasourceDriver(ds);
        System.out.println(dsd.initDriver(ds));
        System.out.println(dsd.initConnection(ds));
    }
}
