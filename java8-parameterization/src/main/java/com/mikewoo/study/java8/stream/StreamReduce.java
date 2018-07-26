package com.mikewoo.study.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class StreamReduce {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1, 8, 9, 6, 1);

        Integer result = list.stream().reduce(0, Integer::sum);
        System.out.println(result);

        list.stream().reduce((i, j) -> i + j).ifPresent(System.out::println);

        list.stream().reduce((i, j) -> i > j ? i : j).ifPresent(System.out::println);

        list.stream().reduce(Integer::max).ifPresent(System.out::println);

    }
}
