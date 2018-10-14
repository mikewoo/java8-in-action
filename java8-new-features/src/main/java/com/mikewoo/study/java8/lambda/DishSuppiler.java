package com.mikewoo.study.java8.lambda;

import com.mikewoo.study.java8.domain.Dish;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class DishSuppiler implements Supplier<Dish> {

    private int calories = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public Dish get() {
        calories = random.nextInt(500);
        return new Dish("Name->" + calories, false, calories, Dish.Type.MEAT);
    }
}
