package com.zysj.service_common.common.utils;

import java.io.*;

/**
 * 文件工具包
 *
 * @author : Created by Unicorn
 * @date : Created in 2019/9/19
 */
public class CreateFileUtils {
    /**
     * 文件的创建
     *
     * @param filePath
     * @return
     */
    public static boolean createFile(String filePath) {
        File f = new File(filePath);
        try {
            return f.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 文件的重命名
     *
     * @param oldPath
     * @param newPath
     * @return
     */
    public static boolean renameFile(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        return oldFile.renameTo(new File(newPath));
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        File f = new File(filePath);
        //判断是否是文件 并且存在
        if (f.isFile() && f.exists()) {
            return f.delete();
        }
        return false;
    }

    // 清空已有的文件内容，以便下次重新写入新的内容
    public static void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成文件
     *
     * @param filePath
     * @param conent
     */
    public static void writeFile(String filePath, String conent) {
        BufferedWriter out = null;
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            } else {
                clearInfoForFile(filePath);
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
