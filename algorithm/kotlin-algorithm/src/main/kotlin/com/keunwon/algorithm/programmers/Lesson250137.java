package com.keunwon.algorithm.programmers;

public class Lesson250137 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        var life = health;
        var step = 0;
        var aIndex = 0;

        for (var i = 1; i <= attacks[attacks.length - 1][0]; i++) {
            if (i != attacks[aIndex][0]) {
                life += bandage[1];
                ++step;

                if (step == bandage[0]) {
                    life += bandage[2];
                    step = 0;
                }
                if (life > health) life = health;
            } else {
                step = 0;
                life -= attacks[aIndex++][1];

                if (life <= 0) return -1;
            }
        }
        return life;
    }
}
