package com.zysj.etl_manage.service.impl;

import com.zysj.etl_manage.service.FileSystemStoreService;
import com.zysj.etl_manage.util.BaseZookeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class ZookeeperStoreService implements FileSystemStoreService {
    @Value("${zkURL}")
    private String zkURL;
    @Value("${zkRootPath}")
    private String ETL_PATH_ROOT;
    private BaseZookeeper zk = new BaseZookeeper();
    public void ensureZkConnection() throws Exception {
        // 构造一个连接zookeeper的客户端对象
        //创建一个与服务器的连接 需要(服务端的 ip+端口号)(session过期时间)(Watcher监听注册)
        if(zk==null) zk = new BaseZookeeper();
        zk.ensureConnection(zkURL);
        System.out.println("zookeeper 创建连接");
    }
    @Override
    public void upload(String filePath, String remoteFileName) {
        try {
            ensureZkConnection();//由于每次zk连接后，超过某段时间session会过期，所以每次生成时重新创建
            //将文件的内容转换成输入流
            InputStream is = new FileInputStream(filePath);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] data = null;
            byte[] datar = new byte[0];
            int ch;
            while ((ch=is.read())!=-1){
                bytestream.write(ch);
            }
            data = bytestream.toByteArray();
            System.out.println(new String(data));
            //判断路径是否存在
            String[] etl =remoteFileName.split("/");
            zk.createNode(ETL_PATH_ROOT+etl[0],new String(datar));
            zk.createNode(ETL_PATH_ROOT+remoteFileName,new String(datar));
            zk.setData(ETL_PATH_ROOT+remoteFileName,new String(data));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
