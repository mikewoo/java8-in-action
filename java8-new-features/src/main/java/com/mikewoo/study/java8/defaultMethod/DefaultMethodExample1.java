package com.mikewoo.study.java8.defaultMethod;

/**
 * @author Eric Gui
 * @date 2018/8/3
 */
public class DefaultMethodExample1 {

    public static void main(String[] args) {
        Counter counter = () -> 100;
        System.out.println(counter.count());
        System.out.println(counter.isEmpty());
    }

    private interface Counter {

        int count();

        default boolean isEmpty() {
            return count() == 0;
        }
    }
}
