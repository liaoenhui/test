package com.zysj.etl_manage.util;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;


/**
 * @Desc：word操作工具类
 * @Author：zl
 * @Date：2014-1-22下午05:03:19
 */
public class WordUtil {

    /**
     * @Desc：生成word文件
     * @Author：zl
     * @Date：2014-1-22下午05:33:42
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param filePath 文件生成的目标路径，例如：D:/wordFile/
     * @param fileName 生成的文件名称，例如：test.doc
     */

    @SuppressWarnings("unchecked")
    public static int createWord(Map dataMap,String templateName,String filePath,String fileName){
        int result = 1;
//        System.out.println(">>>>>>>>>>>传入的参数 dataMap： " + dataMap);
        try {
            //创建配置实例
            Configuration configuration = new Configuration();

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件统一放至 com.framework.template 包下面
            configuration.setClassForTemplateLoading(WordUtil.class,"/template/");

            //获取模板
            Template template = configuration.getTemplate(templateName);

            //输出文件
            File outFile = new File(filePath+ File.separator+fileName);

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));

            System.out.println(">>>>>>>>>>>>>生成文件 start");
            //生成文件

            template.process(dataMap, out);
            System.out.println(">>>>>>>>>>>>>生成文件 end");

            //关闭流
            out.flush();
            out.close();
            result = 0;
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
    public static void main(String[] args) {

        //创建配置实例
        Configuration configuration = new Configuration();

        //设置编码
        configuration.setDefaultEncoding("UTF-8");

        //ftl模板文件统一放至 com.framework.template 包下面
        configuration.setClassForTemplateLoading(WordUtil.class, "/template/");

        //获取模板
        try {
            Template template = configuration.getTemplate("promise");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
