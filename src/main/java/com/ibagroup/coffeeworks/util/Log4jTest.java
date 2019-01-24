package com.ibagroup.coffeeworks.util;

import org.apache.log4j.Logger;

public class Log4jTest {

final static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
	
		Log4jTest obj = new Log4jTest();
		obj.runMe("mkyong");
		
	}
	
	private void runMe(String parameter){
		
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
		
	}
	
}
