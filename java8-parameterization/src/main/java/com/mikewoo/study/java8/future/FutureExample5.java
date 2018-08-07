package com.mikewoo.study.java8.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class FutureExample5 {

    public static void main(String[] args) {
        System.out.println("main function started.");
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "action finished";
            } catch (InterruptedException e) {
                return "exception happened";
            }
        });

        future.addListener(new Listener<String>() {
            @Override
            public void listen(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("error");
                cause.printStackTrace();
            }
        });

        System.out.println("main function continue running..., do something else...");
    }

    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);

        Future<T> future = new Future<T>() {
            private Listener<T> listener;
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public void addListener(Listener<T> listener) {
                this.listener = listener;
            }

            @Override
            public Listener<T> getListener() {
                return this.listener;
            }
        };

        Thread thread = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                if (future.getListener() != null) {
                    future.getListener().listen(value);
                }
            } catch (Throwable cause) {
                if (future.getListener() != null) {
                    future.getListener().exception(cause);
                }
            }
        });
        thread.start();



        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();

        void addListener(Listener<T> listener);

        Listener<T> getListener();
    }

    private interface Callable<T> {

        T action();
    }

    private interface Listener<T> {
        void listen(T t);
        void exception(Throwable cause);
    }
}
