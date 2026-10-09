class Solution {
    public int solution(int[] ingredient) {
        var top = -1;
        var res = 0;

        for (var i = 0; i < ingredient.length; i++) {
            ingredient[++top] = ingredient[i];

            if (top >= 3
                    && ingredient[top] == 1
                    && ingredient[top - 1] == 3
                    && ingredient[top - 2] == 2
                    && ingredient[top - 3] == 1) {
                top -= 4;
                ++res;
            }
        }
        return res;
    }
}