/** 
 * @项目名称：MySQLTest   
 * @文件名：Request.java    
 * @版本信息：
 * @日期：2015年6月12日    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.na517.lf.debug.net;

import java.beans.Transient;
import java.io.Serializable;

/**    
 *     
 * @项目名称：MySQLTest    
 * @类名称：Request    
 * @类描述：    
 * @创建人：lianfeng    
 * @创建时间：2015年6月12日 下午3:55:36    
 * @修改人：lianfeng    
 * @修改时间：2015年6月12日 下午3:55:36    
 * @修改备注：    
 * @version     
 *     
 */
public class Request implements Serializable{
    
    /**    
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
     *    
     * @since Ver 1.1    
     */    
    private static final long serialVersionUID = 1L;

    public String url;
    
    public String action;
    
    public String token;
    
    public String uuid;
    
    public String machineCode;
    
    public String version;
    
    public String data;
    
    public String user;
    
    public boolean isDataAll;
    
    public static final String UUID = "a24ec092-6a4a-400f-a977-32d6b94549ed";
    
}
