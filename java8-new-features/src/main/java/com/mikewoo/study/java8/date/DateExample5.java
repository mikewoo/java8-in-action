package com.mikewoo.study.java8.date;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Eric Gui
 * @date 2018/8/7
 */
public class DateExample5 {

    public final static DateTimeFormatter STR_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        // testDateTimeFormat();

        // testDateTimeParse();

        // 北京时间转为UTC时间
        ZonedDateTime zonedDateTime = changeShanghaiToUTC("2018-08-07 17:53:09");
        System.out.println(zonedDateTime);
    }

    public static ZonedDateTime changeShanghaiToUTC(String beijingDateTimeStr){
        DateTimeFormatter beijingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"));
        if(StringUtils.isBlank(beijingDateTimeStr)){
            return null;
        }
        ZonedDateTime beijingDateTime = ZonedDateTime.parse(beijingDateTimeStr, beijingFormatter);
        return beijingDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }


    private static void testDateTimeParse() {
        String dateTime = "2018-08-07T17:55:36.738";
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(localDateTime);

        dateTime = "2018-08-07 17:53:09";
        localDateTime = LocalDateTime.parse(dateTime, STR_DATETIME);
        System.out.println(localDateTime);
    }

    private static void testDateTimeFormat() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(dateTime);

        String now = localDateTime.format(STR_DATETIME);
        System.out.println(now);
    }
}
