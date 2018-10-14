package com.mikewoo.study.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Eric Gui
 * @date 2018/8/7
 */
public class DateTimeUtils {
    /**
     * 根据给定的时间返回对应格式的字符串
     *  1、小于一分钟---"just now"
     *  2、大于一分钟而在本日内的，返回格式为"HH:mm"
     *  3、今年之内而不再本日内，格式为"MM-dd"
     *  4、否则，格式为"yyyy-MM-dd"
     * @param strDate
     * @return
     */
    public static String getFormatDate(String strDate) {
        DateTimeFormatter formatter;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDate);
        if (zonedDateTime.toLocalDate().equals(LocalDate.now())) {
            if (getOverMinutes(strDate) <= 1) {
                return "just now";
            } else {
                formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.CHINA);
                return zonedDateTime.format(formatter);
            }
        } else {
            if (zonedDateTime.toLocalDate().getYear() == LocalDate.now().getYear()) {
                formatter = DateTimeFormatter.ofPattern("MM-dd", Locale.CHINA);
            } else {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
            }
            return zonedDateTime.format(formatter);
        }
    }

    /**
     * 将给定的秒数转换为一段持续时间，如70s,可为01:10
     * @param s
     * @return
     */
    public static String sec2Duration(int s) {
        int sec;
        if (s < 0) {
            return "00:00";
        }
        int min = s / 60;
        if (min < 60) {
            sec = s % 60;
            return unitFormat(min) + ":" + unitFormat(sec);
        } else {
            int hour = min / 60;
            min = min % 60;
            sec = s - hour * 3600 - min * 60;
            return unitFormat(hour) + ":" + unitFormat(min) + ":" + unitFormat(sec);
        }
    }
    public static String unitFormat(int i) {
        return i >= 0 && i < 10 ? "0" + i : i + "";
    }

    /**
     * 给定开始时间，返回还剩多少天多少时多少分的字符串数组
     * @param startTime
     * @return
     */
    public static String[] getLeftStr(String startTime) {
        Instant start = ZonedDateTime.parse(startTime).toInstant();
        long minutes = Duration.between(Instant.now(), start).toMinutes();
        if (minutes < 60 && minutes > 0) {
            return new String[]{"0", "0", minutes + ""};
        } else if (minutes <= 0) {
            return null;
        }
        long hours = minutes / 60;
        if (hours < 60) {
            minutes = minutes % 60;
            return new String[]{"0", hours + "", minutes + ""};
        } else {
            long days = hours / 24;
            hours = hours % 24;
            minutes = minutes - days * 24 * 60 - hours * 60;
            return new String[]{days + "", hours + "", minutes + ""};
        }
    }
    /**
     * 根据创建时间和总共时间得到当前剩余时间
     * @param createStr
     * @param totalMills
     * @return
     */
    public static long getLeftMills(String createStr, long totalMills) {
        long leftMills = totalMills - getOverMills(createStr);
        return leftMills >= 0 ? leftMills : 0;
    }
    public static long getLeftMinutes(String createStr, long totalMinutes) {
        long leftMinutes = totalMinutes - getOverMinutes(createStr);
        return leftMinutes >= 0 ? leftMinutes : 0;
    }
    public static long getLeftHours(String createStr, long totalHours) {
        long leftHours = totalHours - getOverHours(createStr);
        return leftHours >= 0 ? leftHours : 0;
    }
    /**
     * 根据跟定的时间得到到目前为止过了多少秒
     * @param time
     * @return
     */
    public static long getOverMills(String time){
        return getOverTime(time).toMillis();
    }
    public static long getOverMinutes(String time){
        return getOverTime(time).toMinutes();
    }
    public static long getOverHours(String time){
        return getOverTime(time).toHours();
    }
    private static Duration getOverTime(String time) {
        Instant timeInstant = ZonedDateTime.parse(time).toInstant();
        Instant now = Instant.now();
        return Duration.between(timeInstant, now);
    }

    /**
     * 将UNIX时间戳（秒级）格式化成ZoneDateTime的格式
     */
    public static String format2ZoneDateTimeFromSecond(long timestamp) {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of("Asia/Shanghai")).toString();
    }
}
