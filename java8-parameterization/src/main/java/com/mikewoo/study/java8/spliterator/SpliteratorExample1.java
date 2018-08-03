package com.mikewoo.study.java8.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author Eric Gui
 * @date 2018/8/3
 */
public class SpliteratorExample1 {

    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(0, 10);
        Spliterator.OfInt spliterator = intStream.spliterator();
        Consumer<Integer> consumer = i -> System.out.println(i);
        spliterator.forEachRemaining(consumer);
    }
}
