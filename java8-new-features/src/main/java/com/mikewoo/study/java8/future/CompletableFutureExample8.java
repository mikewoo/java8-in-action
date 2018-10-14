package com.mikewoo.study.java8.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author Eric Gui
 * @date 2018/8/7
 */
public class CompletableFutureExample8 {

    public static void main(String[] args) throws InterruptedException {

        // testWhenComplete();

        // testHandle();

        // testThenRun();

        // testThenAccept();

        // testThenCompose();

        // testThenCombine();

        // testThenAcceptBoth();

        Thread.sleep(1000L);
    }

    private static void testThenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (i, j) -> {
                    System.out.println(i);
                    System.out.println(j);
                    System.out.println(i + j);
                });
    }

    private static void testThenCombine() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 10), (i, j) -> i + j)
                .thenAccept(System.out::println);
    }

    private static void testThenCompose() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);
    }

    private static void testThenAccept() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .thenAccept(System.out::println);
    }

    private static void testThenRun() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v))
                .thenRun(() -> {
                    System.out.println("finished");
                });
    }

    private static void testHandle() {
        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> System.out.println(v));
    }

    private static void testWhenComplete() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v));
    }
}
