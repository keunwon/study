package com.keunwon.algorithm.programmers;

public class Lesson49993 {
    public int solution(String skill, String[] skill_trees) {
        var answer = 0;
        for (var skillTree : skill_trees) {
            var tmp = skillTree.replaceAll("[^" + skill + "]", "");
            if (skill.startsWith(tmp)) ++answer;
        }
        return answer;
    }
}
