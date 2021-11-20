package com.shri.springdemo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyLoggerConfig {
	
	@Value("${rootLoggerLevel}")
	private String rootLoggerLevel;
	
	@Value("${printLoggerLevel}")
	private String printLoggerLevel;
	
	@PostConstruct
	public void initLogger() {
		
		Level rootLevel = Level.parse(rootLoggerLevel);
		Level printLevel = Level.parse(printLoggerLevel);
		
		Logger applicationContextLogger = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());
		
		Logger loggerParent = applicationContextLogger.getParent();
		
		loggerParent.setLevel(rootLevel);
		
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(printLevel);
		consoleHandler.setFormatter(new SimpleFormatter());
		
		loggerParent.addHandler(consoleHandler);
		
	}
	
	public String getRootLevel() {
		return rootLoggerLevel;
	}
	public void setRootLevel(String rootLoggerLevel) {
		this.rootLoggerLevel = rootLoggerLevel;
	}
	public String getPrintLevel() {
		return printLoggerLevel;
	}
	public void setPrintLevel(String printLoggerLevel) {
		this.printLoggerLevel = printLoggerLevel;
	}

}
