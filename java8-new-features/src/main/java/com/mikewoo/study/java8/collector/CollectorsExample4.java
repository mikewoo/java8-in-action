package com.mikewoo.study.java8.collector;


import com.mikewoo.study.java8.domain.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.mikewoo.study.java8.domain.DishMenu.menu;

/**
 * @author Eric Gui
 * @date 2018/8/2
 */
public class CollectorsExample4 {

    public static void main(String[] args) {
        testSummingDouble();
        testSummingLong();
        testSummingInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        testToList();
        testToSet();
        testToMap();
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorAndSupplier();
    }

    private static void testSummingDouble() {
        System.out.println("testSummingDouble");
        Double collect = menu.stream().collect(Collectors.summingDouble(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testSummingLong() {
        System.out.println("testSummingLong");
        Long collect = menu.stream().collect(Collectors.summingLong(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testSummingInt() {
        System.out.println("testSummingInt");
        Integer collect = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);

        int sum = menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum();
        Optional.ofNullable(sum).ifPresent(System.out::println);
    }

    private static void testToCollection() {
        System.out.println("testToCollection");
        LinkedList<Dish> collect = menu.stream().filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toCollection(LinkedList::new));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToConcurrentMap() {
        System.out.println("testToConcurrentMap");
        ConcurrentMap<String, Integer> collect = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToConcurrentMapWithBinaryOperator() {
        System.out.println("testToConcurrentMapWithBinaryOperator");
        ConcurrentMap<Dish.Type, Long> collect = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType,
                        v -> 1L,
                        (a, b) -> a + b));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToConcurrentMapWithBinaryOperatorAndSupplier");
        ConcurrentSkipListMap<Dish.Type, Long> collect = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType,
                        v -> 1L,
                        (a, b) -> a + b,
                        ConcurrentSkipListMap::new));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToList() {
        List<Dish> collect = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToSet() {
        System.out.println("testToSet");
        Set<Dish> collect = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet());
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToMap() {
        System.out.println("testToMap");
        Map<String, Integer> collect = menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(Dish::getName,
                        Dish::getCalories),
                        Collections::synchronizedMap));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToMapWithBinaryOperator() {
        System.out.println("testToMapWithBinaryOperator");
        Map<Dish.Type, Long> collect = menu.stream().collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testToMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToMapWithBinaryOperatorAndSupplier");
        Hashtable<Dish.Type, Long> collect = menu.stream().collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b, Hashtable::new));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
}
