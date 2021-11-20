package com.shri.automation.LoggingDemo;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;

/**
 * Hello world!
 *
 */
public class LoggingExample1 
{
	public static void printLogs1(Logger log) {
		log.debug("Message from Debug");
		log.info("Message from Info");
		log.warn("Message from Warn");
		log.error("Message from Error");
		log.fatal("Message from Fatal");
	}
	public static void main( String[] args ) throws IOException
	{
		Logger log = Logger.getLogger(LoggingExample1.class);

		Layout layout = new PatternLayout("%d{E, yyyy-MM-dd HH:mm:ss} %-5p %t %3C{1} %M %L %m %n");
		Appender appender1 = new ConsoleAppender(layout);
		//      Appender appender2 = new FileAppender(layout, "log4j.txt");
		RollingFileAppender rollingFileAppender = new RollingFileAppender(layout, "log4j.txt");
		rollingFileAppender.setMaxBackupIndex(2);
		rollingFileAppender.setMaxFileSize("2KB");

		log.addAppender(appender1);
		log.addAppender(rollingFileAppender);

		printLogs1(log);

	}
}
