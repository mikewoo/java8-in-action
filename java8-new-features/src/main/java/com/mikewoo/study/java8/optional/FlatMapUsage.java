package com.mikewoo.study.java8.optional;

import com.mikewoo.study.java8.domain.Address;
import com.mikewoo.study.java8.domain.Company;
import com.mikewoo.study.java8.domain.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * map: 对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
 * flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中；
 * @author Eric Gui
 * @date 2018/7/31
 */
public class FlatMapUsage {

    public static void main(String[] args) {
        Optional.ofNullable(getUserAddressByOptioinal(null)).ifPresent(System.out::println);

        // 对给定单词列表 ["Hello","World"]，返回列表["H","e","l","o","W","r","d"]
        // 使用flatMap方法的效果是，各个数组并不是分别映射一个流，而是映射成流的内容，所有使用map(Array::stream)时生成的单个流被合并起来，即扁平化为一个流。
        String[] words = new String[] {"Hello", "World"};
        List<String> collect = Arrays.stream(words)
                .map(world -> world.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static String getUserAddressByOptioinal(Company company) {
        return Optional.ofNullable(company)
                .flatMap(Company::getUser)
                .flatMap(User::getAddress)
                .map(Address::getName)
                .orElse("unknown");
    }
}
