import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        var countMap = new HashMap<String, Integer>(clothes.length);
        for (var clothe : clothes) {
            var key = clothe[1];
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }

        var values = countMap.values().stream().toList();
        var res = 1;

        for (var value : values) {
            res *= (value + 1);
        }
        return res - 1;
    }
}