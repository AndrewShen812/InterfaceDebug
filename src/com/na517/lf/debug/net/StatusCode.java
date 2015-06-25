/** 
 * @项目名称：InterfaceDebug   
 * @文件名：StatusCode.java    
 * @版本信息：
 * @日期：2015年6月25日    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.na517.lf.debug.net;

import java.util.ArrayList;

public final class StatusCode {
    
    // ///////////////////////////////客户端Code///////////////////////////////
    /** 失败数据 */
    public static final int GET_DATA_ERROR = 0000;
    
    /** 客户端没有网络 */
    public static final int NETWORK_NOT_CONNECTED = 1001;
    
    /** 连接超时 */
    public static final int NETWORK_TIME_OUT = 1002;
    
    /** 服务器端响应失败 */
    public static final int NETWORK_RESPONSE_ERROR = 1003;
    
    /** 数据解析错误 */
    public static final int DATA_PARSE_ERROE = 1004;
    
    /** 参数错误 */
    public static final int PARAM_ERROR = 1005;
    
    /** 取消请求 */
    public static final int NETWORK_CANCEL = 1006;
    
    /** 未知错误 */
    public static final int UNKNOWN_ERROR = 1007;
    
    // //////////////////////服务器端Code///////////////////////////////////
    /** 服务器响应失败 */
    public static final int SERVER_ERROR = 9999;
    
    /** 请求格式错误 */
    public static final int REQUEST_FORMAT_ERROR = 1;
    
    /** 请求参数错误 */
    public static final int REQUEST_PARAME_ERROR = 2;
    
    /** 未获取到用户信息 */
    public static final int GET_NO_USERINFO = 3;
    
    /** 用户价格小于平台支付价格 */
    public static final int USERPRICE_LESSTHAN_PLATFORMPRICE = 4;
    
    /** 更新编码状态出错 */
    public static final int UPDATE_CODING_ERROR = 5;
    
    /** 编码状态出错,位置被NO或编码被取消 */
    public static final int CODING_STATUS_ERROR = 6;
    
    /** Token无效 */
    public static final int TOKEN_INVALIDATION = 7;
    
    /** 签名验证不通过 */
    public static final int SIGN_VERTIFICATION_ERROR = 8;
    
    /** UUID不能为空 */
    public static final int UUID_IS_NULL = 9;
    
    /** Token为空 */
    public static final int TOKEN_IS_NULL = 10;
    
    /** action为空 */
    public static final int ACTION_IS_NULL = 11;
    
    /** MachineCode（设备码）为空 */
    public static final int MACHINECODE_IS_NULL = 12;
    
    /** data为空 */
    public static final int DATA_IS_NULL = 13;
    
    /** DepData起飞日期为空 */
    public static final int DEPDATA_IS_NULL = 14;
    
    /** OrgCity出发城市为空 */
    public static final int ORGCITY_IS_NULL = 15;
    
    /** DstCity到达城市为空 */
    public static final int DSTCITY_IS_NULL = 16;
    
    /** FlightWay行程类型 单程 往返为空 */
    public static final int FLIGHTWAY_IS_NULL = 17;
    
    /** SortType排序方式为空 */
    public static final int SORTTYPE_IS_NULL = 18;
    
    /** QueryType查询方式为空 */
    public static final int QUERYTYPE_IS_NULL = 19;
    
    /** PageSize条数为空 */
    public static final int PAGESIZE_IS_NULL = 20;
    
    /** PageIndex页码为空 */
    public static final int PAGEINDEX_IS_NULL = 21;
    
    /** TotalPrice价格为空 */
    public static final int TOTALPRICE_IS_NULL = 22;
    
    /** Tel电话为空 */
    public static final int TEL_IS_NULL = 23;
    
    /** Name姓名为空 */
    public static final int NAME_IS_NULL = 24;
    
    /** Cantact联系人信息为空 */
    public static final int CONTACT_IS_NULL = 25;
    
    /** Delivery航程信息为空 */
    public static final int DELIVERY_IS_NULL = 26;
    
    /** DeliveryType配送类型为空 */
    public static final int DELIVERYTYPE_IS_NULL = 27;
    
    /** ExpressNo快递单号为空 */
    public static final int EXPRESSNO_IS_NULL = 28;
    
    /** Mobile电话为空 */
    public static final int MOBIILE_IS_NULL = 29;
    
    /** Sequence为空 */
    public static final int SEQUENCE_IS_NULL = 30;
    
    /** FlightNo航班号为空 */
    public static final int FLIGHTNO_IS_NULL = 31;
    
    /** OrgJetquay始发航站楼为空 */
    public static final int ORGJETQUAY_IS_NULL = 32;
    
    /** DstJetquay到达航站楼为空 */
    public static final int DSTJETQUAY_IS_NULL = 33;
    
    /** SeatClass仓位为空 */
    public static final int SEATCLASS_IS_NULL = 34;
    
    /** TakeffTime起飞时间为空 */
    public static final int TAKEOFFTIME_IS_NULL = 35;
    
    /** SeatMsg仓位说明为空 */
    public static final int SEATMSG_IS_NULL = 36;
    
    /** Discount折扣为空 */
    public static final int DISCOUNT_IS_NULL = 37;
    
    /** ArrTime到达时间为空 */
    public static final int ARRTIME_IS_NULL = 38;
    
    /** RatePoint 返点 为空 */
    public static final int RATE_POINT = 39;
    
    /** PolicyId政策ID为空 */
    public static final int POLICYID_IS_NULL = 40;
    
    /** AduTicketPrice成人票价为空 */
    public static final int ADUTICKETPRICE_IS_NULL = 41;
    
    /** AduAirBuid成人基建为空 */
    public static final int ADUAIRBUID_IS_NULL = 42;
    
    /** AduOil成人燃油为空 */
    public static final int ADUOIL_IS_NULL = 43;
    
    /** Gender性别为空 */
    public static final int GENDER_IS_NULL = 44;
    
    /** Ptype乘客类型为空 */
    public static final int PTYPE_IS_NULL = 45;
    
    /** IdType证件类型为空 */
    public static final int IDTYPE_IS_NULL = 46;
    
    /** IdNo证件号码为空 */
    public static final int IDNO_IS_NULL = 47;
    
    /** VoyageInfo航程信息为空 */
    public static final int VOYAGEINFO_IS_NULL = 48;
    
    /** PassengerList乘客信息为空 */
    public static final int PASSENGERLIST_IS_NULL = 49;
    
    /** PnrInfos订单信息为空 */
    public static final int PNRINFOS_IS_NULL = 50;
    
    /** OrderNo客户端订单为空 */
    public static final int ORDERNO_IS_NULL = 51;
    
    /** Paytype支付类型为空 */
    public static final int PAYTYPE_IS_NULL = 52;
    
    /** AirLine航司为空 */
    public static final int AIRLINE_IS_NULL = 53;
    
    /** 密码错误 */
    public static final int PWD_ERROR = 54;
    
    /** 禁止登录 */
    public static final int FORBIDDEN_LOGIN = 55;
    
    /** 政策为空 */
    public static final int POLICY_IS_NULL = 56;
    
    /** 没有渠道订单详情 */
    public static final int NO_ORDER_DETAIL = 57;
    
    /** 订单编码失败 */
    public static final int ORDER_CODEING_FAILED = 58;
    
    /** PlaneType 机型为空 */
    public static final int PLANE_TYPE = 59;
    
    /** 不加失败 */
    public static final int PREMIUM_FAILED = 60;
    
    /** 票面价为0，不能生成订单 */
    public static final int SHOWPRICE_IS_NULL = 61;
    
    /** 新增卖出订单信息失败 */
    public static final int ADD_SALE_ORDER_FAIL = 62;
    
    /** 选择日期超出规定 **/
    public static final int DATE_OVER_RULE = 63;
    
    /** Token过期 */
    public static final int EXPIRED_TOKEN = 65;
    
    
    public static ArrayList<String> getErrNote() {
        ArrayList<String> errNotes = new ArrayList<String>();
        
        StatusCode code = new StatusCode();
        
        Class<?> cls = code.getClass();
        
        
        return errNotes;
    }
}
