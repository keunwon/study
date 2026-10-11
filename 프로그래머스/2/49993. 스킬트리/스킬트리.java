class Solution {
    public int solution(String skill, String[] skill_trees) {
        var alphabet = new int[26];
        for (var i = 0; i < skill.length(); i++) {
            var c = skill.charAt(i) - 'A';
            alphabet[c] = i + 1;
        }

        var res = 0;
        for (var st : skill_trees) {
            var valid = true;
            var sIdx = 0;
            
            for (var i = 0; i < st.length(); i++) {
                var n1 = alphabet[skill.charAt(sIdx) - 'A'];
                var n2 = alphabet[st.charAt(i) - 'A'];

                if (n1 == n2) {
                    ++sIdx;
                    if (sIdx == skill.length()) break;
                } else if (n1 < n2) {
                    valid = false;
                    break;
                }
            }
            if (valid) ++res;
        }
        return res;
   }
}