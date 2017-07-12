package com.zbl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 关于time的工具类
 * @author 张翰林
 *
 */
public class AboutTimeUtil {
	
	//私有化构造方法
	private AboutTimeUtil() {
	}
	
	/**
	 * 返回格式真确的当前时间
	 * @return String 
	 */
	public static String getTimeStamp(){
		//设置日期格式,并获取当前时间
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date()).toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(AboutTimeUtil.getTimeStamp());
	}
}
