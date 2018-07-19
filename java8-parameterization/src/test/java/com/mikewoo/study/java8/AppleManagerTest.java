package com.mikewoo.study.java8;

import com.mikewoo.study.java8.filter.AppleFilter;
import com.mikewoo.study.java8.filter.DefaultColorAndWeightFilter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Eric Gui
 * @date 2018/7/16 11:36
 */
public class AppleManagerTest {

    @Test
    public void findGreenApple() {
        Apple apple1 = new Apple("red", 123L);
        Apple apple2 = new Apple("green", 89L);
        Apple apple3 = new Apple("red", 76L);
        Apple apple4 = new Apple("green", 59L);
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        List<Apple> greenApples = AppleManager.findGreenApple(apples);
        assert greenApples.size() == 2;
        for (Apple apple : greenApples) {
            System.out.println("apple color: " + apple.getColor() + ", weight: " + apple.getWeight());
        }
    }

    @Test
    public void findAppleByColor() {
        Apple apple1 = new Apple("red", 123L);
        Apple apple2 = new Apple("green", 89L);
        Apple apple3 = new Apple("red", 76L);
        Apple apple4 = new Apple("green", 59L);
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        List<Apple> redApples = AppleManager.findApple(apples, "red");
        assert redApples.size() == 2;
        for (Apple apple : redApples) {
            System.out.println("apple color: " + apple.getColor() + ", weight: " + apple.getWeight());
        }
    }

    @Test
    public void findAppleByFilter() {
        Apple apple1 = new Apple("red", 123L);
        Apple apple2 = new Apple("green", 89L);
        Apple apple3 = new Apple("red", 76L);
        Apple apple4 = new Apple("green", 59L);
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        List<Apple> redApples = AppleManager.findApple(apples, new DefaultColorAndWeightFilter("red", 100L));
        assert redApples.size() == 1;
        for (Apple apple : redApples) {
            System.out.println("apple color: " + apple.getColor() + ", weight: " + apple.getWeight());
        }
    }

    @Test
    public void findAppleByFilterWithAnonymousClass() {
        Apple apple1 = new Apple("red", 123L);
        Apple apple2 = new Apple("green", 89L);
        Apple apple3 = new Apple("red", 76L);
        Apple apple4 = new Apple("green", 59L);
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        List<Apple> greenApples = AppleManager.findApple(apples, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        assert greenApples.size() == 2;
        for (Apple apple : greenApples) {
            System.out.println("apple color: " + apple.getColor() + ", weight: " + apple.getWeight());
        }
    }

    @Test
    public void findAppleByFilterWithLambda() {
        Apple apple1 = new Apple("red", 123L);
        Apple apple2 = new Apple("green", 89L);
        Apple apple3 = new Apple("red", 76L);
        Apple apple4 = new Apple("green", 59L);
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        List<Apple> greenApples = AppleManager.findApple(apples, apple -> "green".equals(apple.getColor()));
        assert greenApples.size() == 2;
        for (Apple apple : greenApples) {
            System.out.println("apple color: " + apple.getColor() + ", weight: " + apple.getWeight());
        }
    }

    @Test
    public void concurrentHashMap() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.remove(null);
    }

    @Test
    public void lambdaExpression() {
        Apple apple1 = new Apple("red", 123L);
        Apple apple2 = new Apple("green", 89L);
        Apple apple3 = new Apple("red", 76L);
        Apple apple4 = new Apple("green", 59L);
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        Comparator<Apple> bycolor = Comparator.comparing(Apple::getColor);

        apples.sort(bycolor);
        for (Apple apple : apples) {
            System.out.println("apple color: " + apple.getColor() + ", weight: " + apple.getWeight());
        }
    }
}