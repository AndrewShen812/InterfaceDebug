/**
 * @项目名称：MySQLTest
 * @文件名：FileUtils.java
 * @版本信息：
 * @日期：2015年6月12日
 * @Copyright 2015 www.517na.com Inc. All rights reserved.
 */
package com.na517.lf.debug.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @项目名称：MySQLTest
 * @类名称：FileUtils
 * @类描述：文件操作工具类
 * @创建人：lianfeng
 * @创建时间：2015年6月12日 下午7:59:38
 * @修改人：lianfeng
 * @修改时间：2015年6月12日 下午7:59:38
 * @修改备注：
 * @version
 */
public class FileUtils {
    
    public static String getFilePath(String filaname) throws IOException {
        File file = new File(filaname);
        if(!file.exists()) {
            file.createNewFile();
        }
        
        return file.getAbsolutePath();
    }
    
    /**
     * @description 读文件
     * @date 2015年6月14日
     * @param path 文件路径
     * @return
     * @throws IOException
     */
    public static String readFromFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            file.createNewFile();
        }
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        FileInputStream fin = new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((fin.read(buf)) != -1) {
            sb.append(new String(buf));
            buf = new byte[1024];// 重新生成，避免和上次读取的数据重复
        }
        fin.close();
        
        return new String(sb.toString().getBytes(), "utf-8").trim();
    }
    
    /**
     * @description 写文件
     * @date 2015年6月14日
     * @param path 文件路径
     * @param data 要写入的数据
     * @throws IOException
     */
    public static void writeToFile(String path, String data) throws IOException {
        File file=new File(path);
        if(!file.exists()) {
            file.createNewFile();
        }
        
        // 写入前先清空文件内容
        FileWriter fileWriter =new FileWriter(file);
        fileWriter.write("");
        fileWriter.flush();
        fileWriter.close();
        
        // 写入数据
        FileOutputStream out=new FileOutputStream(file,true);   
        out.write(data.getBytes("utf-8"));    
        out.flush();
        out.close();
    }
    
}
