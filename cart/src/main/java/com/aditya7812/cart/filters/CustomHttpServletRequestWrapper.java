package com.aditya7812.cart.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String> customHeaders = new HashMap<>();

    public CustomHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void addHeader(String name, String value) {
        customHeaders.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        return customHeaders.getOrDefault(name, super.getHeader(name));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return super.getParameterMap();
    }
}
