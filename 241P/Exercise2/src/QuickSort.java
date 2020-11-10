public class QuickSort {


    public String[] QuickSort(String[] array) {
        Traverse(array, 0, array.length - 1);
        return array;
    }

    public void Traverse(String[] array, int start, int end) {
        if (start < end) {
            String tmp = array[start];
            int i = start;
            int j = end;

            while (i < j) {
                // Find the smaller
                while (i < j && array[j].compareTo(tmp) > 0)
                    j--;
                if (i < j)
                    array[i++] = array[j];
                // Find the bigger
                while (i < j && array[i].compareTo(tmp) < 0)
                    i++;
                if (i < j)
                    array[j--] = array[i];
            }
            // Swap
            array[i] = tmp;
            Traverse(array, start, i - 1);
            Traverse(array, j + 1, end);
        }
    }
}
