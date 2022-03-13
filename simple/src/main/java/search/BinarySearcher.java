package search;

import java.util.Arrays;

public class BinarySearcher implements Searcher {

    public int search(int[] arr, int val) {
        return search(arr, val, 0, arr.length - 1);
    }

    public int search(int[] arr, int val, int start, int end) {

        if (end < start) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (arr[mid] == val)
            return mid;

        if (arr[mid] > val)
            return search(arr, val, start, mid - 1);
        else
            return search(arr, val, mid + 1, end);

        // 0 1 2 3 4 5 6 7 8
        // 9 / 2 = 4
        // 0..3, 5..8

    }
}


