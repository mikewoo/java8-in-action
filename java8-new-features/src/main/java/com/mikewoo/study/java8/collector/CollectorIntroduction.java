package com.mikewoo.study.java8.collector;

import com.mikewoo.study.java8.domain.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Eric Gui
 * @date 2018/7/31
 */
public class CollectorIntroduction {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("red", 130)
                , new Apple("green", 140)
                , new Apple("green", 160)
                , new Apple("red", 120)
                , new Apple("green", 160));

        Optional.ofNullable(groupByColorNormal(list)).ifPresent(System.out::println);
        System.out.println("=====================");
        Optional.ofNullable(groupByColorFunction(list)).ifPresent(System.out::println);
        System.out.println("=====================");
        Optional.ofNullable(groupByColorCollector(list)).ifPresent(System.out::println);

    }

    private static Map<String, List<Apple>> groupByColorNormal(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple a : apples) {
            List<Apple> list = map.get(a.getColor());
            if (null == list) {
                list = new ArrayList<>();
                map.put(a.getColor(), list);
            }
            list.add(a);
        }

        return map;
    }

    private static Map<String, List<Apple>> groupByColorFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        apples.stream().forEach(a -> {
            List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(), list);
                return list;
            });
            colorList.add(a);
        });
        return map;
    }

    private static Map<String, List<Apple>> groupByColorCollector(List<Apple> apples) {
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
