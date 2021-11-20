package com.shri.automation.LoggingDemo;

import org.apache.log4j.Logger;

public class LoggingExample2 {
	
	public static void printLogs2(Logger log) {
		log.debug("Message from Debug");
		log.info("Message from Info");
		log.warn("Message from Warn");
		log.error("Message from Error");
		log.fatal("Message from Fatal");
	}

	public static void main(String[] args) {
		Logger log = Logger.getLogger(LoggingExample2.class);
		
		printLogs2(log);

	}

}
