package com.example.demo.commond.context.receiver;

import com.example.demo.commond.context.CommandReceiver;
import com.example.demo.commond.context.CommandResponse;
import com.example.demo.commond.context.CommandSupportType;
import com.example.demo.commond.context.request.AbstractCommandRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Smile(wangyajun)
 * @create 2018-09-14 10:56
 **/
@Component
public abstract class AbstractCommandReceiver implements CommandReceiver<AbstractCommandRequest>{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommandReceiver.class);

    @Override
    public void doAfterCompletion(AbstractCommandRequest request, CommandResponse response, Exception exception) {
        LOGGER.info("abstract command receiver doAfterCompletion ");
    }

    @Override
    public void doBeforeCompletion(AbstractCommandRequest request, CommandResponse response) {
        LOGGER.info("abstract command receiver doBeforeCompletion ");
    }

    @Override
    public boolean doAfterOpenSession(AbstractCommandRequest request, CommandResponse response) {
        LOGGER.info("abstract command receiver doAfterOpenSession ");
        return false;
    }

    @Override
    public boolean doBeforeOpenSession(AbstractCommandRequest request, CommandResponse response) {
        LOGGER.info("abstract command receiver doBeforeOpenSession ");
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Class<AbstractCommandRequest> getRequestType() {
        return null;
    }

    @Override
    public boolean isSupportTypes(CommandSupportType supportType) {
        return false;
    }
}
