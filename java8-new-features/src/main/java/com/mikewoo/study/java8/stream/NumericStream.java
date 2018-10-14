package com.mikewoo.study.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class NumericStream {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1, 8, 9, 6, 1);

        Stream<Integer> stream = list.stream().filter(i -> i > 3);
        IntStream intStream = stream.mapToInt(i -> i.intValue());
        int sum = intStream.filter(i -> i % 2 == 0).sum();
        System.out.println(sum);

        int a = 9;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a = " + r[0] + ", b = " + r[1] + ", c = " + r[2]));

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a = " + r[0] + ", b = " + r[1] + ", c = " + r[2]));
    }
}
