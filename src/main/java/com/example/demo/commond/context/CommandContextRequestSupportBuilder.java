package com.example.demo.commond.context;

import com.example.demo.commond.CommandException;
import com.example.demo.commond.context.selector.DefaultRequestSupportSelector;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * @author Smile(wangyajun)
 * @create 2018-09-13 20:41
 **/
public class CommandContextRequestSupportBuilder extends CommandContextConfigurator {
    /** 操作支持类映射 */
    private Map<CommandSupportType, Map<Class<? extends CommandRequest>, CommandRequestSupport>> supporType2RequestSupportMapping = new HashMap<>();

    /** 命令支撑类映射 */
    private final MultiValueMap<CommandSupportType, Class<? extends CommandRequestSupport>> requestSupportMaps = new LinkedMultiValueMap<>();

    /** 命令支撑类选择器映射 */
    private List<CommandRequestSupportSelector> requestSupportSelectors = new ArrayList<>();

    /** 命令支撑类选择器映射 */
    private Map<Class<? extends CommandRequest>, CommandRequestSupport> requestSupportSelecterMaps = new HashMap<>();



    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        /////// RequestSupport
        Collection<CommandRequestSupport> values = applicationContext.getBeansOfType(CommandRequestSupport.class).values();
        for (CommandRequestSupport requestSupport : values) {
            requestSupportMaps.add(requestSupport.supportType(), (Class<CommandRequestSupport>) AopUtils.getTargetClass(requestSupport));
        }

        /////// RequestSupportSelecter
        requestSupportSelectors.addAll(applicationContext.getBeansOfType(CommandRequestSupportSelector.class).values());
        requestSupportSelectors.add(new DefaultRequestSupportSelector() {
            @Override
            public int getOrder() {
                return Ordered.LOWEST_PRECEDENCE;
            }
        });
        OrderComparator.sort(requestSupportSelectors);

        logger.info("开始扫描命令容器接收器: ......");

        //加载容器中存在的Receiver
        logger.info("   开始加载命令接收处理器......");
        Map<CommandSupportType, MultiValueMap<Class<? extends CommandRequest>, CommandReceiver<? extends CommandRequest>>> mapping = loadType2ReceiverMultiValueMap();
        if (MapUtils.isEmpty(mapping)) {
            logger.warn("   加载命令接收处理器失败：不存在任何的命令接收处理器...");
            return;
        }

        //生成Receiver与Request之间的映射关系
        for (Map.Entry<CommandSupportType, MultiValueMap<Class<? extends CommandRequest>, CommandReceiver<? extends CommandRequest>>> entry : mapping.entrySet()) {
            CommandSupportType commandSupportType = entry.getKey();
            if (!supporType2RequestSupportMapping.containsKey(commandSupportType)) {
                supporType2RequestSupportMapping.put(commandSupportType, new HashMap<Class<? extends CommandRequest>, CommandRequestSupport>());
            }

            MultiValueMap<Class<? extends CommandRequest>, CommandReceiver<? extends CommandRequest>> value = entry.getValue();
            for (Map.Entry<Class<? extends CommandRequest>, List<CommandReceiver<? extends CommandRequest>>> entry2 : value.entrySet()) {
                Class<? extends CommandRequest> requestType = entry2.getKey();
                List<CommandReceiver<? extends CommandRequest>> receivers = entry2.getValue();

                //如果已经存在对应请求的处理类，则抛出异常，理论上每个请求，在对应贷款单账户中有且只有一个处理类
                if (supporType2RequestSupportMapping.get(commandSupportType).containsKey(requestType)) {
                    throw new CommandException("对同一命令请求器:{} 以及同一命令支撑器:{}, 存在重复的命令接收处理器:{}", new Object[] { requestType, commandSupportType, receivers.get(0) });
                }

                List<Class<? extends CommandRequestSupport>> requestSupportClassList = requestSupportMaps.get(commandSupportType);

                boolean flg = false;
                for (Class<? extends CommandRequestSupport> requestSupportClass : requestSupportClassList) {
                    for (CommandReceiver<? extends CommandRequest> commandReceiver : receivers) {   // 此循环是有顺序的
                        CommandRequestSupport support = (CommandRequestSupport) applicationContext.getBean(requestSupportClass);
                        if (support.isSupportRequest(requestType)) {
                            if (flg) {
                                throw new CommandException("对同一命令请求器:{} 以及同一命令支撑器:{}, 存在重复的命令接收处理器:{}", new Object[] { requestType, commandSupportType, receivers.get(0) });
                            }
                            logger.debug("        实例化RequestSupport支撑器： requestClass：{} ; requestSupportClass：{} ; receiver：{}", requestType.getName(), requestSupportClass.getName(), AopUtils.getTargetClass(commandReceiver).getName());
                            CommandRequestSupport support2 = (CommandRequestSupport) applicationContext.getBean(requestSupportClass, commandReceiver);
                            supporType2RequestSupportMapping.get(commandSupportType).put(requestType, support2);
                            flg = true;
                            break;
                        }
                    }
                }
                if (!flg) {
                    throw new CommandException("对命令请求器:{} 命令支撑器:{}", new Object[] { requestType, commandSupportType });
                }
            }

        }
        logger.info("命令容器接收器扫描完成: ......");
    }

    /**
     * 根据请求器获取对应的请求支撑器
     *
     * @param request
     *
     * @return RequestSupport<Request> 请求器对应的请求支撑器
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2018年3月15日]
     * @author rain
     */
    public CommandRequestSupport getRequestSupport(CommandRequest request) {
        Assert.notNull(request, "request is null.");
        Class<? extends CommandRequest> requestClass = request.getClass();
        CommandRequestSupport commandRequestSupport = this.requestSupportSelecterMaps.get(requestClass);
        if (commandRequestSupport != null) {   // 从缓存获取支撑器
            return commandRequestSupport;
        }

        for (CommandRequestSupportSelector selector : requestSupportSelectors) {
            CommandSupportType commandSupportType = selector.select(request);
            Assert.notNull(commandSupportType, String.format("Select's CommandSupportType is null。 selector class: {} ; request class：{}", selector.getClass(), requestClass));
            CommandRequestSupport requestSupport = this.supporType2RequestSupportMapping.get(commandSupportType).get(requestClass);
            this.requestSupportSelecterMaps.put(requestClass, requestSupport);
            return requestSupport;
        }

        return null;
    }

    /**
     * 加载类型与Receiver的映射<br>
     * 此方法返回的 Receiver 集合已经经过排序
     *
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<CommandSupportType, MultiValueMap<Class<? extends CommandRequest>, CommandReceiver<? extends CommandRequest>>> loadType2ReceiverMultiValueMap() {
        Map<CommandSupportType, MultiValueMap<Class<? extends CommandRequest>, CommandReceiver<? extends CommandRequest>>> mapping = new HashMap<>();

        //加载贷款单分类
        Collection<CommandReceiver> receivers = applicationContext.getBeansOfType(CommandReceiver.class).values();
        if (CollectionUtils.isNotEmpty(receivers)) {
            //按贷款单类型分类
            for (CommandSupportType supportType : requestSupportMaps.keySet()) {
                MultiValueMap<Class<? extends CommandRequest>, CommandReceiver<? extends CommandRequest>> multiValueMap = new LinkedMultiValueMap<>();
                mapping.put(supportType, multiValueMap);

                for (CommandReceiver<? extends CommandRequest> receiver : receivers) {
                    if (receiver.isSupportTypes(supportType)) {  // 判断是否支持此贷款账户的处理
                        logger.debug("      加载命令接收处理器：({}) {}", supportType, AopUtils.getTargetClass(receiver).getName());
                        multiValueMap.add(receiver.getRequestType(), receiver);
                    }
                }
            }
        }

        // 排序
        for (MultiValueMap<Class<? extends CommandRequest>, CommandReceiver<? extends CommandRequest>> map : mapping.values()) {
            for (List<CommandReceiver<? extends CommandRequest>> list : map.values()) {
                OrderComparator.sort(list);
            }
        }

        return mapping;
    }

}
