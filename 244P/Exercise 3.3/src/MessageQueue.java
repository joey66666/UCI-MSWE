import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.System.exit;

public class MessageQueue {
    private static int n_ids;

    //    Task 1
//    public static void main(String[] args) {
//        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(10);
//        Producer p1 = new Producer(queue, n_ids++);
//        Consumer c1 = new Consumer(queue, n_ids++);
//
//        new Thread(p1).start();
//        new Thread(c1).start();
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        p1.stop();
//    }

//    Task 2
//    public static void main(String[] args) {
//        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(10);
//        Producer p1 = new Producer(queue, n_ids++);
//        Consumer c1 = new Consumer(queue, n_ids++);
//        Consumer c2 = new Consumer(queue, n_ids++);
//
//        new Thread(p1).start();
//        new Thread(c1).start();
//        new Thread(c2).start();
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        p1.stop();
//        queue.add(new Message("stop"));
//    }

    //    Task 3
//    public static void main(String[] args) {
//        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(10);
//        Producer p1 = new Producer(queue, n_ids++);
//        Producer p2 = new Producer(queue, n_ids++);
//        Consumer c1 = new Consumer(queue, n_ids++);
//        Consumer c2 = new Consumer(queue, n_ids++);
//
//        new Thread(p1).start();
//        new Thread(p2).start();
//        new Thread(c1).start();
//        new Thread(c2).start();
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        p1.stop();
//        p2.stop();
//    }

    //    Task 4
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(10);
        int N, M;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input numbers of consumers and producers like <2 2>");
        String line = scanner.nextLine();
        String[] commands = line.split(" ");

        try {
            // N: consumer, M: producer
            N = Integer.parseInt(commands[0]);
            M = Integer.parseInt(commands[1]);
            if (N == 0 || M == 0) {
                System.out.println("Invalid Command");
                exit(0);
            }
            Thread[] consumerThreadPool = new Thread[N];
            Producer[] producerPool = new Producer[M];
            Thread[] producerThreadPool = new Thread[M];

            // create consumers
            for (int i = 0; i < N; i++) {
                consumerThreadPool[i] = new Thread(new Consumer(queue, n_ids++));
            }
            // create producers
            for (int j = 0; j < M; j++) {
                producerPool[j] = new Producer(queue, n_ids++);
                producerThreadPool[j] = new Thread(producerPool[j]);
            }

            // activate consumers
            for (int i = 0; i < N; i++) {
                consumerThreadPool[i].start();
            }
            // activate producers
            for (int j = 0; j < M; j++) {
                producerThreadPool[j].start();
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // stop producers
            for (Producer producer : producerPool) {
                producer.stop();
            }
            // eliminate differ to stop all consumers
            int differ = N - M;
            if (differ > 0) {
                for (int k = 0; k < differ; k++) {
                    queue.put(new Message("stop"));
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid Command");
        }
    }
}
