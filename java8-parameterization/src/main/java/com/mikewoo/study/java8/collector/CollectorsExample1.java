package com.mikewoo.study.java8.collector;

import com.mikewoo.study.java8.domain.Dish;
import static com.mikewoo.study.java8.domain.DishMenu.menu;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Eric Gui
 * @date 2018/7/31
 */
public class CollectorsExample1 {

    public static void main(String[] args) {
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();
        testCounting();
        testGroupByFunction();
        testGroupByFunctionAndCollector();
        testGroupByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }

    private static void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testAveragingInt() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "The average calories is " + a)))
                .ifPresent(System.out::println);
    }

    private static void testCounting() {
        System.out.println("testCounting");
        Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
    }

    private static void testGroupByFunction() {
        System.out.println("testGroupByFunction");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
    }

    private static void testGroupByFunctionAndCollector() {
        System.out.println("testGroupByFunctionAndCollector");
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void testGroupByFunctionAndSupplierAndCollector() {
        System.out.println("testGroupByFunctionAndSupplierAndCollector");
        TreeMap<Dish.Type, Double> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");
        IntSummaryStatistics summaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.ofNullable(summaryStatistics).ifPresent(System.out::println);
    }
}