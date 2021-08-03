package com.keunwon.codingtest.chapter2;

import java.util.List;

public class NQueen {

    public void dfcFunc(int n, int currentRow, List<Integer> currentCandidate) {
        if (n == currentRow) {
            System.out.println(currentCandidate);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isAvailable(currentCandidate, i)) {
                currentCandidate.add(i);
                dfcFunc(n, currentRow + 1, currentCandidate);
                currentCandidate.remove(currentCandidate.size() - 1);
            }
        }
    }

    private boolean isAvailable(List<Integer> candidate, int currentCol) {
        final int currentRow = candidate.size();

        for (int i = 0; i < currentRow; i++) {
            if (candidate.get(i) == currentCol) { return false; }

            int col = Math.abs(candidate.get(i) - currentCol);
            int row = currentRow - i;
            if (col == row) { return false; }
        }
        return true;
    }
}
