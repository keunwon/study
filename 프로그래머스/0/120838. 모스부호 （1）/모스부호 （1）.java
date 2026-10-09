class Solution {
    public String solution(String letter) {
        var tokens = letter.split(" ");
        var res = new char[tokens.length];
        var arr = new String[] {
            ".-", "-...", "-.-.", "-..", ".", "..-.",
            "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--.."
        };

        for (var i = 0; i < tokens.length; i++) {
            var token = tokens[i];
            for (var j = 0; j < arr.length; j++) {
                if (token.equals(arr[j])) {
                    res[i] = (char) ('a' + j);
                }
            }
        }
        return new String(res);
    }
}