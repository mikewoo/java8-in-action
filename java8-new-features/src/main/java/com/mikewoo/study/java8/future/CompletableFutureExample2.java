package com.mikewoo.study.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class CompletableFutureExample2 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        }).start();

        System.out.println("main function running...");
        completableFuture.whenComplete((value, throwable) -> {
            Optional.ofNullable(value).ifPresent(System.out::println);
            Optional.ofNullable(throwable).ifPresent(e -> e.printStackTrace());
        });

    }

    private static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
            int a = 10 / 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }
}
