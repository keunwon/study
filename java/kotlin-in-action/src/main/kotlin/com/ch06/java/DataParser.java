package com.ch06.java;

import java.util.List;

public interface DataParser<T> {
    void parseData(String input, List<T> output, List<String> errors);
}
