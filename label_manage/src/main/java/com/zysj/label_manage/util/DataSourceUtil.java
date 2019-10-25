package com.zysj.label_manage.util;/**
 * Created by 尚先生 on 2019/9/20.
 */

import com.zysj.label_manage.entity.DataSource;
import com.zysj.service_common.common.utils.R;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 尚先生
 * @date 2019/9/20 10:08
 */

public class DataSourceUtil {
    /**
     * @author 尚先生
     * @date 2019/9/20 10:51
     * @param dataSource
     * @return
     * @throws
     * @since
    */

    public static R createWord(DataSource dataSource){

      String driver =null;
      String urler=null;
      int ss=  dataSource.getDS_TYPE();
      switch (ss){
          case 0:
              driver="com.mysql.jdbc.Driver";
              urler="jdbc:mysql:// ";
              break;
          case 1:
              driver="oracle.jdbc.driver.OracleDriver";
              urler="jdbc:oracle:thin:@";
              break;
      }
      //数据库地址,注意数据库名称.useSSL=false 不加就报一个警告
      String URL = urler+dataSource.getDS_IP()+":"+dataSource.getDS_PORT();
      //用户名DS_USERNAME
      String username=dataSource.getDS_USERNAME();
      //密码DS_PASSWORD
      String password=dataSource.getDS_PASSWORD();
      Connection conn = null;
      try {
          Class.forName(driver);

      } catch (java.lang.ClassNotFoundException e) {


          return  R.error("Cant't load Driver");
      }
      try {
          conn = DriverManager.getConnection(URL, username, password);
          System.out.println("Connect Successful.");

          return R.ok("链接测试成功");
      } catch (Exception e) {
          return R.error("Connect fail:" + e.getMessage());

      }finally {
          try {
              conn.close();
          } catch (Exception e2) {
              System.out.println("Close Connection error.");
          }
      }

    }



}
