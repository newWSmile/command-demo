package com.example.demo.commond.context.receiver;

import com.example.demo.commond.context.CommandReceiver;
import com.example.demo.commond.context.CommandSupportType;
import com.example.demo.commond.context.request.AbstractCommandRequest;
import org.springframework.stereotype.Component;

/**
 * @author Smile(wangyajun)
 * @create 2018-09-14 10:56
 **/
@Component
public class AbstractCommandReceiver implements CommandReceiver<AbstractCommandRequest> {

    @Override
    public Class<AbstractCommandRequest> getRequestType() {
        return null;
    }

    @Override
    public boolean isSupportTypes(CommandSupportType supportType) {
        return true ;
    }
}
