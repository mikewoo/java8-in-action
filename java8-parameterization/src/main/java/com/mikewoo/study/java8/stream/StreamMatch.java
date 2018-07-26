package com.mikewoo.study.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class StreamMatch {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1, 8, 9, 6, 1);

        // allMatch()
        boolean matched = list.stream().allMatch(i -> i > 0);
        assert matched : "not all match";

        // anyMatch()
        matched = list.stream().anyMatch(i -> i > 6);
        assert matched : "not any match";

        // noneMatch()
        matched = list.stream().noneMatch(i -> i < 0);
        assert matched : "none match";
    }
}
