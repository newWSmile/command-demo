/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HelloRequest
 * Author:   Administrator
 * Date:     2018\9\20 0020 22:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.demo.commond.context.request;

import com.example.demo.commond.CommandException;
import com.example.demo.commond.context.CommandRequest;
import com.example.demo.commond.context.CommandSupportType;
import com.example.demo.commond.context.support.SourceEnumType;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018\9\20 0020
 * @since 1.0.0
 */
public class HelloRequest  extends AbstractCommandRequest implements CommandRequest {
    private String name ;

    public HelloRequest(String name) {
        this.name = name;
    }

    public HelloRequest(CommandSupportType requestSupportType, String requestSource, String name) throws CommandException {
        super(requestSupportType, requestSource);
        this.name = name;
    }

    @Override
    public CommandSupportType getRequestSupportType() {
        return SourceEnumType.城市服务商;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}