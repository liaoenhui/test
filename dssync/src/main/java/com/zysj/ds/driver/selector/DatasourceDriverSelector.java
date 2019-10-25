package com.zysj.ds.driver.selector;

import com.zysj.ds.driver.DatasourceDriver;
import com.zysj.dssync.entity.Datasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DatasourceDriverSelector {
    @Autowired
    private Environment env;
    private static final String DS_DRIVER_TYPE_PREFIX = "ds.driver.driver_";

    public DatasourceDriver getDatasourceDriver(Datasource ds){
        int ss = (int) ds.getDsType();
        String driverClass = env.getProperty(DS_DRIVER_TYPE_PREFIX+ss);
        System.out.println(driverClass);
        try {
            return (DatasourceDriver) Class.forName(driverClass).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("未找到相应驱动："+driverClass+",请添加相应数据库驱动包后再试");
            return null;
        }

    }
}
