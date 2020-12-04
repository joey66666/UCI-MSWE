import java.util.concurrent.*;

public class Main3 {
    private static final Semaphore sem = new Semaphore(1);

    private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d) {
        // Add a sequence of addRow operations with short random naps.
        for (int i = 0; i < 20; i++) {
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            d.addRow("AAAAAAAAAAAA  " + i);
            d.addRow("BBBBBBBBBBBB  " + i);
            sem.release();
            nap(500);
        }
    }

    private static void deleteProc(HighLevelDisplay d) {
        // Add a sequence of deletions of row 0 with short random naps.
        for (int i = 0; i < 20; i++) {
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            d.deleteRow(0);
            sem.release();
            nap(1000);
        }
    }

    public static void main(String[] args) {
        final HighLevelDisplay d = new JDisplay2();

        new Thread() {
            public void run() {
                addProc(d);
            }
        }.start();


        new Thread() {
            public void run() {
                deleteProc(d);
            }
        }.start();

    }
}