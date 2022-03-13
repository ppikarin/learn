package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Sort {

    public static void main(String[] args) {

        Sorter sorter = new BubbleSorter();

        long start = System.currentTimeMillis();

        int[] array = IntStream
                .generate(() -> new Random().nextInt(1000))
                .limit(1000)
                .toArray();

        sorter.sort(array);

        long end = System.currentTimeMillis();

        System.out.println(Arrays.asList(array));
        System.out.println(end - start);

    }

}
