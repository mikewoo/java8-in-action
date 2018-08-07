package com.mikewoo.study.java8.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SimpleDateFormat 线程安全使用方式
 * @author Eric Gui
 * @date 2018/8/7
 */
public class DateExample3 {

    public final static String STR_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static final int CLIENT_COUNT = 500;

    public static final int THREAD_COUNT = 30;

    private static final ThreadLocal<DateFormat> DF_DATETIME = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(STR_DATETIME);
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_COUNT);
        for (int i = 0; i < CLIENT_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    Date date = null;
                    try {
                        date = DF_DATETIME.get().parse("2018-08-07 16:19:36");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(date);
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
    }
}
