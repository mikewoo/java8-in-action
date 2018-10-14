package com.mikewoo.study.java8.stream;

import com.mikewoo.study.java8.domain.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Eric Gui
 * @date 2018/7/25
 */
public class SimpleStream {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        /*List<String> dishNamesByCollections = getDishNamesByCollections(menu);
        System.out.println(dishNamesByCollections);*/

       /* List<String> nameList = getDishNamesByStrems(menu);
        System.out.println(nameList);*/

       menu.stream().forEach(System.out::println);

        System.out.println("=========================");
        List<String> collect = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("map " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("==========================");
        long count = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("map " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .count();
        System.out.println("count: " + count);
    }

    private static List<String> getDishNamesByStrems(List<Dish> menu) {
        return menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowCaories = new ArrayList<>();
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaories.add(dish);
            }
        }

        Collections.sort(lowCaories, Comparator.comparing(Dish::getCalories));

        List<String> dishNames = new ArrayList<>();
        for (Dish dish : lowCaories) {
            dishNames.add(dish.getName());
        }
        return dishNames;
    }
}
