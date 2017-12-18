package com.demo.core.utils;

import org.springframework.util.ObjectUtils;

import com.demo.core.exception.BusinessException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fade on 2017/5/15.
 */
public class OwnerUtil {

    public static String getSex(String cardId) {
        String sex = cardId.substring(16, 17);
        if (Integer.parseInt(sex) / 2 == 0) {
            return "F";
        } else {
            return "M";
        }
    }

    public static String getBirthday(String cardId) {
        return cardId.substring(6, 14);
    }

    public static String getAge(String cardId) {
        String birthday = getBirthday(cardId);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            return String.valueOf(getAge(df.parse(birthday)));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "0";
    }

    //由出生日期获得年龄
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    public static String getExpireDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dd = df.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.YEAR, 1);//加一年
            calendar.add(Calendar.DAY_OF_YEAR, -1);//减一天
            return df.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLastQuoteDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dd = df.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.YEAR, -1);//加一年
            calendar.add(Calendar.DAY_OF_YEAR, 1);//减一天
            return df.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getExpireDay(Date date) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        long s1 = date.getTime();//将时间转为毫秒
        long s2 = System.currentTimeMillis() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//得到今天0点的毫秒
        int day = (int) ((s1 - s2) / 1000 / 60 / 60 / 24);
        if(day < 0){
            day += 365;
        }
        return day;
    }

    public static int getExpireMonth(Date date) {
        if (ObjectUtils.isEmpty(date)) {
            throw new BusinessException("没有保险日期！", "");
        }
        long s1 = date.getTime();//将时间转为毫秒
        long s2 = System.currentTimeMillis() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//得到今天0点的毫秒
        int month = (int) ((s1 - s2) / 1000 / 60 / 60 / 24);
        return month;
    }

}
