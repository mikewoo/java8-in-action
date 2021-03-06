package com.mikewoo.study.java8.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class FutureExample3 {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("start submit, current time is " + System.currentTimeMillis());
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000);
                return "execute finithed";
            } catch (InterruptedException e) {
                return "exception happened";
            }
        });
        System.out.println("end submit, current time is " + System.currentTimeMillis());

        // String result = future.get();
        String result = future.get(3, TimeUnit.SECONDS);
        System.out.println("get result = [" + result + "], current time is " + System.currentTimeMillis());
        executorService.shutdown();
    }
}
