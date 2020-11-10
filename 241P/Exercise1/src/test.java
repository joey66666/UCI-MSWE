import java.io.*;
import java.util.Arrays;

public class test {

    public String[] readWords(String path) {
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

    String path1 = "./words-shuffled.txt";
    String path2 = "./pride-and-prejudice.txt";

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

    public void testExercise11(int round) {
        String[] words = readWords(path1);
        String[] searchWords = readWords(path2);

        LinkedListSet lls = new LinkedListSet();

        // Measure LinkedListSet
        long[][] timeTake1 = new long[words.length][2];
        long countTime = 0;
        for (int i = 0; i < words.length; i++) {
            long startTime = System.nanoTime();
            lls.add(words[i]);
            long curTime = System.nanoTime() - startTime;
            countTime += curTime;
            timeTake1[i][1] = countTime;
            timeTake1[i][0] = i;
        }
        String name1 = "./res/LinkedListSet/" + String.valueOf(round) + ".csv";
        saveCsv(timeTake1, name1);
        System.out.println("Size of LinkedListSet: " + lls.size());
        long[] llsRes = lls.search(searchWords);
        System.out.println("Not Exist Words: " + llsRes[0] + ", Search Min Time: " + llsRes[1] + ", Search Max Time: " + llsRes[2] + ", Search Average Time: " + llsRes[3]);
    }

    public void testExercise12(int round) {
        String[] words = readWords(path1);
        String[] searchWords = readWords(path2);

        BinaryTreeSet bts = new BinaryTreeSet();

        // Measure BinaryTreeSet
        long[][] timeTake2 = new long[words.length][2];
        long countTime = 0;
        for (int i = 0; i < words.length; i++) {
            long startTime = System.nanoTime();
            bts.add(words[i]);
            long curTime = System.nanoTime() - startTime;
            countTime += curTime;
            timeTake2[i][1] = countTime;
            timeTake2[i][0] = i;
            timeTake2[i][0] = i;
        }
        String name2 = "./res/BinaryTreeSet/" + String.valueOf(round) + ".csv";
        saveCsv(timeTake2, name2);
        System.out.println("Size of BinaryTreeSet: " + bts.size());
        long[] btsRes = bts.search(searchWords);
        System.out.println("Not Exist Words: " + btsRes[0] + ", Search Min Time: " + btsRes[1] + ", Search Max Time: " + btsRes[2] + ", Search Average Time: " + btsRes[3]);
    }


    public void testExercise13(int round) {
        String[] words = readWords(path1);
        String[] searchWords = readWords(path2);
        HashTableSet hts = new HashTableSet();
        // Measure HashTableSet
        long[][] timeTake3 = new long[words.length][2];
        long countTime = 0;
        for (int i = 0; i < words.length; i++) {
            long startTime = System.nanoTime();
            hts.add(words[i]);
            long curTime = System.nanoTime() - startTime;
            countTime += curTime;
            timeTake3[i][1] = countTime;
            timeTake3[i][0] = i;
        }
        String name3 = "./res/HashTableSet/" + String.valueOf(round) + ".csv";
        saveCsv(timeTake3, name3);
        System.out.println("Size of HashTableSet: " + hts.size());
        long[] htsRes = hts.search(searchWords);
        System.out.println("Not Exist Words: " + htsRes[0] + ", Search Min Time: " + htsRes[1] + ", Search Max Time: " + htsRes[2] + ", Search Average Time: " + htsRes[3]);
    }

    public void t1() {

        for (int round = 0; round < 10; round += 1) {
            testExercise11(round);
        }
        for (int round = 0; round < 10; round += 1) {
            testExercise12(round);
        }
        for (int round = 0; round < 10; round += 1) {
            testExercise13(round);
        }
    }

    public static void main(String[] args) {
        test t = new test();
        t.t1();
    }
}
