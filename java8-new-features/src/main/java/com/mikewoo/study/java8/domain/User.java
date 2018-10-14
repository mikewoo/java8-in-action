package com.mikewoo.study.java8.domain;

import java.util.Optional;

/**
 * @author Eric Gui
 * @date 2018/7/31
 */
public class User {

    private Optional<Address> address;

    public Optional<Address> getAddress() {
        return address;
    }
}
