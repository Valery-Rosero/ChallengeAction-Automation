package com.choucair.utils;

import io.cucumber.java.After;

public class Hooks {

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}