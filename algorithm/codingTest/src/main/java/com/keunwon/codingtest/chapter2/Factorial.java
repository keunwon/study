package com.keunwon.codingtest.chapter2;

import java.util.List;

public class Factorial {

    public int factorialFunc(int n) {
        if (n > 1) {
            return n * factorialFunc(n - 1);
        }
        return 1;
    }

    public int sum(List<Integer> dataList) {
        if (dataList.size() <= 0) { return 0; }
        int data = sum(dataList.subList(1, dataList.size()));
        return dataList.get(0) + data;
    }

    public int recursive(int data) {
        if (data == 1) { return 1; }
        else if (data == 2) { return 2; }
        else if (data == 3) { return 4; }
        return recursive(data - 1) + recursive(data - 2) + recursive(data - 3);
    }

    public int fibonacci(int data) {
        if (data <= 1) {
            return data;
        }
        return fibonacci(data - 1) + fibonacci(data - 2);
    }
}
