package search;

public class LinearSearcher implements Searcher {

    @Override
    public int search(int[] arr, int val) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == val)
                return i;
        }
        return -1;
    }
}


