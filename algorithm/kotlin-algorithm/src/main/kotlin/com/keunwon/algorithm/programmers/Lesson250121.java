package com.keunwon.algorithm.programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lesson250121 {
    public List<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        var map = Map.of(
                "code", 0,
                "date", 1,
                "maximum", 2,
                "remain", 3
        );
        return Arrays.stream(data)
                .filter(d -> d[map.get(ext)] <= val_ext)
                .sorted(Comparator.comparingInt(d -> d[map.get(sort_by)]))
                .collect(Collectors.toList());
    }
}
