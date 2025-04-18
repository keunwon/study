package algorithm.programmers;

import java.util.HashSet;

public class Lesson12981 {
    public int[] solution(int n, String[] words) {
        var answer = new int[2];
        var set = new HashSet<String>();
        var prev = String.valueOf(words[0].charAt(0));

        for (int i = 0; i < words.length; i++) {
            var word = words[i];

            if (set.contains(word) || prev.charAt(prev.length() - 1) != word.charAt(0)) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
            set.add(word);
            prev = word;
        }
        return answer;
    }
}
