package com.mikewoo.study.java8;

import com.mikewoo.study.java8.filter.AppleFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据条件过滤Apple
 * @author : Eric Gui
 * @date 2018/7/16 11:29
 */
public class AppleManager {

    public static List<Apple> findGreenApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

}
