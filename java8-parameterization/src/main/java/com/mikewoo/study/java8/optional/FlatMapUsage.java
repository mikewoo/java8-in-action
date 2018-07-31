package com.mikewoo.study.java8.optional;

import com.mikewoo.study.java8.domain.Address;
import com.mikewoo.study.java8.domain.Company;
import com.mikewoo.study.java8.domain.User;

import java.util.Optional;

/**
 * @author Eric Gui
 * @date 2018/7/31
 */
public class FlatMapUsage {

    public static void main(String[] args) {
        Optional.ofNullable(getUserAddressByOptioinal(null)).ifPresent(System.out::println);

    }

    private static String getUserAddressByOptioinal(Company company) {
        return Optional.ofNullable(company)
                .flatMap(Company::getUser)
                .flatMap(User::getAddress)
                .map(Address::getName)
                .orElse("unknown");
    }
}
