package com.na517.lf.debug.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @项目名称：517NaSell
 * @类名称：GZipUtils
 * @类描述： GZip编码
 * @创建人：huaiying
 * @创建时间：2015年4月9日 下午2:43:59
 * @修改人：huaiying
 * @修改时间：2015年4月9日 下午2:43:59
 * @修改备注：
 * @version
 */
public class GZipUtils {
    
    /**
     * gZip压缩方法
     */
    public static byte[] gZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    /**
     * gZip解压方法
     * 
     * @throws IOException
     */
    public static byte[] unGZip(byte[] data) throws IOException {
        byte[] b = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gzip = new GZIPInputStream(bis);
        byte[] buf = new byte[1024];
        int num = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((num = gzip.read(buf, 0, buf.length)) != -1) {
            baos.write(buf, 0, num);
        }
        b = baos.toByteArray();
        baos.flush();
        baos.close();
        gzip.close();
        bis.close();
        return b;
    }
}
