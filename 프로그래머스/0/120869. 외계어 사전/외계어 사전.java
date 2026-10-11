class Solution {
    public int solution(String[] spell, String[] dic) {
        var str = "";
        for (var s : spell) {
            str += s;
        }

        var targetHash = generateHash(str);
        for (var d : dic) {
            var dicHash = generateHash(d);
            if (targetHash == dicHash) return 1;
        }
        return 2;
    }

    private int generateHash(String str) {
        var alphabet = new boolean[26];

        for (var i = 0; i < str.length(); i++) {
            var c = str.charAt(i) - 'a';
            alphabet[c] = true;
        }

        var hash = 1;
        for (var i = 0; i < 26; i++) {
            hash *= 31;
            if (alphabet[i]) hash += (i + 1);
        }
        return hash;
    }
}