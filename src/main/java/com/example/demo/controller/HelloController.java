/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HelloController
 * Author:   Administrator
 * Date:     2018\9\20 0020 22:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.demo.controller;

import com.example.demo.commond.context.CommandContext;
import com.example.demo.commond.context.request.HelloRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018\9\20 0020
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hello")
public class HelloController {


    @RequestMapping("/getName")
    @ResponseBody
    public String getName(){
        HelloRequest request = new HelloRequest("xiaoming");
        CommandContext.get().execute(request);
        return "my name is :"+request.getName();
    }
}