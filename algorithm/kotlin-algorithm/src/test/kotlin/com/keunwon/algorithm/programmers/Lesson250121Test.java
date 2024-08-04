package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

class Lesson250121Test {
    @Test
    void case_1() {
        var data = new int[][]{
                {1, 20300104, 100, 80},
                {2, 20300804, 847, 37},
                {3, 20300401, 10, 8}
        };
        var ext = "date";
        var val_ext = 20300501;
        var sort_by = "remain";


        var actual = new Lesson250121().solution(data, ext, val_ext, sort_by);

    }
}
