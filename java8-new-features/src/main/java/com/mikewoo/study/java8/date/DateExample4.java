package com.mikewoo.study.java8.date;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 * @author Eric Gui
 * @date 2018/8/7
 */
public class DateExample4 {

    public static void main(String[] args) throws InterruptedException {
        // testLocalDate();

        // testLocalTime();

        // testLocalDateTime();

        // testInstant();

        // testDuration();

        // testPeriod();

        // testZonedDateTime();


    }

    private static void testZonedDateTime() {
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        LocalDate date = LocalDate.of(2018, Month.AUGUST, 7);
        ZonedDateTime zdt1 = date.atStartOfDay(zone);
        System.out.println(zdt1);
        Instant instant = Instant.now();
        ZonedDateTime zdt2 = instant.atZone(zone);
        System.out.println(zdt2);
    }


    private static void testPeriod() {
        Period period = Period.between(LocalDate.of(2018, 1, 5), LocalDate.now());
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(period.getYears());
    }

    private static void testDuration() {
        LocalTime time = LocalTime.now();
        LocalTime beforeTime = time.minusHours(1);
        Duration duration = Duration.between(beforeTime, time);
        System.out.println(duration.toHours());
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    private static void testLocalDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime.toString());
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getHour());

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
    }

    private static void testLocalTime() {
        LocalTime localTime = LocalTime.of(16, 42, 20);
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.getNano());
        System.out.println(localTime.get(ChronoField.HOUR_OF_AMPM));
    }

    private static void testLocalDate() {
        LocalDate localDate = LocalDate.of(2018, 8, 7);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.get(ChronoField.DAY_OF_YEAR));
    }


}
