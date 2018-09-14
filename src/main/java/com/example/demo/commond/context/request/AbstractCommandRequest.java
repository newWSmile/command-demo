/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context.request;

import com.example.demo.commond.CommandException;
import com.example.demo.commond.context.CommandRequest;
import com.example.demo.commond.context.CommandSupportType;
import com.example.demo.commond.context.support.SourceEnumType;
import com.example.demo.utils.UUIDUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Request默认实现
 * 
 * @author rain
 * @version [版本号, 2018年3月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractCommandRequest implements CommandRequest {
    /** 获取操作流水 */
    private String requestId;
    
    /** 获取操作创建时间 */
    private Date requestCreateTime;
    
    /** 请求发起人 */
    private String requestOperator;
    
    /** 父操作流水 */
    private String requestParentId;
    
    /** 分组ID */
    private String requestGroupId;
    
    /** 获取备注 */
    private String requestRemark;
    
    /** 获取操作请求来源 */
    private String requestSource;
    
    /** 命令支撑类型 */
    private CommandSupportType requestSupportType;
    
    public AbstractCommandRequest() {
        this.requestId = UUIDUtils.generateUUIDjdk();
        this.requestCreateTime = new Date();
//        Object user = WebHolder.loginWithoutException();
//        if (user != null) {
//            if (user instanceof UserDetails) {
//                this.requestOperator = ((UserDetails) user).getUsername();
//            } else if (user instanceof CharSequence) {
//                this.requestOperator = ((CharSequence) user).toString();
//            }
//        }
    }
    
    public AbstractCommandRequest(CommandSupportType requestSupportType, String requestSource) throws CommandException {
        super();
        Assert.notNull(requestSupportType, "requestSupportType is null!");
//        Assert.notBlank(requestSource, "requestSource is empty!");
        if (StringUtils.isEmpty(requestSource)){
            throw new CommandException("requestSource is empty!");
        }
        this.requestId = UUIDUtils.generateUUIDjdk();
        this.requestSupportType = requestSupportType;
        this.requestSource = requestSource;
        this.requestCreateTime = new Date();
//        Object user = WebHolder.loginWithoutException();
//        if (user != null) {
//            if (user instanceof UserDetails) {
//                this.requestOperator = ((UserDetails) user).getUsername();
//            } else if (user instanceof CharSequence) {
//                this.requestOperator = ((CharSequence) user).toString();
//            }
//        }
    }
    
    /** 获取操作创建时间 */
    @Override
    public Date getRequestCreateTime() {
        return requestCreateTime;
    }
    
    /** 分组ID */
    public String getRequestGroupId() {
        return requestGroupId;
    }
    
    /** 获取操作流水 */
    @Override
    public String getRequestId() {
        return requestId;
    }
    
    /** 请求发起人 */
    @Override
    public String getRequestOperator() {
        return requestOperator;
    }
    
    /** 父操作流水 */
    @Override
    public String getRequestParentId() {
        return requestParentId;
    }
    
    /** 获取备注 */
    @Override
    public String getRequestRemark() {
        return requestRemark;
    }
    
    /** 获取操作请求来源 */
    @Override
    public String getRequestSource() {
        return requestSource;
    }
    
    @Override
    public CommandSupportType getRequestSupportType() {
        return SourceEnumType.城市服务商;
    }
    
    /** 获取操作创建时间 */
    public void setRequestCreateTime(Date requestCreateTime) {
        this.requestCreateTime = requestCreateTime;
    }
    
    /** 分组ID */
    public void setRequestGroupId(String requestGroupId) {
        this.requestGroupId = requestGroupId;
    }
    
    /** 获取操作流水 */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    /** 请求发起人 */
    public void setRequestOperator(String requestOperator) {
        this.requestOperator = requestOperator;
    }
    
    /** 父操作流水 */
    public void setRequestParentId(String requestParentId) {
        this.requestParentId = requestParentId;
    }
    
    /** 获取备注 */
    public void setRequestRemark(String requestRemark) {
        this.requestRemark = requestRemark;
    }
    
    /** 获取操作请求来源 */
    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }
    
    /** 命令支撑类型 */
    public void setRequestSupportType(CommandSupportType requestSupportType) {
        this.requestSupportType = requestSupportType;
    }
    
}
