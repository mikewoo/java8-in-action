package com.mikewoo.study.java8.domain;

import java.util.Optional;

/**
 * @author Eric Gui
 * @date 2018/7/31
 */
public class Company {

    private Optional<User> user;

    public Optional<User> getUser() {
        return user;
    }
}
