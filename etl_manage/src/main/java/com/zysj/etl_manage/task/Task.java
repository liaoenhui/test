package com.zysj.etl_manage.task;//package com.zysj.etl_manage.task;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.zysj.etl_manage.dao.EtlInfoDao;
//import com.zysj.etl_manage.dao.EtlNeedsDao;
//import com.zysj.etl_manage.entity.EtlParameter;
//import com.zysj.etl_manage.entity.TagSh;
//import com.zysj.etl_manage.util.PathConstant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//
//
//
//@Configuration // 配置beans,
//@EnableScheduling // <task:*>, 让spring进行任务调度
//public class Task {
//
//	@Autowired
//	EtlNeedsDao etlNeedsDao;
//
//	@Autowired
//	EtlInfoDao etlInfoDao;
//
//	//获得任务文件夹下的所有json文件
//	public File[] getFileList(String taskPath){
//		File file=new File(taskPath);
//		File[] files=file.listFiles((File f)->f.getName().endsWith(".json"));
//		return files;
//	}
//
//	@Scheduled(cron = "0 0 */4 * * ?")	//每隔4小时执行
//	public void doTask(){
//		List<TagSh> TagShList= etlNeedsDao.etlTagSh(0);
//		for (int i=0;i<TagShList.size();i++){
//			String tabid= TagShList.get(i).getTABLE_ID();
//			List<EtlParameter> etlList=etlInfoDao.getEtlInfo(tabid);
//			int count_before = 0;
//			int count_after = 0;
//			File[] files=getFileList("E:\\工作文档\\"+etlList.get(0).getOBJECT_NAME());
//			for(File f:files){
//				String cmd = "python "+PathConstant.EXCETE_DATAX_PATH+" "+f.getAbsolutePath();
//				try {
//					String str1 = "同步审批事项定时任务开始----------------";
//					String str2 = "同步审批事项定时任务结束----------------";
//
//
//					System.out.println(str1);
//					long temp = System.currentTimeMillis();
////			System.out.println("同步命令："+cmd);
//					Process process = Runtime.getRuntime().exec(cmd);
//
//					//返回信息写入流用控制台打出来,若子进程process的标准输入、输出流将缓冲区占满，子进程将无法再继续写入、输出数据，进程挂住；
//					//此处转码，不然控制台中文乱码
//					BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"utf-8"));
//					String line = null;
//					while ((line = in.readLine()) != null) {
//						System.out.println(line);
//					}
//					in.close();
//					process.waitFor();  //等待子进程运行结束；
//					System.out.println(str2);
//					System.out.println("总耗时："+(System.currentTimeMillis()-temp));
//				}
//				catch (Exception e){
//					e.printStackTrace();
//				}finally {
//					//保存每次同步的数据记录；
//					Map<String,Object> params = new HashMap<String, Object>();
//					params.put("TYPE", "审批事项同步");
//					params.put("COUNT", (count_after-count_before));
//					params.put("DES_TABLE", "tb_approve_process");
//				}
//			}
//		}
//
//	}
//}
