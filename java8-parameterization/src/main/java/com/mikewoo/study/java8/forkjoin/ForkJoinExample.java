package com.mikewoo.study.java8.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @author Eric Gui
 * @date 2018/8/2
 */
public class ForkJoinExample {

    private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


    private static int clacSum() {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        calcSum();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        recursiveTaskTest(forkJoinPool);

        recursiveActionTest(forkJoinPool);
    }

    private static void recursiveActionTest(ForkJoinPool forkJoinPool) {
        CountRecursiveAction countRecursiveAction = new CountRecursiveAction(0, data.length, data);
        forkJoinPool.submit(countRecursiveAction);
        System.out.println("countRecursiveAction sum " + CountRecursiveAction.CountHelper.getSum());
    }

    private static void calcSum() {
        int sum = clacSum();
        System.out.println("clacSum: " + sum);
    }

    private static void recursiveTaskTest(ForkJoinPool forkJoinPool) {
        CountRecursiveTask countTask = new CountRecursiveTask(0, data.length, data);

        Future<Integer> forkJoinResult = forkJoinPool.submit(countTask);
        try {
            System.out.println("forkJoinResult: " + forkJoinResult.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
