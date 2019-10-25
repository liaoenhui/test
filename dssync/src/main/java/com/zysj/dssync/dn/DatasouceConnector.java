package com.zysj.dssync.dn;

import com.zysj.dssync.entity.Datasource;

import java.sql.Connection;
import java.sql.DriverManager;
/*
 * @param dataSource
 * @return
 * @throws
 * @author Konals
 * @date 2019/9/26 10:51
 * @todo 需重构，目前仅支持MySql，Oracle，而且未进行抽象
 * @since
 */
@Deprecated
public class DatasouceConnector {
    public static Connection createConnection(Datasource dataSource) {
        String driver = null;
        String urler = null;
        String param = null;
        int ss = (int) dataSource.getDsType();
        switch (ss) {
            case 0:
                driver = "com.mysql.jdbc.Driver";
                urler = "jdbc:mysql://";
                param = "?useUnicode=true&characterEncoding=utf-8";
                break;
            case 1:
                driver = "oracle.jdbc.driver.OracleDriver";
                urler = "jdbc:oracle:thin:@";
                break;
        }
        if(driver==null) return null;
        //数据库地址,注意数据库名称.useSSL=false 不加就报一个警告
        String URL = urler + dataSource.getDsIp() + ":" + dataSource.getDsPort()+"/"+dataSource.getDsEname()+param;
        //用户名DS_USERNAME
        String username = dataSource.getDsUsername();
        //密码DS_PASSWORD
        String password = dataSource.getDsPassword();
        Connection conn = null;
//        System.out.println(URL);
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
           return null;
        }try {
            conn = DriverManager.getConnection(URL, username, password);
            return conn;
        } catch (Exception e) {
            return null;
        }
    }
}
