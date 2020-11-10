public class InsertionSort {
    public String[] InsertionSort(String[] array){
        for (int i = 1; i < array.length; i++) {
            String tmp = array[i];
            int j = i - 1;

            // Move and Search
            while (j >= 0 && (tmp.compareTo(array[j]) < 0)) {
                array[j + 1] = array[j];
                j -= 1;
            }
            // Insert
            array[j + 1] = tmp;
        }
        return array;
    }
}
