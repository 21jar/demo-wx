package com.demo.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 胡超云 on 2016/12/14.
 * 时间工具类
 */
@Slf4j
public class DateUtil {

    //获取不可预约时间
    public static int getInvalidTime() {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        //当前时间加一天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        //明天的12点前都不可预约
        String invalidTime = sf.format(calendar.getTime()) + " 12:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return (int) (sdf.parse(invalidTime).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    
    //获取预约优惠时间
    public static String getOrderEndTime() {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        //当前时间加七天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        //明天的12点前都不可预约
        String orderEndTime = sf.format(calendar.getTime());

        return orderEndTime;
    }
    
    //获取给定时间一天后的时间戳
    public static long getAddOneDayTimeStamp(Date date){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(calendar.DAY_OF_MONTH, 1);
    	return calendar.getTime().getTime();
    }

    //获取当天日期时间戳
    public static Integer getLocalTimeStamp() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return toTimeStamp(sf.format(new Date()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    //获取当前时间时间戳
    public static Long getNowTimeStamp(){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			return defaultParseDateTime(sf.format(new Date())).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }

    //时间戳转字符串
    public static String toStrDate(Integer timeStamp) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = Long.valueOf(timeStamp) * 1000;
        return sf.format(new Date(lt));
    }

    //时间戳转字符串(年月日)
    public static String toStrDate2(Integer timeStamp) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        long lt = Long.valueOf(timeStamp) * 1000;
        return sf.format(new Date(lt));
    }

    //字符串转时间戳
    public static int toTimeStamp(String timeStamp) throws ParseException {
        return (int) (defaultParseDateTime(timeStamp).getTime() / 1000);
    }
    public static int toTimeStamp1(String timeStamp) throws ParseException {
        return (int) (defaultParseDateTime1(timeStamp).getTime() / 1000);
    }
    

    //按照给定的格式化字符串格式化日期
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    //按照给定的格式化字符串解析日期
    public static Date defaultParseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateStr);
    }

    //按照给定的格式化字符串解析日期
    public static Date defaultParseDateTime(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);
    }
    public static Date defaultParseDateTime1(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateStr);
    }

    //按照给定的格式化字符串解析日期
    public static Date parseDate(String dateStr, String formatStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.parse(dateStr);
    }

    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static Integer getWeekNum(Integer date) throws ParseException {
        //时间戳转日期
        String dateString = DateUtil.toStrDate(date);
        Date meetTime = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        //根据日期获取星期几
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(meetTime);
        return calendar.get(Calendar.DAY_OF_WEEK)-1;
    }

    public static String getNextDate(String date) throws ParseException {

        Date dat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dat);
        rightNow.add(Calendar.DAY_OF_YEAR, 1);

        return new SimpleDateFormat("yyyy-MM-dd").format(rightNow.getTime());
    }

}
