package com.example.demo.commond.context;

import com.example.demo.commond.CommandException;
import com.example.demo.commond.context.response.DefaultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * @author Smile(wangyajun)
 * @create 2018-09-13 20:40
 **/
public class CommandContext extends CommandContextRequestSupportBuilder  {

    /** 自身唯一引用 */
    private static CommandContext context;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.logger.info("加载[Smile {}]...", CommandContext.class.getSimpleName());
        super.afterPropertiesSet();
    }


    /** 返回自身唯一引用(PS：在Spring容器初始化阶段调用此方法，可能抛出 applicationContext未初始化的异常) */
    public synchronized static CommandContext get() {
        if (CommandContext.context == null) {
            Assert.notNull(CommandContextConfigurator.applicationContext, "请优先把{"+CommandContext.class.getSimpleName()+"}托管给Spring!");
            CommandContext.context = CommandContextConfigurator.applicationContext.getBean(CommandContext.class);
        }
        return CommandContext.context;
    }


    /** 执行命令。PS：命令容器没有在任何地方开启事务，如有开启事务的需求，请自行开启事务 */
    public <REQ extends CommandRequest> DefaultResponse execute(REQ request) throws CommandException {
        return execute(request, null);
    }

    /** 执行命令。PS：命令容器没有在任何地方开启事务，如有开启事务的需求，请自行开启事务 */
    @SuppressWarnings("unchecked")
    public <REQ extends CommandRequest, RES extends CommandResponse> RES execute(REQ request, Class<RES> clazz) throws CommandException {
        Assert.notNull(request, "request is null.");

        RES response = (clazz != null ? BeanUtils.instantiate(clazz) : (RES) new DefaultResponse());
        getRequestSupport(request).handle(request, response);
        return response;
    }

}
