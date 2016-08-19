package cn.com.meng.base.log;

import org.apache.log4j.Logger;

public class Log4jTester {

	private static Logger logger = Logger.getLogger(Log4jTester.class);
	
	public static void main(String[] args){
		logger.debug("sds");
	}
}
