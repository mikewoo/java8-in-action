package com.mikewoo.study.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1, 8, 9, 6, 1);

        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(toList());
        System.out.println(result);

        result = list.stream().distinct().collect(toList());
        System.out.println(result);

        result = list.stream().skip(5).collect(toList());
        System.out.println(result);

        result = list.stream().limit(5).collect(toList());
        System.out.println(result);

        System.out.println("============");
        list.forEach(System.out::println);

        System.out.println("============");
        list.forEach(i -> System.out.println(i));

        System.out.println("============");
        list.forEach((Integer i) -> System.out.println(i));

        System.out.println("============");
        for (int i : list) {
            System.out.println(i);
        }
    }
}
