package edu.uci;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyRunnable implements Runnable {

    public volatile boolean flag = true;
    public int ID;

    public void thread(int threadID) {
        ID = threadID;
    }
    public void setStopSign(boolean stopSign){
        flag = stopSign;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        while (flag) {
            try {
                Date date = new Date();
                String dateStr = sdf.format(date);
                System.out.printf("Hello World! I'm thread <%d>. The time is <%s>\n", ID, dateStr);

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
