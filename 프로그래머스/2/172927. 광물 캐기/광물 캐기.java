class Solution {
    private String[] minerals;
    private int[][] fee = new int[][]{
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1},
    };
    private int min = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        this.minerals = minerals;
        
        var sum = 0;
        for (var p : picks) {
            sum += p;
        }
        
        dfs(picks, new int[sum], 0);
        return min;
    }
    
    private void dfs(int[] picks, int[] tools, int depth) {
        if (depth == tools.length) {
            var mIdx = 0;
            var total = 0;
            
            lo:
            for (var tool : tools) {
                for (var i = 0; i < 5; i++) {
                    var id = switch(minerals[mIdx]) {
                        case "diamond" -> 0;
                        case "iron" -> 1;
                        case "stone" -> 2;
                        default -> -1;
                    };
                    total += fee[tool][id];
                    
                    if (++mIdx == minerals.length) break lo;
                }
            }
            
            min = Math.min(min, total);
            return;
        }
        
        for (var i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                --picks[i];
                tools[depth] = i;
                dfs(picks, tools, depth + 1);
                ++picks[i];
            }
        }
    }
}