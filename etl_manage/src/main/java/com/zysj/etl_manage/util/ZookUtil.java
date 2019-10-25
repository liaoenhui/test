package com.zysj.etl_manage.util;/**
 * Created by 尚先生 on 2019/9/17.
 */

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * @author 尚先生
 * @date 2019/9/17 15:14
 */

public  class ZookUtil {

    private static BaseZookeeper zk = null;
    @Value("${Zookeeperurl}")
    private static String Zookeeperurl;
    @PostConstruct
    public static void init() throws Exception {
        // 构造一个连接zookeeper的客户端对象
        //创建一个与服务器的连接 需要(服务端的 ip+端口号)(session过期时间)(Watcher监听注册)
        BaseZookeeper zk = new BaseZookeeper();
        zk.connectZookeeper(Zookeeperurl);
        System.out.println("zookeeper 创建连接");
    }

    public  static void createWord(String filePath, String fileName) {
        try {
            init();
            //将文件的内容转换成输入流
            InputStream is = new FileInputStream(filePath);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] data = null;
            byte[] datar = new byte[0];
            int ch;
            while ((ch = is.read()) != -1) {
                bytestream.write(ch);
            }
            data = bytestream.toByteArray();
//            System.out.println(new String(data));
            //判断路径是否存在
            String[] etl = fileName.split("/");
            zk.createNode("/etl/" + etl[0], new String(datar));
            zk.createNode("/etl/" + fileName, new String(datar));
            zk.setData("/etl/" + fileName, new String(data));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
