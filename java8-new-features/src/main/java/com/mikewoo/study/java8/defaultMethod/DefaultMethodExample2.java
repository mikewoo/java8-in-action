package com.mikewoo.study.java8.defaultMethod;

/**
 * @author Eric Gui
 * @date 2018/8/3
 */
public class DefaultMethodExample2 {

    public static void main(String[] args) {
        A c = new C();
        c.hello();
    }

    interface A {
        default void hello() {
            System.out.println("== interface A:hello ==");
        }
    }

    interface B extends A {

        @Override
        default void hello() {
            System.out.println("== interface B:hello ==");
        }
    }

    static class C implements B, A {

    }

}
