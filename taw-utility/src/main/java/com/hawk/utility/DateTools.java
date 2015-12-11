package com.hawk.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTools {

	public final static String DATE_PATTERN = "yyyy-MM-dd";
	public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String DATETIME_SSS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	public static Date now() {
		return new Date();
	}

	public static String now(String pattern) {
		if (pattern == null)
			return convert(now());
		else
			return convert(now(), pattern);
	}

	public static int dayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();

		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK);
		return i;
	}

	public static String dayNameOfWeek(Date date) {
		int dayOfWeek = dayOfWeek(date);
		switch (dayOfWeek) {
		case 1:
			return "星期天";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		default:
			return "星期几";
		}
	}

	public static Integer dayOfWeek(String dateStr) {
		Date d = convert(dateStr, DATE_PATTERN);
		Integer dayOfWeek = dayOfWeek(d);
		return dayOfWeek;
	}
	
	public static String dayNameOfWeek(String dateStr) {
		Date d = convert(dateStr, DATE_PATTERN);
		String name = dayNameOfWeek(d);
		return name;
	}

	public static Date convert(String dateStr) {
		return convert(dateStr, DATETIME_PATTERN);
	}

	public static String convert(Date date) {
		return convert(date, DATETIME_PATTERN);
	}

	public static Date convert(String dateStr, String pattern) {
		try {
			DateFormat df = new SimpleDateFormat(pattern);
			Date date = df.parse(dateStr);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String convert(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * 返回指定时间加上指定天数
	 * @param date
	 * @param diff
	 * @return
	 */
	public static Date addDays(Date date, int diff) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, diff);
		return c.getTime();
	}
	
	/**
	 * 返回指定时间加上指定分钟数
	 * @param date
	 * @param diff
	 * @return
	 */
	public static Date addMinutes(Date date, int diff){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, diff);
		return c.getTime();
	}

	public static long diff(Date ldate, Date rdate) {
		return rdate.getTime() - ldate.getTime();
	}

	public static boolean dateBeforeNow(Date date){
		return System.currentTimeMillis() > date.getTime();
	}
	
	public static List<Date> datesAfterNow(int count) {
		List<Date> result = new ArrayList<Date>(count);
		Date now = DateTools.now();
		for (int i = 0; i < count; i++) {
			result.add(addDays(now,i));
		}
		return result;
	}
	
	public static List<String> datesAfterNow(int count,String pattern) {
		List<String> result = new ArrayList<String>(count);
		Date now = DateTools.now();
		for (int i = 0; i < count; i++) {
			result.add(convert(addDays(now,i),pattern));
		}
		return result;
	}
	
	/**
	 * 该日期的最早时间点
	 * @param date
	 * @return
	 */
	public static Date newestOfDay(Date date){
		String dateStr = convert(date, DATE_PATTERN);
		return convert(dateStr, DATE_PATTERN);
	}
	
	
	/**
	 * 该日期的最晚时间点
	 * @param date
	 * @return
	 */
	public static  Date lastOfDay(Date date){
		String dateStr = convert(date, DATE_PATTERN) + " 23:59:59";
		return convert(dateStr, DATETIME_PATTERN);
	}
	
	
	
	
}
