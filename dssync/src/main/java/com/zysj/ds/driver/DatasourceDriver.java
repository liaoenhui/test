package com.zysj.ds.driver;

import com.zysj.dssync.entity.Datasource;

import java.sql.Connection;

public interface DatasourceDriver {
    String initDriver(Datasource ds);
    Connection initConnection(Datasource ds);
}
