class Solution {
    public int solution(String[] babbling) {
        var res = 0;
        for (var b : babbling) {
            var str = b.replace("ayaaya", "#")
                    .replace("yeye", "#")
                    .replace("woowoo", "#")
                    .replace("mama", "#")
                    .replace("aya", " ")
                    .replace("ye", " ")
                    .replace("woo", " ")
                    .replace("ma", " ");
            
            if (str.isBlank()) ++res;
        }
        return res;
    }
}