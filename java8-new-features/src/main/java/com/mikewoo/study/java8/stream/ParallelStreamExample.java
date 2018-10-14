package com.mikewoo.study.java8.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Eric Gui
 * @date 2018/8/3
 */
public class ParallelStreamExample {


    public static void main(String[] args) {
        System.out.println("measureSum base normalSum function: " + measureSum(ParallelStreamExample::normalSum, 100_000_000) + " MS");
        // System.out.println("measureSum base iterateStream function: " + measureSum(ParallelStreamExample::iterateStream, 100_000_000) + " MS");
        // System.out.println("measureSum base parallelStream function: " + measureSum(ParallelStreamExample::parallelStream, 100_000_000) + " MS");
        // System.out.println("measureSum base parallelStreamToLong function: " + measureSum(ParallelStreamExample::parallelStreamToLong, 100_000_000) + " MS");
        // System.out.println("measureSum base parallelStreamToLong2 function: " + measureSum(ParallelStreamExample::parallelStreamToLong2, 100_000_000) + " MS");
        // System.out.println("measureSum base parallelStreamToLong3 function: " + measureSum(ParallelStreamExample::parallelStreamToLong3, 100_000_000) + " MS");
        System.out.println("measureSum base parallelStreamToLong4 function: " + measureSum(ParallelStreamExample::parallelStreamToLong4, 100_000_000) + " MS");
    }

    private static long measureSum(Function<Long, Long> adder, long limit) {
        long faster = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long started = System.currentTimeMillis();
            long sum = adder.apply(limit);
            long duration = System.currentTimeMillis() - started;
            if (duration < faster)
                faster = duration;
        }

        return faster;
    }

    private static long normalSum(long limit) {
        long sum = 0L;
        for (int i = 0; i < limit; i++) {
            sum += i;
        }

        return sum;
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).parallel().reduce(0L, Long::sum);
    }

    private static long parallelStreamToLong(long limit) {
        return Stream.iterate(1L, i -> i + 1).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStreamToLong2(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).mapToLong(Long::longValue).sum();
    }

    private static long parallelStreamToLong3(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).mapToLong(Long::longValue).parallel().sum();
    }

    private static long parallelStreamToLong4(long limit) {
        return LongStream.rangeClosed(1L, limit).parallel().reduce(0L, Long::sum);
    }
}
