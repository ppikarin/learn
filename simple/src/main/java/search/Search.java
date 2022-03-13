package search;

import sort.BubbleSorter;
import sort.QuickSorter;
import sort.Sorter;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/*
3385ms - create array
7ms - linear search
8595ms - quick sort
0ms - binary search
 */

public class Search {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        Searcher searcher;
        Integer result;

        int[] arr = IntStream
                .generate(() -> new Random().nextInt(10_000_000))
                .limit(100_000_000)
                .toArray();

        System.out.println(System.currentTimeMillis() - start + "ms - create array");

//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        start = System.currentTimeMillis();
        searcher = new LinearSearcher();
        result = searcher.search(arr, 500);
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start + "ms - linear search");

        start = System.currentTimeMillis();
        Sorter sorter = new QuickSorter();
        sorter.sort(arr);
        System.out.println(System.currentTimeMillis() - start + "ms - quick sort");

        start = System.currentTimeMillis();
        searcher = new BinarySearcher();
        result = searcher.search(arr, 500);
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start + "ms - binary search");


    }

}
