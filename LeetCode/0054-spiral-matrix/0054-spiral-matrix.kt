class Solution {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val res = mutableListOf<Int>()
        var r1 = 0
        var c1 = 0
        var r2 = matrix.lastIndex
        var c2 = matrix[0].lastIndex
        
        while (r1 <= r2 && c1 <= c2) {
            for (c in c1..c2) {
                res.add(matrix[r1][c])
            }
            ++r1
            
            for (r in r1..r2) {
                res.add(matrix[r][c2])
            }
            --c2
            
            if (r1 <= r2 && c1 <= c2) {
                for (c in c2 downTo c1) {
                    res.add(matrix[r2][c])
                }
                --r2
                
                for (r in r2 downTo r1) {
                    res.add(matrix[r][c1])
                }
                ++c1
            }
        }
        return res
    }
}