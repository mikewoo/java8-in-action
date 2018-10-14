package com.mikewoo.study.java8.lambda;

import com.mikewoo.study.java8.domain.Apple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * @author : Eric Gui
 * @date 2018/7/16 15:59
 */
public class LambdaDemo {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        Thread.currentThread().join();


        // Comparator<Apple> byColor = (o1, o2) -> o1.getColor().compareTo(o2.getColor());
        Comparator<Apple> byColor = Comparator.comparing(Apple::getColor);

        List<Apple> list = Collections.emptyList();

        list.sort(byColor);


        Function<String, Integer> function = (String s) -> s.length();

        Predicate<Apple> predicate = (Apple a) -> a.getColor().equals("green");

        IntFunction<Integer> intFunction = (int a) -> 42;

        Runnable r = () -> {};
    }
}
