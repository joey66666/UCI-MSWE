package edu.uci;

import java.util.*;

import static java.lang.System.exit;

public class Main implements Runnable{

    public void run(){
        System.out.println("Waiting for commands...");
        Scanner scanner = new Scanner(System.in);
        ArrayList threadList = new ArrayList();
        int i = 0;
        while (true) {
            String str = scanner.nextLine();
            String[] strArray = str.split(" ");
            if (strArray.length == 1 && (Objects.equals(strArray[0], "a") || Objects.equals(strArray[0], "c"))) {
                //a, c
                switch (strArray[0]) {
                    case "a" -> {
                        System.out.println("Create new thread");
                        MyRunnable run = new MyRunnable();
                        run.thread(i);
                        i += 1;
                        Thread t = new Thread(run);
                        threadList.add(run);
                        t.start();
                    }
                    case "c" -> {
                        System.out.println("Stopping all threads...");
                        for (int j = 0; j < threadList.size(); j++) {
                            MyRunnable tmp = (MyRunnable) threadList.get(j);
                            if (tmp.flag) {
                                tmp.setStopSign(false);
                                System.out.printf("Stopped Thread <%d>\n", tmp.ID);
                            }
                        }
                        System.out.println("Exit");
                        exit(0);
                    }
                }
            } else if (strArray.length == 2 && Objects.equals(strArray[0], "b")) {
                //b
                System.out.printf("Stopping thread <%s>\n", strArray[1]);
                MyRunnable run = (MyRunnable) threadList.get(Integer.parseInt(strArray[1]));
                run.setStopSign(false);
                System.out.printf("Stopped thread <%s>\n", strArray[1]);
            } else {
                //invalid
                System.out.println("Input invalid!");
            }
        }
    }

    public static void main(String[] args) {
        Main main =new Main();
        Thread mainThread = new Thread(main);
        mainThread.start();
    }
}
// b 0
// b 1
// b 2
// b 3


