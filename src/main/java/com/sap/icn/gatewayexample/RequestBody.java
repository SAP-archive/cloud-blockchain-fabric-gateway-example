/*
 * Copyright 2020 SAP All Rights Reserved.
 */

package com.sap.icn.gatewayexample;

public class RequestBody {
    private String function;
    private String[] arguments;

    public RequestBody() {
    }

    public RequestBody(String function, String[] arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
