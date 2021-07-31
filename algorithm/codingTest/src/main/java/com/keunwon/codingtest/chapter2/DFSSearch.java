package com.keunwon.codingtest.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFSSearch {

    public List<String> dfsFunc(Map<String, List<String>> graph, String startNoe) {
        List<String> visitedList = new ArrayList<>();
        Stack<String> needStack = new Stack<>();

        needStack.push(startNoe);

        while (needStack.size() > 0) {
            final String node = needStack.pop();

            if (!visitedList.contains(node)) {
                visitedList.add(node);
                needStack.addAll(graph.get(node));
            }
        }

        return visitedList;
    }
}
