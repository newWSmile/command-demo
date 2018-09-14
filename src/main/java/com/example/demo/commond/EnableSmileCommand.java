package com.example.demo.commond;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
@Import(CommandConfiguration.class)
@Configuration
public @interface EnableSmileCommand {

}
