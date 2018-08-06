package com.mikewoo.study.java8.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Spliterator接口是1.8新加的接口，字面意思可分割的迭代器。
 * 不同以往的iterator需要顺序迭代，Spliterator可以分割为若干个小的迭代器进行并行操作，既可以实现多线程操作提高效率，又可以避免普通迭代器的fail-fast机制所带来的异常。
 * Spliterator可以配合1.8新加的Stream进行并行流的实现，大大提高处理效率。
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
