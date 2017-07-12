package com.zbl.util;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @ClassName:MyLoggingEvent.java
 * @Description:重写，替换message 单引号为 转义符
 * @author:Yuan
 * @version:V1.0
 * @Date:2016年6月7日 上午10:51:38
 */

public class MyLoggingEvent extends LoggingEvent
{
	private static final long serialVersionUID = -1405129465403337629L;

	public MyLoggingEvent(String fqnOfCategoryClass, Category logger, Priority level, Object message, Throwable throwable)
	{
		super(fqnOfCategoryClass, logger, level, message, throwable);
	}
	@Override
	public String getThreadName()
	{
		String thrdName = super.getThreadName();
		if (thrdName.indexOf("'") != -1)
		{
			//thrdName = thrdName.replaceAll("'", "''");
			thrdName= StringEscapeUtils.escapeSql(thrdName);
		}
		return thrdName;
	}
	@Override
	public String getRenderedMessage()
	{
		String msg = super.getRenderedMessage();
		if (msg.indexOf("'") != -1)
		{
			//msg = msg.replaceAll("'", "''");
			msg= StringEscapeUtils.escapeSql(msg);
		}
		return msg;
	}

}
