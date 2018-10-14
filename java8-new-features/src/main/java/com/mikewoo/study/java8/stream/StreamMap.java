package com.mikewoo.study.java8.stream;

import com.mikewoo.study.java8.domain.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class StreamMap {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1, 8, 9, 6, 1);

        List<Integer> result = list.stream().map(i -> i * 2).collect(toList());
        System.out.println(result);

        System.out.println("=======================");
        List<String> dishNames = listDish().stream().map(dish -> dish.getName()).collect(toList());
        System.out.println(dishNames);

        System.out.println("========================");
        String[] words = {"Hello", "World"};
        Arrays.stream(words).map(w -> w.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }

    private static List<Dish> listDish() {
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
        return menu;
    }
}
