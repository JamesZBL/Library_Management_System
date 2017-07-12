package com.zbl.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期格式化工具类
 * 
 * @author 
 */
public class DateFormatter {
	public static final String SIMPLE_LONG_DATE = "yyyy-M-d HH:mm:ss";
	public static final String SIMPLE_LONG_DATE_SHOW = "yyyy-MM-dd HH:mm:ss";
	public static final String CHINESE_SHORT_DATE ="yyyy-MM-dd";// "yyyy年M月d日";

	public static final String SIMPLE_SHORT_DATE = "yyyy-M-d";
	public static final String SIMPLE_SHORT_DATE_SHOW = "yyyy-MM-dd";

	//public static final String SIMPLE_LONG_DATE_DOJO = "EEE MMM dd yyyy HH:mm:ss Z";
	public static final String SIMPLE_LONG_DATE_DOJO = "yyyy-M-d HH:mm:ss.EEEZ";

	private static final String[] weekDays = { "", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 格式化今日中文短日期
	 * 
	 * @return
	 */
	public static final String formatChineseShortToday() {
		try {
			return formatDate(new Date(), CHINESE_SHORT_DATE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期，返回格式化后的字符串
	 * 
	 * @param date
	 * @param patter
	 * @return
	 */
	public static final String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 格式化日期，返回格式化后的字符串
	 * 
	 * @param date
	 * @param patter
	 * @return
	 */
	public static final String formatDate(Date date) {
		return formatDate(date,SIMPLE_SHORT_DATE_SHOW);
	}
	/**
	 * 格式化日期，返回格式化后的字符串
	 * 
	 * @param date
	 * @param patter
	 * @return
	 */
	public static final String formatDateTime(Date date) {
		return formatDate(date,SIMPLE_LONG_DATE_SHOW);
	}

	public static final Date formatDate(String sDate, String pattern) {
		if(Empty.isEmpty(sDate))return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
		try {
			return sdf.parse(sDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static final Date formatDate(String sDate) {
		if(Empty.isEmpty(sDate))return null;
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_SHORT_DATE, Locale.ENGLISH);
		try {
			return sdf.parse(sDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static final Date formatDateTime(String sDate) {
		if(Empty.isEmpty(sDate))return null;
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_LONG_DATE_SHOW, Locale.ENGLISH);
		try {
			return sdf.parse(sDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到中文星期
	 * 
	 * @return
	 */
	public static final String getChineseWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return weekDays[c.get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * 得到例如 200803 格式的日期
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentYearMonth() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		return df.format(new Date());
	}

	/**
	 * 得到当前年份例如 2008 格式的日期
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentYear() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(new Date());
	}

	/**
	 * 得到当前月份例如 04 格式的日期
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentMonth() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("MM");
		return df.format(new Date());
	}

	public static synchronized Calendar getNextWeek(Calendar gc) {
		/**
		 * 详细设计： 1.指定日期加7天
		 */
		gc.add(Calendar.DATE, 7);
		return gc;
	}

	public static synchronized Date getNextWeek(Date date) {
		/**
		 * 详细设计： 1.指定日期加7天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 7);
		return gc.getTime();
	}

	public static synchronized Date getLastWeek(Date date) {
		/**
		 * 详细设计： 1.指定日期加7天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -7);
		return gc.getTime();
	}

	public static synchronized Date getFirstDayOfWeek(Date date) {
		/**
		 * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天
		 * 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天 6.如果date是星期五，则减5天
		 * 7.如果date是星期六，则减6天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc.getTime();
	}

	public static synchronized Date getFirstDayOfNextWeek(Date date) {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(getNextWeek(gc.getTime()));
		gc.setTime(getFirstDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Date getFirstDayOfLastWeek(Date date) {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(getLastWeek(gc.getTime()));
		gc.setTime(getFirstDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Date getLastDayOfWeek(Date date) {
		/**
		 * 详细设计： 1.如果date是星期日，则加6天 2.如果date是星期一，则加5天 3.如果date是星期二，则加4天
		 * 4.如果date是星期三，则加3天 5.如果date是星期四，则加2天 6.如果date是星期五，则加1天
		 * 7.如果date是星期六，则加0天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 6);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, 5);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, 4);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 0);
			break;
		}
		return gc.getTime();
	}
	
	/**
	 * 获取某时间所在月的第几周的星期几的日期
	 * day：0：星期一,1：星期二,2：星期三,3：星期四,4：星期五,5：星期六,6：星期日
	 * week: 0:第一周,1:第二周,2:第三周,3:第四周,4:第五周
	 * */
	public static Date getWeekDayOfMonth(Date now,int day,int week){
		GregorianCalendar cal = new GregorianCalendar(); 
		cal.setTime(now); 
		cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY); 
		cal.add(GregorianCalendar.DAY_OF_MONTH, 7*week); 
		cal.add(GregorianCalendar.DAY_OF_MONTH, day);
		Date  date = cal.getTime();
		if(now.getMonth()> date.getMonth()){
			date = getWeekDayOfMonth(now,day,week+1);
		}else if(now.getMonth()<date.getMonth()){
			date = getWeekDayOfMonth(now,day,week-1);
		}
		
		String s = DateFormatter.formatDate(date, DateFormatter.SIMPLE_SHORT_DATE_SHOW)+" 23:59:59";
		
		date = DateFormatter.formatDate(s, DateFormatter.SIMPLE_LONG_DATE_SHOW);
		
		return date;
	}

	
	/**
	 * 比较两日期相差天数
	 * */
	
	public static long compareToNum(Date d1,Date d2){

		String s = formatDate(d1,DateFormatter.SIMPLE_SHORT_DATE_SHOW);
		String e = formatDate(d2,DateFormatter.SIMPLE_SHORT_DATE_SHOW);
		d1 = formatDate(s, DateFormatter.SIMPLE_SHORT_DATE_SHOW);
		d2 = formatDate(e, DateFormatter.SIMPLE_SHORT_DATE_SHOW);

		long num = 0;
		if(d1.getTime()>d2.getTime()){
			num =(long)((d1.getTime()- d2.getTime())/86400000);
		}else{
			num =(long)((d2.getTime()- d1.getTime())/86400000);
		}
		return num;
	}
	
	//年月相加
	public static String addMonth(String ny,int num) { 
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		Date date = formatDate(ny, "yyyy-MM");
		gc.setTime(date);
		gc.add(Calendar.MONTH,(num+1));
		String strDatr = "";
		if(gc.get(Calendar.MONTH) == 0) {
			gc.add(Calendar.YEAR,-1);
			strDatr = gc.get(Calendar.YEAR)+"-12";
		} else {
			strDatr = gc.get(Calendar.YEAR)+"-"+gc.get(Calendar.MONTH);
		}
		return strDatr;
	}
	//年月相加
	public static String jsMonth(String ny,int num) { 
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		Date date = formatDate(ny, "yyyy-MM");
		gc.setTime(date);
		gc.add(Calendar.MONTH,-num);
		String strDatr = "";
		if(gc.get(Calendar.MONTH) == 0) {
			gc.add(Calendar.YEAR,-1);
			strDatr = gc.get(Calendar.YEAR)+"-12";
		} else {
			strDatr = gc.get(Calendar.YEAR)+"-"+gc.get(Calendar.MONTH);
		}
		return strDatr;
	}
	
	public static void main(String[] arg) {
		Date date1 = formatDate("2012-08-31");
		Date date2 = formatDate("2013-09-02");
		Date date3 = formatDate("2014-09-30");
		
		System.out.println(date1.before(date3) && date2.after(date3));
		System.out.println();
		
		

	}
}
