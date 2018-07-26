package com.mikewoo.study.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class StreamFind {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1, 8, 9, 6, 1);

        Optional<Integer> findAnyInteger = list.stream().filter(i -> i % 2 == 0).findAny();
        System.out.println(findAnyInteger.get());

        Optional<Integer> findFirstInteger = list.stream().filter(i -> i % 2 == 0).findFirst();
        System.out.println(findFirstInteger.get());

    }
}
