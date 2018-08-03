package com.mikewoo.study.java8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Eric Gui
 * @date 2018/8/2
 */
public class CountRecursiveTask extends RecursiveTask<Integer> {

    private final static int THRESHOLD = 3; // 阈值

    private int start;

    private int end;

    private int[] data;

    public CountRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
        } else {
            // 生成子任务
            int middle = (start + end) / 2;
            CountRecursiveTask leftTask = new CountRecursiveTask(start, middle, data);
            CountRecursiveTask rightTask = new CountRecursiveTask(middle, end, data);

            // 执行子任务
            invokeAll(leftTask, rightTask);

            // 等待任务执行结束合并结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务执行结果
            sum = leftResult + rightResult;

        }
        return sum;
    }
}
