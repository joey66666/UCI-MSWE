import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class CountLines implements Callable<Integer> {
    public String fileName;

    CountLines(String fileName) {
        this.fileName = fileName;
    }

    public Integer call() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) {
                count += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return count;
    }
}
