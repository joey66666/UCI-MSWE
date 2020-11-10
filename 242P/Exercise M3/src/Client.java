import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.logging.SocketHandler;

public class Client {
    public final static String HOST = "127.0.0.1";
    public final static int PORT = 2345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT);
            OutputStream os = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            System.out.println("Client activated");

            if (args.length == 1) {
                String action = args[0];
                bw.write(action);
                bw.flush();
                System.out.println(action);
            } else if (args.length == 2 && args[0].equals("get")) {
                String action = args[0];
                String fileName = args[1];
                bw.write(action + " " + fileName);
                bw.flush();
            } else {
                System.out.println("Input error!");
            }
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            is.close();
            bw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
