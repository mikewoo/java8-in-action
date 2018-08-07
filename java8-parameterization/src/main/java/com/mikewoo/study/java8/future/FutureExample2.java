package com.mikewoo.study.java8.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class FutureExample2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start invoke, current time is " + System.currentTimeMillis());
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "action finished";
            } catch (InterruptedException e) {
                return "exception happened";
            }
        });
        System.out.println("end invoke, current time is " + System.currentTimeMillis());

        System.out.println("result is " + future.get());
        System.out.println("result is " + future.get());
        System.out.println("result is " + future.get());

        while (!future.isDone()) {
            Thread.sleep(100);
        }
        System.out.println("result is " + future.get());
    }

    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        thread.start();

        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };

        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();
    }

    private interface Callable<T> {

        T action();
    }
}
