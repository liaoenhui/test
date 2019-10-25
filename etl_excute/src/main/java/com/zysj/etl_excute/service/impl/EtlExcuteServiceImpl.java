package com.zysj.etl_excute.service.impl;

import com.zysj.etl_excute.zookeeper.BaseZookeeper;
import com.zysj.service_common.common.utils.CreateFileUtils;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 监听mq消息，读取zk，生成文件，执行etl任务
 *
 * @author : Created by Unicorn
 * @date : Created in 2019/9/19
 */
@Service
public class EtlExcuteServiceImpl {

    private BaseZookeeper zk = new BaseZookeeper();

    @Value("${zkURL}")
    private String zkURL;
//    @PostConstruct
//    public void init() throws Exception {
//        // 构造一个连接zookeeper的客户端对象
//        //创建一个与服务器的连接 需要(服务端的 ip+端口号)(session过期时间)(Watcher监听注册)
//        zk = new BaseZookeeper();
//        zk.connectZookeeper("47.106.37.11:2181");
//        System.out.println("zookeeper 创建连接");
//    }
    public void ensureZkConnection() throws Exception {
        // 构造一个连接zookeeper的客户端对象
        //创建一个与服务器的连接 需要(服务端的 ip+端口号)(session过期时间)(Watcher监听注册)
        if(zk==null) zk = new BaseZookeeper();
        zk.ensureConnection(zkURL);
        System.out.println("zookeeper 创建连接");
    }
    @Value("${etl.pythonPath}")
    private String pythonPath;

    @Value("${etl.dataxPath}")
    private String dataxPath;

    @Value("${etl.localPath}")
    private String localPath;


    private static final String DEFAULT_DATAX_PATH = "D:/框架资料及文档/datax/datax/bin/datax.py";

    private static final String DEFAULT_PYTHON_PATH = "D:/python27/python";
    /**
     * 读取zookeeper里的json内容，生成本地文件
     *
     * @param zkjsonPath
     * @return
     */
    public void executeEtlJob(String zkjsonPath) {
        excuteEtl(retrieveETLFileData(zkjsonPath));
    }


    private String retrieveETLFileData(String zkjsonPath){
        String data = null;
        try {
            ensureZkConnection();
            data = zk.getData(zkjsonPath);
            System.out.println(data);
            // 生成文件
            if (data != null && data != "") {

                // 截取路径
                String p = cutStr(zkjsonPath);
                String filePath = localPath+ "/" + p+".json";
//                String filePath = "/usr/local/files" + zkjsonPath + ".json";
//                String filePath = "D:/files/" + "test" + ".json";
                System.out.println(filePath);
                CreateFileUtils.writeFile(filePath, data);
                return filePath;
                // 执行etl任务
//                excuteEtl(filePath);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 截取路径
     * @param zkjsonPath
     * @return
     */
    private String cutStr(String zkjsonPath) {
        String s = "";
        if (zkjsonPath != "") {
           s  = zkjsonPath.substring(zkjsonPath.lastIndexOf("/") + 1);
        }

        return s;
    }

    /**
     * 执行etl任务
     *
     * @param filePath
     */
    public void excuteEtl(String filePath) {
        String str1 = "同步审批事项定时任务开始----------------";
        String str2 = "同步审批事项定时任务结束----------------";
        try {
            ensurePaths();
//            String cmd = "D:/python27/python " + dataxPath + " " + filePath;
            String cmd = pythonPath + " " + dataxPath + " " + filePath;
            System.out.println(cmd);
            System.out.println(str1);
            long temp = System.currentTimeMillis();
            Process process = Runtime.getRuntime().exec(cmd);
            //返回信息写入流用控制台打出来,若子进程process的标准输入、输出流将缓冲区占满，子进程将无法再继续写入、输出数据，进程挂住；
            //此处转码，不然控制台中文乱码
            BufferedReader in = null;

            in = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));

            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);

            }
            in.close();
            process.waitFor();  //等待子进程运行结束；

            System.out.println(str2);
            System.out.println("总耗时：" + (System.currentTimeMillis() - temp));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void ensurePaths(){
        if(dataxPath == null)  dataxPath = DEFAULT_DATAX_PATH;
        if(pythonPath == null) pythonPath = DEFAULT_PYTHON_PATH;
    }
//    public static void main(String[] args) {
//        EtlExcuteServiceImpl etlExcuteService = new EtlExcuteServiceImpl();
//        try {
//            etlExcuteService.init();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String zkjsonPath = "/etl/1/1";
//        long temp = System.currentTimeMillis();
//        etlExcuteService.getZKJsonInfo(zkjsonPath);
//        System.out.println("任务总耗时：" + (System.currentTimeMillis() - temp));
//
//    }
}
