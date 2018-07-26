package com.mikewoo.study.java8.filter;

import com.mikewoo.study.java8.domain.Apple;

/**
 * @author : Eric Gui
 * @date 2018/7/16 11:52
 */
@FunctionalInterface
public interface AppleFilter {

    boolean filter(Apple apple);
}
