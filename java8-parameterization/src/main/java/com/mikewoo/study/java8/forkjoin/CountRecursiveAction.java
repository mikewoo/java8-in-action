package com.mikewoo.study.java8.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eric Gui
 * @date 2018/8/2
 */
public class CountRecursiveAction extends RecursiveAction {

    private final static int THRESHOLD = 3; // 阈值

    private int start;

    private int end;

    private int[] data;

    public CountRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        boolean canCompute = (end - start) <= THRESHOLD;

        if (canCompute) {
            for (int i = start; i < end; i++) {
                CountHelper.accumulate(data[i]);
            }
        } else {
            // 生成子任务
            int middle = (end - start) / 2;
            CountRecursiveAction leftAction = new CountRecursiveAction(start, middle, data);
            CountRecursiveAction rightAction = new CountRecursiveAction(middle, end, data);

            // 执行子任务
            invokeAll(leftAction, rightAction);

            // 等待任务执行结束
            leftAction.join();
            rightAction.join();
        }
    }

    static class CountHelper {
        private final static AtomicInteger sum = new AtomicInteger(0);

        static void accumulate(int value) {
            sum.getAndAdd(value);
        }

        public static int getSum() {
            return sum.get();
        }

        static void reset() {
            sum.set(0);
        }
    }
}
