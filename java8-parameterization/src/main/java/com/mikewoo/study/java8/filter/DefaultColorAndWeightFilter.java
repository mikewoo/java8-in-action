package com.mikewoo.study.java8.filter;

import com.mikewoo.study.java8.Apple;

/**
 * @author : Eric Gui
 * @date 2018/7/16 12:02
 */
public class DefaultColorAndWeightFilter implements AppleFilter {

    private String color;

    private Long weight;

    public DefaultColorAndWeightFilter(String color, Long weight) {
        this.color = color;
        this.weight = weight;
    }

    @Override
    public boolean filter(Apple apple) {
        if (this.color.equals(apple.getColor()) && apple.getWeight() >= this.weight) {
            return true;
        }
        return false;
    }
}
