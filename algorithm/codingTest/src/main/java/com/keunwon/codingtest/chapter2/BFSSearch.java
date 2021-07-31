package com.keunwon.codingtest.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BFSSearch {

    public List<String> bfsFunc(Map<String, List<String>> graph, String startNode) {
        List<String> visitedList = new ArrayList<>();
        List<String> needList = new ArrayList<>();

        needList.add(startNode);

        while (needList.size() > 0) {
            final String node = needList.remove(0);

            if (!visitedList.contains(node)) {
                visitedList.add(node);
                needList.addAll(graph.get(node));
            }
        }

        return visitedList;
    }
}
