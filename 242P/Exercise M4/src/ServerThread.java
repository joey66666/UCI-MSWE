import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerThread extends Thread {
    static String confirm = "confirm";
    static String error = "error";
    static String end = "end";
    static byte[] confirmBytes = confirm.getBytes();
    static byte[] endBytes = end.getBytes();
    final static String splitMark = "splitMark";

    File fileDir = null;
    DatagramSocket socket = null;

    public ServerThread(File fileDir, DatagramSocket socket) {
        this.fileDir = fileDir;
        this.socket = socket;
    }

    public void run() {
        while (true) {
            try {
                byte[] reqBytes = new byte[1024];
                DatagramPacket req = new DatagramPacket(reqBytes, reqBytes.length);
                socket.receive(req);
                String reqString = new String(req.getData(), 0, req.getLength());
                String[] reqStrings = reqString.split(" ");
                if (reqStrings.length == 1 && reqStrings[0].equals("index")) {
                    System.out.println(reqString);
                    String res = Arrays.toString(fileDir.listFiles());
                    DatagramPacket resp = new DatagramPacket(res.getBytes(), res.getBytes().length, req.getSocketAddress());
                    socket.send(resp);
                    while (true) {
                        reqBytes = new byte[1024];
                        req = new DatagramPacket(reqBytes, reqBytes.length);
                        socket.receive(req);
                        reqString = new String(req.getData(), 0, req.getLength());
                        if (!reqString.equals(confirm)) {
                            socket.send(resp);
                        } else {
                            break;
                        }
                    }
                } else if (reqStrings.length == 2 && reqStrings[0].equals("get")) {
                    System.out.println(reqString);
                    String fileName = reqStrings[1];
                    File file = new File(fileName);
                    System.out.println(fileName);
                    if (!file.exists()) {
                        System.out.println("file not exist");
                        DatagramPacket resp = new DatagramPacket(error.getBytes(), error.getBytes().length, req.getSocketAddress());
                        socket.send(resp);
                    } else {
                        try {
                            String[] lines = readFile(new File(fileName));
                            Boolean[] confirmMark = new Boolean[lines.length];
                            int index = 0;
                            while (index < lines.length) {
                                sendFile(lines, index, socket, req);
                                int retry = 1;
                                while (true) {
                                    reqBytes = new byte[1024];
                                    req = new DatagramPacket(reqBytes, reqBytes.length);
                                    long time = System.currentTimeMillis();
                                    socket.receive(req);
                                    reqString = new String(req.getData(), 0, req.getLength());
                                    String[] strings = reqString.split(splitMark);
                                    if (strings[1].equals(confirm)) {
                                        confirmMark[Integer.parseInt(strings[0])] = true;
                                        break;
                                    }
                                    long curTime = System.currentTimeMillis() - time;
                                    if (curTime >= 2000 && retry <= 3&& !confirmMark[index]) {
                                        sendFile(lines, index, socket, req);
                                        retry += 1;
                                        System.out.println("Retry: " + retry);
                                    } else {
                                        System.out.println("Retry Time out");
                                        break;
                                    }
                                }
                                index += 1;
                            }
                            // end
                            socket.send(new DatagramPacket(endBytes, endBytes.length, req.getSocketAddress()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("error");
                            DatagramPacket resp = new DatagramPacket(error.getBytes(), error.getBytes().length, req.getSocketAddress());
                            socket.send(resp);
                        }
                    }
                } else {
                    String res = error;
                    DatagramPacket resp = new DatagramPacket(res.getBytes(), res.getBytes().length, req.getSocketAddress());
                    socket.send(resp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendFile(String[] lines, int index, DatagramSocket socket, DatagramPacket req) {
        try {
            String res = String.valueOf(lines.length) + splitMark + String.valueOf(index) + splitMark + lines[index];
            DatagramPacket resp = new DatagramPacket(res.getBytes(), res.getBytes().length, req.getSocketAddress());
            socket.send(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] readFile(File fileName) {
        ArrayList<String> fileLines = new ArrayList<>();
        String[] fileLinStrings = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // split by line
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                fileLines.add(s);
            }
            Object[] fileLinesArray = fileLines.toArray();
            fileLinStrings = new String[fileLinesArray.length];
            for (int i = 0; i < fileLinesArray.length; i++) {
                fileLinStrings[i] = (String) fileLinesArray[i];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLinStrings;
    }
}


