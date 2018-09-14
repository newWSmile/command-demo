/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context;

import java.util.Date;

/**
 * 命令请求器
 * 
 * @author smile
 * @version [版本号, 2018年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CommandRequest {

    /** 获取操作流水 */
    public String getRequestId();

    /** 获取操作创建时间 */
    public Date getRequestCreateTime();

    /** 请求发起人 */
    public String getRequestOperator();

    /** 父操作流水 */
    public String getRequestParentId();

    /** 获取备注 */
    public String getRequestRemark();

    /** 获取操作请求来源 */
    public String getRequestSource();

    /** 获取操作命令支撑类型 */
    public CommandSupportType getRequestSupportType();

}
