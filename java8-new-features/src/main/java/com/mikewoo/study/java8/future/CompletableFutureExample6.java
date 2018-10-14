package com.mikewoo.study.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class CompletableFutureExample6 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

        CompletableFuture.supplyAsync(CompletableFutureExample6::get, executorService)
                .thenApply(CompletableFutureExample6::mutilply)
                .whenComplete((v, t) -> Optional.ofNullable(v).ifPresent(System.out::println));
    }

    private static double mutilply(double value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return value * 10;
    }

    private static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = RANDOM.nextDouble();
        System.out.println("result = " + result);
        return result;
    }
}
