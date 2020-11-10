import java.io.*;
import java.nio.channels.SelectableChannel;
import java.util.Arrays;

public class test {
    public static String[] readWords(String path) {
        String match = "[a-zA-Z0-9]+";
        StringBuilder sb = new StringBuilder();
        File f = new File(path);
        Reader reader = null;

        try {
            reader = new InputStreamReader(new FileInputStream(f));
            int tmp = 0;
            while ((tmp = reader.read()) != -1) {
                if (!((char) tmp + "").matches(match)) {
                    tmp = ' ';
                }
                sb.append((char) tmp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sbString = sb.toString();
        String[] words = sbString.replaceAll("\\s+", " ").split(" ");
        return words;
    }


    public void saveCsv(long[][] data, String fileName) {
        File csvFile = new File(fileName);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
            for (int i = 0; i < data.length; i++) {
                writer.newLine();
                writer.write(String.valueOf(data[i][0]) + "," + String.valueOf(data[i][1]));
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTxt(String[] data, String fileName) {
        String filePath = "./res/";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName + ".txt"));
            writer.newLine();
            writer.write(fileName);
            writer.newLine();
            writer.write(Arrays.toString(data));
            writer.close();
            System.out.println("Saved to txt:" + filePath + fileName + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void isTest(String[] array, int i) {
//        System.out.println("Insertion Sort " + i);
        InsertionSort is = new InsertionSort();
        long curTime = System.nanoTime();
        String[] isArray = is.InsertionSort(array);
        long takeTime = System.nanoTime() - curTime;
        System.out.println(takeTime);
//        saveTxt(isArray, "InsertionSort");
        // release manually
        isArray = new String[0];
    }

    public static void ssTest(String[] array, int i) {
//        System.out.println("Selection Sort " + i);
        SelectionSort ss = new SelectionSort();
        long curTime = System.nanoTime();
        String[] ssArray = ss.SelectionSort(array);
        long takeTime = System.nanoTime() - curTime;
        System.out.println(takeTime);
//        saveTxt(ssArray, "SelectionSort");
        // release manually
        ssArray = new String[0];

    }

    public static void msTest(String[] array, int i) {
//        System.out.println("Merge Sort " + i);
        MergeSort ms = new MergeSort();
        long curTime = System.nanoTime();
        String[] msArray = ms.MergeSort(array);
        long takeTime = System.nanoTime() - curTime;
        System.out.println(takeTime);
//        saveTxt(msArray, "MergeSort");
        // release manually
        msArray = new String[0];
    }

    public static void qsTest(String[] array, int i) {
//        System.out.println("Quick Sort " + i);
        QuickSort qs = new QuickSort();
        long curTime = System.nanoTime();
        String[] qsArray = qs.QuickSort(array);
        long takeTime = System.nanoTime() - curTime;
        System.out.println(takeTime);
//        saveTxt(qsArray, "QuickSort");
        // release manually
        qsArray = new String[0];
    }

    public static void hsTest(String[] array, int i) {
//        System.out.println("Heap Sort " + i);
        HeapSort hs = new HeapSort();
        long curTime = System.nanoTime();
        String[] hsArray = hs.HeapSort(array);
        long takeTime = System.nanoTime() - curTime;
        System.out.println(takeTime);
//        saveTxt(hsArray, "HeapSort");
        // release manually
        hsArray = new String[0];
    }

    public static void main(String[] args) {
//        String[] tarray = new String[]{"hb", "ab", "au", "hy", "abc", "ha"};
        String path = "./pride-and-prejudice.txt";


        System.out.println("Merge Sort");
        for (int i = 0; i < 10; i++) {
            String[] array = readWords(path);
            msTest(array, i);
            array = new String[0];
        }
        System.out.println("Quick Sort");
        for (int i = 0; i < 10; i++) {
            String[] array = readWords(path);
            qsTest(array, i);
            array = new String[0];
        }
        System.out.println("Heap Sort");
        for (int i = 0; i < 10; i++) {
            String[] array = readWords(path);
            hsTest(array, i);
            array = new String[0];
        }
        System.out.println("Insertion Sort");
        for (int i = 0; i < 10; i++) {
            String[] array = readWords(path);
            isTest(array, i);
            array = new String[0];
        }
        System.out.println("Selection Sort");
        for (int i = 0; i < 10; i++) {
            String[] array = readWords(path);
            ssTest(array, i);
            array = new String[0];
        }
    }
}
