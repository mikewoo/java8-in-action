package com.mikewoo.study.java8.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Eric Gui
 * @date 2018/8/7
 */
public class CompletableFutureExample9 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        // testRunAfterBoth1();

        // testRunAfterBoth2();

        // testApplyToEither();

        // testRunAfterEither();

        // testAllOf();

        testAnyOf();

        Thread.currentThread().join();

    }

    private static void testAnyOf() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<CompletableFuture<Double>> collect = list.stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureExample9::get))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("finished"));
    }

    private static void testAllOf() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<CompletableFuture<Double>> collect = list.stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureExample9::get))
                .collect(Collectors.toList());
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("finished"));
    }

    private static void testRunAfterEither() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 1 started.");
            return CompletableFutureExample9.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 2 started.");
            return CompletableFutureExample9.get();
        }), () -> System.out.println("finished"));

    }

    private static void testApplyToEither() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 1 started.");
            return CompletableFutureExample9.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 2 started.");
            return CompletableFutureExample9.get();
        }), v -> v * 10).thenAccept(System.out::println);
    }

    private static void testRunAfterBoth1() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " started.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            return 2;
        }), () -> System.out.println("done"));
    }

    private static void testRunAfterBoth2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " started.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
            return 1;
        }, executorService).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            return 2;
        }, executorService), () -> System.out.println("done"));

        executorService.shutdown();
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
