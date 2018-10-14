package com.mikewoo.study.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture 的completeExceptionally方法将导致CompletableFuture 内发生问题的异常抛出。
 * 这样，当执行任务发生异常时，调用get()方法的线程将会收到一个 ExecutionException 异常，该异常接收了一个包含失败原因的Exception 参数。
 *
 * @author Eric Gui
 * @date 2018/8/6
 */
public class CompletableFutureExample10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("执行过程出现异常");
            } catch (Exception e) {
                // 告诉completableFuture任务发生异常了
                completableFuture.completeExceptionally(e);
            }
        }).start();

        System.out.println("main function running...");
        Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);

    }

}
