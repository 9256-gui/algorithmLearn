package ght.myAnswer;

import java.util.Arrays;

public class Code_973 {
    public int[][] kClosest(int[][] points, int K) {
        netherlandsFlag(points, 0, points.length-1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public static void netherlandsFlag(int[][] arr, int L, int R,int K) {
        int pivotId = L + (int) (Math.random() * (R - L + 1));
        int pivot = arr[pivotId][0] * arr[pivotId][0] + arr[pivotId][1] * arr[pivotId][1];
        swap(arr, R, pivotId);
        int i = L - 1;
        for (int j = L; j < R; ++j) {
            int dist = arr[j][0] * arr[j][0] + arr[j][1] * arr[j][1];
            if (dist <= pivot) {
                ++i;
                swap(arr, i, j);
            }
        }
        swap(arr, ++i, R);
        // [left, i-1] 都小于等于 pivot, [i+1, right] 都大于 pivot
        if (K < i - L + 1) {
            netherlandsFlag(arr, L, i - 1, K);
        } else if (K > i - L + 1) {
            netherlandsFlag(arr, i + 1, R, K - (i - L + 1));
        }
    }

    public static void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
