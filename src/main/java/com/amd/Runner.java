package com.amd;

import com.amd.service.Action;
import com.amd.service.impl.ActionImpl;

public class Runner {
    public static void main(String[] args) {
        Action action = new ActionImpl();
        action.Task1();
        action.Task2();
        action.Task3();
    }
}