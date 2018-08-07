package com.mikewoo.study.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author Eric Gui
 * @date 2018/8/6
 */
public class CompletableFutureExample3 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        /**
         *  supplyAsync 方法接受一个生产者（Supplier）作为参数，返回一个 CompletableFuture对象。
         *  生产者方法会交由 ForkJoinPool池中的某个执行线程（ Executor ）运行，但是你也可以使用 supplyAsync 方法的重载版本，
         *  传递第二个参数指定线程池执行器执行生产者方法。
         */
        CompletableFuture.supplyAsync(CompletableFutureExample3::get)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(e -> e.printStackTrace());
                });
        System.out.println("main function running...");
        Thread.currentThread().join();
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
