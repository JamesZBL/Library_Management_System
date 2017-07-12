package com.zbl.util;

import org.apache.commons.logging.LogFactory;

/**
 * 日志工具
 * @author 
 * @date 
 */
public class AppLog {
	private static String logName = "ssh";
	private static final AppLog appLog = new AppLog(logName);
	private org.apache.commons.logging.Log log;
	
	public AppLog(){
		log = LogFactory.getLog(logName);
	}
	
	public AppLog(Object o){
		log = LogFactory.getLog(o.getClass());
	}
	/**
	 * 获取系统默认日志工具
	 * @return
	 */
	public static final AppLog getAppLog(){
		return appLog;
	}
	
	/**输出信息*/
	public void info(Object o){
		if(log.isInfoEnabled()){
			log.info(o);
		}
	}
	
	/**输出信息*/
	public void info(Object o,Throwable throable){
		if(log.isInfoEnabled()){
			log.info(o,throable);
		}
	}
	
	/**输出调试信息*/
	public void debug(Object o){
		if(log.isDebugEnabled()){
			log.debug(o);
		}
	}
	
	/**输出调试信息*/
	public void debug(Object o,Throwable throwable){
		if(log.isDebugEnabled()){
			log.debug(o,throwable);
		}
	}
	
	public void error(Object o){
		if(log.isErrorEnabled()){
			log.error(o);
			if(o instanceof Throwable){
				((Throwable)o).printStackTrace();
			}
		}
	}
	
	public void error(Object o,Throwable throwable){
		if(log.isErrorEnabled()){
			log.error(o,throwable);
		}
	}
	
	public void fatal(Object o){
		if(log.isFatalEnabled()){
			log.fatal(o);
		}
	}
	

	public void fatal(Object o,Throwable throwable){
		if(log.isFatalEnabled()){
			log.fatal(o,throwable);
		}
	}
	
	public void warn(Object o){
		if(log.isWarnEnabled()){
			log.warn(o);
		}
	}
	public void warn(Object o,Throwable throwable){
		if(log.isWarnEnabled()){
			log.warn(o,throwable);
		}
	}
	
	public void trace(Object o){
		if(log.isTraceEnabled()){
			log.trace(o);
		}
	}
	
	public void trace(Object o,Throwable throwable){
		if(log.isTraceEnabled()){
			log.trace(o,throwable);
		}
	}
	
}
