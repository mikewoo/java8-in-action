package com.mikewoo.study.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class CompletableFutureExample4 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        AtomicBoolean finished = new AtomicBoolean(false);

        CompletableFuture.supplyAsync(CompletableFutureExample4::get)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(e -> e.printStackTrace());
                    finished.set(true);
                });
        System.out.println("main function running...");

        while (!finished.get()) {
            Thread.sleep(10);
        }
    }

    private static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }
}
