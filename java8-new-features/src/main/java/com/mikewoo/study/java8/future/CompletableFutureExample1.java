package com.mikewoo.study.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Future 接口的局限性
 * <p>
 * Future接口可以构建异步应用，但依然有其局限性。它很难直接表述多个Future 结果之间的依赖性。实际开发中，我们经常需要达成以下目的：
 * <p>
 * 将两个异步计算合并为一个——这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果。
 * 等待 Future 集合中的所有任务都完成。
 * 仅等待 Future集合中最快结束的任务完成（有可能因为它们试图通过不同的方式计算同一个值），并返回它的结果。
 * 通过编程方式完成一个Future任务的执行（即以手工设定异步操作结果的方式）。
 * 应对 Future 的完成事件（即当 Future 的完成事件发生时会收到通知，并能使用 Future 计算的结果进行下一步的操作，不只是简单地阻塞等待操作的结果）
 * <p>
 * 新的CompletableFuture类将使得这些成为可能。
 * <p>
 * JDK1.8才新加入的一个实现类CompletableFuture，实现了Future<T>, CompletionStage<T>两个接口。
 * <p>
 * 当一个Future可能需要显示地完成时，使用CompletionStage接口去支持完成时触发的函数和操作。
 * <p>
 * 当两个及以上线程同时尝试完成、异常完成、取消一个CompletableFuture时，只有一个能成功。
 * <p>
 * CompletableFuture实现了CompletionStage接口的如下策略：
 * <p>
 * 为了完成当前的CompletableFuture接口或者其他完成方法的回调函数的线程，提供了非异步的完成操作。
 * <p>
 * 没有显式入参Executor的所有async方法都使用ForkJoinPool.commonPool()为了简化监视、调试和跟踪，所有生成的异步任务都是标记接口AsynchronousCompletionTask的实例。
 * <p>
 * 所有的CompletionStage方法都是独立于其他共有方法实现的，因此一个方法的行为不会受到子类中其他方法的覆盖。
 * <p>
 * CompletableFuture实现了Future接口的如下策略：
 * <p>
 * CompletableFuture无法直接控制完成，所以cancel操作被视为是另一种异常完成形式。方法isCompletedExceptionally可以用来确定一个CompletableFuture是否以任何异常的方式完成。
 * <p>
 * 以一个CompletionException为例，方法get()和get(long,TimeUnit)抛出一个ExecutionException，对应CompletionException。为了在大多数上下文中简化用法，这个类还定义了方法join()和getNow，而不是直接在这些情况中直接抛出CompletionException。
 *
 * @author Eric Gui
 * @date 2018/8/6
 */
public class CompletableFutureExample1 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        }).start();

        System.out.println("main function running...");
        Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);

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
