package com.mikewoo.study.java8.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class FutureExample1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start blockInvoke, current time is " + System.currentTimeMillis());
        String value = blockInvoke(() -> {
            try {
                Thread.sleep(10000);
                return "action finished";
            } catch (InterruptedException e) {
                return "exception happened";
            }
        });

        System.out.println("end blockInvoke, current time is " + System.currentTimeMillis());
        System.out.println("result is " + value);
    }

    private static <T> T blockInvoke(Callable<T> callable) {
        return callable.action();
    }

    private interface Callable<T> {

        T action();
    }
}
