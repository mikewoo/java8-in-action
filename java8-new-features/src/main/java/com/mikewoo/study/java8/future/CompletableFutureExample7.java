package com.mikewoo.study.java8.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class CompletableFutureExample7 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

        /*CompletableFuture.supplyAsync(CompletableFutureExample7::get, executorService)
                .thenApply(CompletableFutureExample7::mutilply)
                .whenComplete((v, t) -> Optional.ofNullable(v).ifPresent(System.out::println));*/

        List<Integer> idList = Arrays.asList(1, 2, 3, 4, 5);
        Stream<CompletableFuture<Double>> completableFutureStream = idList.stream().map(id -> CompletableFuture.supplyAsync(() -> queryProductionPrice(id), executorService));
        Stream<CompletableFuture<Double>> mutilplyFutureStream = completableFutureStream.map(future -> future.thenApply(CompletableFutureExample7::mutilply));
        List<Double> priceList = mutilplyFutureStream.map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(priceList);
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

    private static double queryProductionPrice(int id) {
        return CompletableFutureExample7.get();
    }
}
