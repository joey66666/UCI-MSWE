import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ServerThread extends Thread {
    Socket socket = null;
    File fileDir = null;

    public ServerThread(File fileDir, Socket socket) {
        this.fileDir = fileDir;
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            OutputStream os = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("From client: " + line);
                if (line.equals("index")) {
                    File[] files = fileDir.listFiles();
                    String fileString = Arrays.toString(files);
                    System.out.println(fileString);
                    bw.write(fileString);
                    bw.flush();
                } else if (line.split(" ")[0].equals("get")) {

                    String fileName = line.split(" ")[1];
                    File file = new File(fileName);
                    if (!file.exists()) {
                        bw.write("error");
                    } else {
                        bw.write("ok\n");
                        bw.flush();
                        String res = readFile(fileName);
                        bw.write(res);
                    }
                    bw.flush();
                } else {
                    System.out.println("error");
                    bw.write("error");
                    bw.flush();
                }
            }
            bw.close();
            os.close();
            br.close();
            is.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                sb.append(s).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}


