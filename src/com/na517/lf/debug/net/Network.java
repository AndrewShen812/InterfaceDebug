package com.na517.lf.debug.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;

import javax.naming.Context;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.log.LogUtils;

/**
 * @Title: NetworkUtils.java
 * @Package com.na517.net
 * @Description: 网络连接
 * @author GeNie
 * @date 2014-3-31 下午4:15:23
 * @version V1.0
 */
public class Network {
    
    private static final String TAG = "NetworkUtils";
    
    private static final String CHARSET = HTTP.UTF_8;
    
    private static int CONNECTION_TIMEOUT = 40000;
    
    private static HttpClient httpClient = getHttpClient();
    
    private Network() {
        
    }
    
    private static HttpClient getHttpClient() {
        HttpParams params = new BasicHttpParams();
        // 设置一些基本参数
        
        // params.setParameter("Accept-Encoding", "gzip");
        
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, false);
        HttpProtocolParams.setUserAgent(params,
                                        "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) " + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
        // 超时设置
        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(params, CONNECTION_TIMEOUT);
        ConnManagerParams.setTimeout(params, CONNECTION_TIMEOUT);
        
        // 设置我们的HttpClient支持HTTP和HTTPS两种模式
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        
        // 使用线程安全的连接管理来创建HttpClient
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
        DefaultHttpClient httpClient = new DefaultHttpClient(conMgr, params);
        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        workAroundReverseDnsBugInHoneycombAndEarlier(httpClient);
        
        return httpClient;
    }
    
    private static void workAroundReverseDnsBugInHoneycombAndEarlier(HttpClient client) {
        // Android had a bug where HTTPS made reverse DNS lookups (fixed in Ice
        // Cream Sandwich)
        // https://code.google.com/p/android/issues/detail?id=13117
        SocketFactory socketFactory = new LayeredSocketFactory() {
            
            SSLSocketFactory mDelegate = SSLSocketFactory.getSocketFactory();
            
            @Override
            public Socket createSocket() throws IOException {
                return mDelegate.createSocket();
            }
            
            @Override
            public Socket connectSocket(Socket sock, String host, int port, InetAddress localAddress, int localPort, HttpParams params) throws IOException {
                return mDelegate.connectSocket(sock, host, port, localAddress, localPort, params);
            }
            
            @Override
            public boolean isSecure(Socket sock) throws IllegalArgumentException {
                return mDelegate.isSecure(sock);
            }
            
            @Override
            public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
                injectHostname(socket, host);
                return mDelegate.createSocket(socket, host, port, autoClose);
            }
            
            private void injectHostname(Socket socket, String host) {
                try {
                    Field field = InetAddress.class.getDeclaredField("hostName");
                    field.setAccessible(true);
                    field.set(socket.getInetAddress(), host);
                }
                catch (Exception ignored) {
                }
            }
        };
        client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
    }
    
    /**
     * 设置超时
     * 
     * @description
     * @date 2015-1-20
     * @param
     * @Exception
     */
    public static void setConnectionTimeout(int timeout) {
        if (CONNECTION_TIMEOUT != timeout) {
            CONNECTION_TIMEOUT = timeout;
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);// 连接超时
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);// 请求超时
        }
    }
    
    /**
     * 组装请求参数
     */
    private static JSONObject getJsonObjectParam(Request request) {
        JSONObject data = JSON.parseObject(request.data);
        JSONObject json = new JSONObject();
        // 非开放平台底层协议
        String sign = MD5.getMD5Code(request.data + ";UUID=" + request.uuid);
        json.put("m", request.machineCode);
        json.put("t", request.token);
        json.put("a", request.action);
        json.put("s", sign);
        json.put("v", request.version);
        json.put("at", 1); // 1：Android-phone 2:android平板 3:ipad 4:iphone
        json.put("d", data);
        json.put("u", request.user);
        
        System.out.println("jsonParam=" + json.toJSONString());
        
        return json;
    }
    
    /**
     * @description 发送post请求
     * @date 2015年6月15日
     * @param request
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws Exception
     */
    public static String post(Request request) throws ClientProtocolException, IOException, Exception {
        setConnectionTimeout(30 * 1000);
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(request.url + request.action);
            System.out.println("url=" + httpPost.getURI());
            // 发送请求获取数据
            String base64Param = null;
            String stringParam = null;
            if (request.isDataAll) {
                stringParam = request.data;
            }
            else {
                JSONObject jsonParam = getJsonObjectParam(request);
                stringParam = jsonParam.toString();
            }
            System.out.println("stringParam=" + stringParam);
            // Base64编码
            base64Param = new String(Base64Helper.encode(stringParam.getBytes()));
            StringEntity requestParam = new StringEntity(base64Param, CHARSET);
            httpPost.setEntity(requestParam);
            HttpResponse response = httpClient.execute(httpPost);
            // 解析返回内容
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = "";
                    String gzipString = EntityUtils.toString(entity, CHARSET);
                    System.out.println("gzipString=" + gzipString);
                    byte[] gzipBytes = GZipUtils.unGZip(gzipString.getBytes("ISO-8859-1"));
                    if (gzipBytes == null) {
                        return null;
                    }
                    String base64String = new String(gzipBytes, "ISO-8859-1");
                    // Base64解码
                    byte[] base64Bytes = Base64Helper.decode(base64String);
                    result = new String(base64Bytes, CHARSET);
                    System.out.println("Base64 decode result=" + result);
                    return result;
                }
            }
            else {
                if (httpPost != null) {
                    httpPost.abort();
                }
            }
        }
        catch (UnsupportedEncodingException e) {
            if (httpPost != null) {
                httpPost.abort();
            }
            throw e;
        }
        catch (ParseException e) {
            if (httpPost != null) {
                httpPost.abort();
            }
            throw e;
        }
        catch (IOException e) {
            if (httpPost != null) {
                httpPost.abort();
            }
            throw e;
        }
        
        return null;
    }
    
}
