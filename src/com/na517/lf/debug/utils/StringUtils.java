/** 
 * @项目名称：InterfaceDebug   
 * @文件名：StringUtils.java    
 * @版本信息：
 * @日期：2015年6月14日    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.na517.lf.debug.utils;

/**    
 *     
 * @项目名称：InterfaceDebug    
 * @类名称：StringUtils    
 * @类描述：字符串操作工具类    
 * @创建人：lianfeng    
 * @创建时间：2015年6月14日 下午2:32:57    
 * @修改人：lianfeng    
 * @修改时间：2015年6月14日 下午2:32:57    
 * @修改备注：    
 * @version     
 *     
 */
public class StringUtils {
    /**
     * @description 判断字符串是否为空
     * @date 2015年6月12日
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (null == string) {
            return true;
        }
        
        return string.isEmpty();
    }
    
    /**
     * 返回两个字符串中间的内容
     * 
     * @param all
     * @param start
     * @param end
     * @return
     */
    public static String getMiddleString(String all,String start,String end) {
        int beginIdx = all.indexOf(start) + start.length();
        int endIdx = all.indexOf(end);
        return all.substring(beginIdx,endIdx);
    }
}
