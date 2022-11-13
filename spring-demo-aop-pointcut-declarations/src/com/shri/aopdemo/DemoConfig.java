package com.shri.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.shri.aopdemo")
@PropertySource("classpath:logging.properties")
public class DemoConfig {

}
