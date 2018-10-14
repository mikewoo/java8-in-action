package com.mikewoo.study.java8.lambda;

import com.mikewoo.study.java8.domain.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * {@link Predicate} {@link Consumer} {@link Function} {@link Supplier} 用法
 * @author Eric Gui
 * @date 2018/7/19 10:25
 */
public class LambdaUsage {

    public static List<Apple> filter(List<Apple> sources, Predicate<Apple> predicate) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : sources) {
            if (predicate.test(apple)) {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> filterByWeight(List<Apple> sources, LongPredicate predicate) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : sources) {
            if (predicate.test(apple.getWeight())) {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> filterByColorAndWeight(List<Apple> sources, BiPredicate<String, Long> predicate) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : sources) {
            if (predicate.test(apple.getColor(), apple.getWeight())) {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> consumerApple(List<Apple> sources, LongPredicate predicate, Consumer<Apple> consumer) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : sources) {
            if (predicate.test(apple.getWeight())) {
                consumer.accept(apple);
            } else {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> cosumerAppleByColorAndWeight(List<Apple> sources, BiPredicate<String, Long> predicate, BiConsumer<String, Long> consumer) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : sources) {
            if (predicate.test(apple.getColor(), apple.getWeight())) {
                consumer.accept(apple.getColor(), apple.getWeight());
            } else {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static Apple functionTenMultipleAppleWeight(Apple apple, Function<Apple, Apple> function) {
        return function.apply(apple);
    }

    public static Apple biFunctionCreateApple(String color, Long weight, BiFunction<String, Long, Apple> function) {
        return function.apply(color, weight);
    }

    public static Apple supplierCreateApple(Supplier<Apple> supplier) {
        return supplier.get();
    }

    public static String supplierAppleColor(Supplier<String> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        List<Apple> sources = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

        System.out.println("==========byColor(color:green)==========");
        List<Apple> byColor = filter(sources, apple -> apple.getColor().equals("green"));
        System.out.println(byColor);


        System.out.println("\n==========byWeight(weight > 120)==========");
        List<Apple> byWeight = filterByWeight(sources, weight -> weight > 120);
        System.out.println(byWeight);

        System.out.println("\n==========byColorAndWeight(color:green && weight > 100)==========");
        List<Apple> byColorAndWeight = filterByColorAndWeight(sources,
                (color, weight) -> color.equals("green") && weight > 100);
        System.out.println(byColorAndWeight);

        System.out.println("\n==========consumerApple(weight < 150)==========");
        List<Apple> consumerApple = consumerApple(sources,
                weight -> weight < 150,
                apple -> System.out.println("consumer apple: " + apple));
        System.out.println(consumerApple);

        System.out.println("\n==========cosumerAppleByColorAndWeight(color:green && weight < 150)==========");
        List<Apple> cosumerAppleByColorAndWeight = cosumerAppleByColorAndWeight(sources,
                (color, weight) -> color.equals("green") && weight < 150,
                (color, weight) -> System.out.println("consumer apple: color=" + color + ", weight=" + weight));
        System.out.println(cosumerAppleByColorAndWeight);

        System.out.println("\n==========functionTenMultipleAppleWeight(weight = weight * 10)==========");
        Apple result = functionTenMultipleAppleWeight(sources.get(0), apple -> {
            apple.setWeight(apple.getWeight() * 10);
            return apple;
        });
        System.out.println(result);

        System.out.println("\n==========biFunctionCreateApple==========");
        Apple apple = biFunctionCreateApple("yellow", 200L, (color, weight) -> new Apple(color, weight));
        System.out.println("created new apple: " + apple);

        System.out.println("\n==========supplierCreateApple==========");
        Apple greenApple = supplierCreateApple(() -> new Apple("green", 130));
        System.out.println("created new apple: " + greenApple);

        String appleColor = supplierAppleColor(() -> greenApple.getColor());
        System.out.println("color: " + appleColor);

        Apple empty = supplierCreateApple(Apple::new);
        System.out.println("created new apple: " + empty);

    }

}
