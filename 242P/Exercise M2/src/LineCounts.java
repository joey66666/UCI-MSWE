import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LineCounts {

    //Test Data
    //t1: 5, t2: 24, t3: 36, t4: 13426, t5: 6744
    public static void main(String[] args) {
        String[] fileArray = new String[args.length];
        Future[] futureArray = new Future[args.length];
        ExecutorService service = Executors.newFixedThreadPool(args.length);
        String filePath = "../files/";

        for (int i = 0; i < args.length; i++) {
            String fileName = filePath + args[i];
            fileArray[i] = fileName;
            CountLines count = new CountLines(fileName);
            futureArray[i] = service.submit(count);
        }

        for (int j = 0; j < futureArray.length; j++) {
            Object res = 0;
            try {
                res = futureArray[j].get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(fileArray[j] + " " + res);
        }
        System.exit(0);
    }
}
