import java.util.Arrays;

class Solution {
    public String[] solution(String myStr) {
        var list = Arrays.stream(myStr.split("[abc]")).filter(s -> !s.isBlank()).toArray(String[]::new);
        return list.length == 0 ? new String[] {"EMPTY"} : list;
    }
}