public class SelectionSort {
    public String[] SelectionSort(String[] array) {
        for (int i = 0; i < array.length; i++) {
            // Move and Select
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // Swap(array[minIndex], array[i])
            String tmp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = tmp;
        }
        return array;
    }
}
