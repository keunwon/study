import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    private List<Integer> numbers;

    public int solution(int[] cards) {
        this.numbers = new ArrayList<>();

        for (var i = 1; i <= cards.length; i++) {
            if (cards[i - 1] > 0) {
                dfs(cards, i, 0);
            }
        }

        if (numbers.size() < 2) {
            return 0;
        } else {
            numbers.sort(Comparator.reverseOrder());
            return numbers.get(0) * numbers.get(1);
        }
    }

    private void dfs(int[] cards, int cur, int count) {
        if (cards[cur - 1] == 0) {
            numbers.add(count);
            return;
        }

        var x = cards[cur - 1];
        cards[cur - 1] = 0;
        dfs(cards, x, count + 1);
    }
}