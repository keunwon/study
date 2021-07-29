package com.keunwon.codingtest.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap {
    public List<Integer> heapArray = new ArrayList<>();

    public Heap(int data) {
        heapArray.add(null);
        heapArray.add(data);
    }

    public boolean insert(int data) {
        if (heapArray == null) {
            heapArray = new ArrayList<>();
            heapArray.add(null);
            heapArray.add(data);
            return true;
        }

        heapArray.add(data);
        int insertedIdx = heapArray.size() - 1;
        int parentIdx = 0;

        while (moveUp(insertedIdx)) {
            parentIdx = insertedIdx / 2;
            Collections.swap(heapArray, insertedIdx, parentIdx);
            insertedIdx = parentIdx;
        }

        return true;
    }

    public Integer pop() {
        if (this.heapArray == null) { return null; }

        final int result = heapArray.get(1);
        heapArray.set(1, heapArray.get(heapArray.size() - 1));
        heapArray.remove(heapArray.size() - 1);

        int leftChildIdx;
        int rightChildIdx;
        int poppedIdx = 1;
        while (moveDown(poppedIdx)) {
            leftChildIdx = poppedIdx * 2;
            rightChildIdx = poppedIdx * 2 + 1;

            if (rightChildIdx >= heapArray.size()) {
                if (heapArray.get(poppedIdx) < heapArray.get(leftChildIdx)) {
                    Collections.swap(heapArray, poppedIdx, leftChildIdx);
                    poppedIdx = leftChildIdx;
                    continue;
                }

                if (heapArray.get(leftChildIdx) > heapArray.get(rightChildIdx)) {
                    if (heapArray.get(poppedIdx) < heapArray.get(leftChildIdx)) {
                        Collections.swap(heapArray, poppedIdx, leftChildIdx);
                        poppedIdx = leftChildIdx;
                    }
                } else {
                    if (heapArray.get(poppedIdx) < heapArray.get(rightChildIdx)) {
                        Collections.swap(heapArray, poppedIdx, rightChildIdx);
                        poppedIdx = rightChildIdx;
                    }
                }
            }
        }

        return result;
    }

    private boolean moveDown(int poppedIdx) {
        final int leftChildIdx = poppedIdx * 2;
        final int rightChildIdx = poppedIdx * 2 + 1;

        if (leftChildIdx >= heapArray.size()) {
            return false;
        }

        if (rightChildIdx >= heapArray.size()) {
            return heapArray.get(poppedIdx) < heapArray.get(leftChildIdx);
        }

        if (heapArray.get(leftChildIdx) > heapArray.get(rightChildIdx)) {
            return heapArray.get(poppedIdx) < heapArray.get(leftChildIdx);
        }
        return heapArray.get(poppedIdx) < heapArray.get(rightChildIdx);
    }


    private boolean moveUp(int index) {
        if (index <= 1) {
            return false;
        }

        final int parentIdx = index / 2;
        return heapArray.get(index) > heapArray.get(parentIdx);
    }
}
