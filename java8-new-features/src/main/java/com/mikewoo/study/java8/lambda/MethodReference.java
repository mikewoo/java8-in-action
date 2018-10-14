package com.mikewoo.study.java8.lambda;

import com.mikewoo.study.java8.domain.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Eric Gui
 * @date 2018/7/23
 */
public class MethodReference {

    public static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }


    public static void main(String[] args) {

        Consumer<String> consumer = (s) -> System.out.println(s);
        useConsumer(consumer, "Hello Java");

        useConsumer((s) -> System.out.println(s), "Hello Eric");

        useConsumer(System.out::println, "Hello Gui");

        List<Apple> list = Arrays.asList(new Apple("green", 110), new Apple("yellow", 133), new Apple("red", 123));
        System.out.println(list);
        list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);

        list.sort(Comparator.comparing(Apple::getColor));
        System.out.println(list);
        System.out.println("============");

        list.stream().forEach((a) -> System.out.println(a));
        list.stream().forEach(System.out::println);

        int value = Integer.parseInt("123");
        Function<String, Integer> function = Integer::parseInt;

        Integer integer = function.apply("123");
        System.out.println(integer);

        BiFunction<String, Integer, Character> characterBiFunction = String::charAt;
        Character c = characterBiFunction.apply("Hello", 2);
        System.out.println(c);

        String string = new String("Hello");
        Function<Integer, Character> characterFunction = string::charAt;
        Character c2 = characterFunction.apply(4);
        System.out.println(c2);

        Supplier<String> supplier = String::new;
        String s = supplier.get();
        System.out.println(s);

        BiFunction<String, Long, Apple> appleBiFunction = Apple::new;

        Apple apple = appleBiFunction.apply("red", 120L);
        System.out.println(apple);

        AppleConstructorFunction<String, Long, String, Apple> appleConstructorFunction = Apple::new;
        apple = appleConstructorFunction.apply("green", 131L, "Shanxi");
        System.out.println(apple);
    }
}
