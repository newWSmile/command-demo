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

import com.example.demo.commond.context.CommandRequest;
import com.example.demo.commond.context.CommandRequestSupport;
import com.example.demo.commond.context.CommandResponse;
import com.example.demo.commond.context.CommandSupportType;
import com.example.demo.commond.context.request.HelloRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HelloReceiver extends AbstractCommandReceiver<HelloRequest> implements CommandRequestSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloReceiver.class);

    @Override
    public Class<HelloRequest> getRequestType() {
        return null;
    }

    @Override
    public boolean isSupportTypes(CommandSupportType supportType) {
        return false;
    }

    @Override
    public CommandSupportType supportType() {
        return null;
    }

    @Override
    public void handle(CommandRequest request, CommandResponse response) {
        LOGGER.info("Helloreceiver is handing !!!");
    }
}