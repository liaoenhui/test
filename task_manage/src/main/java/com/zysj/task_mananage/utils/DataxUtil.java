package com.zysj.task_mananage.utils;/**
 * Created by 尚先生 on 2019/9/18.
 */

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.zookeeper.*;

/**
 * @author 尚先生
 * @date 2019/9/18 13:53
 */

public class DataxUtil {
    /**
     * @param etlpath Zookee
     * @return
     * @throws
     * @author 尚先生
     * @date 2019/9/18 13:56
     * @since
     */
    public static void ImplementationOfEtl(String etlpath) {
        String connectString = "127.0.0.1:2181";
        int sessionTimeout = 4000;
        String[] etl = etlpath.split("/");

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        };

        try {
            //TODO MQ需要分发 etlpath
            ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
            byte[] b = zooKeeper.getData(etlpath, false, null);
            System.out.println(new String(b));
            //输出文件
            File outFile = new File("E:" + File.separator + etl[0] + File.separator + etl[1] + File.separator + etl[2] + ".json");

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            //向文件中写入数据
            FileWriter writer;
            writer = new FileWriter("E:" + File.separator + etl[0] + File.separator + etl[1] + File.separator + etl[2] + ".json");
            writer.write(new String(b));
            writer.flush();
            writer.close();

            String cmd = "python " + PathConstant.EXCETE_DATAX_PATH + " " + "E:" + File.separator + etl[0] + File.separator + etl[1] + File.separator + etl[2] + ".json";

            String str1 = "同步审批事项定时任务开始----------------";
            String str2 = "同步审批事项定时任务结束----------------";
            System.out.println(str1);
            long temp = System.currentTimeMillis();
            Process process = Runtime.getRuntime().exec(cmd);

            //返回信息写入流用控制台打出来,若子进程process的标准输入、输出流将缓冲区占满，子进程将无法再继续写入、输出数据，进程挂住；
            //此处转码，不然控制台中文乱码
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();  //等待子进程运行结束；

            System.out.println(str2);
            System.out.println("总耗时：" + (System.currentTimeMillis() - temp));
            //删除文件
            outFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
