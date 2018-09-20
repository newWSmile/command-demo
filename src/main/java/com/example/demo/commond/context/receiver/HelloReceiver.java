/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HelloReceiver
 * Author:   Administrator
 * Date:     2018\9\20 0020 22:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.demo.commond.context.receiver;

import com.example.demo.commond.context.CommandResponse;
import com.example.demo.commond.context.CommandSupportType;
import com.example.demo.commond.context.request.AbstractCommandRequest;
import com.example.demo.commond.context.request.HelloRequest;
import com.example.demo.commond.context.support.AbstractCommandRequestSupport;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018\9\20 0020
 * @since 1.0.0
 */
@Component
public class HelloReceiver extends AbstractCommandReceiver implements AbstractCommandRequestSupport<HelloRequest,AbstractCommandReceiver<HelloRequest>> {
    @Override
    public void doAfterCompletion(AbstractCommandRequest request, CommandResponse response, Exception exception) {
        super.doAfterCompletion(request, response, exception);
    }

    @Override
    public void doBeforeCompletion(AbstractCommandRequest request, CommandResponse response) {
        super.doBeforeCompletion(request, response);
    }

    @Override
    public boolean doAfterOpenSession(AbstractCommandRequest request, CommandResponse response) {
        return super.doAfterOpenSession(request, response);
    }

    @Override
    public boolean doBeforeOpenSession(AbstractCommandRequest request, CommandResponse response) {
        return super.doBeforeOpenSession(request, response);
    }

    @Override
    protected void doHandle(HelloRequest request, CommandResponse response) {

    }

    @Override
    public CommandSupportType supportType() {
        return null;
    }
}