public class MergeSort {

    public String[] MergeSort(String[] array) {
        MergeSortHelper(array, 0, array.length - 1);
        return array;
    }

    public void MergeSortHelper(String[] array, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            // Divide
            MergeSortHelper(array, start, mid);
            MergeSortHelper(array, mid + 1, end);
            // Merge
            Merge(array, start, mid, end);
        }
    }

    public void Merge(String[] array, int start, int mid, int end) {
        String[] tmp = new String[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        // Copy in order
        while (i <= mid && j <= end) {
            if (array[i].compareTo(array[j]) < 0) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }
        // Copy the left
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= end) {
            tmp[k++] = array[j++];
        }
        // Copy back
        for (int l = 0; l < tmp.length; l++) {
            array[start + l] = tmp[l];
        }
    }
}
