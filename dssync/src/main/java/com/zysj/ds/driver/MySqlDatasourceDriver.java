package com.zysj.ds.driver;

import com.zysj.dssync.entity.Datasource;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDatasourceDriver implements DatasourceDriver {

    private static final String DEFAULt_VErSION_DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
//    useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true
    private static final String MYSQL6_VErSION_DB_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USE_UNICODE_DEFAULT = "true";
    private static final String DEFAULT_CHARACTER_ENCODING = "utf8";
    private static final String DEFAULT_CHARACTER_SETRESULTS = "utf8";
    private static final String DEFAULT_USE_SSL = "false";
    private static final String CONNECTOR_URL_PREFIX ="jdbc:mysql://";
    private static final String CONNECTOR_URL_DEFALUT = "useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false";
    @Override
    public String initDriver(Datasource ds) {
        String driverName = checkVersion(ds.getDsVersionId());
        try {
            Class.forName(driverName);
            return driverName;
        } catch (ClassNotFoundException e) {
            System.out.println("未找到相应驱动："+e.getCause()+",请添加相应数据库驱动包后再试");
            return null;
        }
    }
    private String checkVersion(String version){
        return DEFAULt_VErSION_DB_DRIVER_NAME;
    }
    @Override
    public Connection initConnection(Datasource dataSource) {
        String driverName =  initDriver(dataSource);
        if(driverName==null) {
            return null;
//            throw new RuntimeException("找不到对应的驱动。");
        }
        //数据库地址,注意数据库名称.useSSL=false 不加就报一个警告
        String URL = CONNECTOR_URL_PREFIX + dataSource.getDsIp() + ":" + dataSource.getDsPort()+"/"+dataSource.getDsEname()+"?"+CONNECTOR_URL_DEFALUT;
        //用户名DS_USERNAME
        String username = dataSource.getDsUsername();
        //密码DS_PASSWORD
        String password = dataSource.getDsPassword();
        Connection conn = null;
       try {
            conn = DriverManager.getConnection(URL, username, password);
            return conn;
        } catch (Exception e) {
           return null;
        }
    }
}
