import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public final static int PORT = 2345;
    public final static int THREAD_NUM = 5;

    public static void main(String[] args) {
        File fileDir = new File(args[0]);
        if (!fileDir.isDirectory()) {
            System.out.println("File directory error");
            System.exit(0);
        }else{
            System.out.println("File directory: "+Arrays.toString(fileDir.listFiles()));
        }

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_NUM);

        try {
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = null;
            System.out.println("Server listen");
            while (true) {
                socket = server.accept();
                Runnable task = new ServerThread(fileDir, socket);
                pool.submit(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
