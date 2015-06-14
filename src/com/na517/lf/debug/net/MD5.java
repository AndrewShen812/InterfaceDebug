package com.na517.lf.debug.net;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @项目名称：517NaSell
 * @类名称：MD5
 * @类描述：MD5加密
 * @创建人：huaiying
 * @创建时间：2015年4月9日 下午5:03:18
 * @修改人：huaiying
 * @修改时间：2015年4月9日 下午5:03:18
 * @修改备注：
 * @version
 */
public class MD5 {
    
    // 全局数组
    private static final  String[] STRDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return STRDIGITS[iD1] + STRDIGITS[iD2];
    }
    
    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
    
    /** 生成32位 */
    public static String getMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    
}
