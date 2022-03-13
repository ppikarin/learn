package sort;

public interface Sorter {

    void sort(int[] arr);

    default void swap(int[] array, int index1, int index2) {
        var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


}
