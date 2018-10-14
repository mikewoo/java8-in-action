package com.mikewoo.study.java8.lambda;

/**
 * @author Eric Gui
 * @date 2018/7/23
 */
@FunctionalInterface
public interface AppleConstructorFunction<T, U, K, R> {

    R apply(T t, U u, K k);
}
