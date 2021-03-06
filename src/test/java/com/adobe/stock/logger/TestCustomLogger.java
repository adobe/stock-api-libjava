/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.logger;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestCustomLogger extends TestListenerAdapter {

    private static final String ANSI_BOLD_RED = "\033[31;1m";
    private static final String ANSI_RESET = "\033[0m";
    private static final String ANSI_BOLD_GREEN = "\033[32;1m";
    private static final String ANSI_BOLD_YELLOW = "\033[33;1m";

    @Override
    public void onTestFailure(ITestResult tr) {
        log(ANSI_BOLD_RED + "\t\u2717 " + this.getTestName(tr) + ANSI_RESET);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log(ANSI_BOLD_YELLOW + "\t- " + this.getTestName(tr) + ANSI_RESET);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log(ANSI_BOLD_GREEN + "\t\u2713 " + this.getTestName(tr) + ANSI_RESET);
    }

    private String getTestName(ITestResult tr) {
        ITestNGMethod method = tr.getMethod();
        return method.getMethodName();
    }

    private void log(String string) {
        System.out.println(string);
    }

}