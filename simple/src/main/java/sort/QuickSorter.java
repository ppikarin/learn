package sort;

public class QuickSorter implements Sorter {


    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length -1);
    }

    public void sort(int[] array, int start, int end) {

        if (start >= end) {
            return;
        }

        // partition
        var boundary = partition(array, start, end);

        // sort left
        sort(array, start, boundary - 1);

        // sort right
        sort(array, boundary + 1, end);
    }

    private int partition(int[] array, int start, int end) {

        // pivot is the last element
        var pivot = array[end];

        // assumes left partition is empty
        // right starts from index 0
        var boundary = start - 1;

        for (var i = start; i <= end; i++) {
            if (array[i] <= pivot) {
                swap(array, i, ++boundary);
            }
        }

        return boundary;
    }

}
