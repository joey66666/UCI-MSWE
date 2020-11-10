public class HeapSort {

    public String[] HeapSort(String[] array) {
        // Build heap from the last non-leaf node
        for (int i = array.length / 2 - 1; i >= 0; i -= 1) {
            BuildHeap(array, i, array.length);
        }

        for (int j = array.length - 1; j >= 0; j -= 1) {
            // Swap(0, last)
            String tmp = array[j];
            array[j] = array[0];
            array[0] = tmp;
            BuildHeap(array, 0, j);
        }
        return array;

    }

    public void BuildHeap(String[] array, int index, int length) {
        // Parent: (i - 1) / 2
        // Left: 2 * i + 1
        // Right: 2 * i + 2

        String tmp = array[index];
        for (int i = 2 * index + 1; i < length; i = 2 * i + 1) {
            // Compare(left, right)
            if (i + 1 < length && (array[i].compareTo(array[i + 1]) < 0)) {
                i += 1;
            }
            // Big child bigger than parent
            if (array[i].compareTo(tmp) > 0) {
                array[index] = array[i];
                index = i;
            }
            array[index] = tmp;
        }
    }
}
