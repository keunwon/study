package com.keunwon.codingtest.chapter2;

public class MergeSort2 {
    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {

            int middle = (l + r) / 2;
            mergeSort(arr, l, middle);
            mergeSort(arr, middle + 1, r);

            merge(arr, l, middle, r);
        }
    }

    private void merge(int[] arr, int l, int middle, int r) {
        int[] sortArr = new int[100];
        int i = l;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= r) {
            if (arr[i] <= arr[j]) {
                sortArr[k] = arr[i];
                i++;
            } else {
                sortArr[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            sortArr[k] = arr[i];
            i++;
            k++;
        }

        while (j <= r) {
            sortArr[k] = arr[j];
            j++;
            k++;
        }

        k--;
        while (k >= 0) {
            arr[l + k] = sortArr[k];
            k--;
        }
    }
}
