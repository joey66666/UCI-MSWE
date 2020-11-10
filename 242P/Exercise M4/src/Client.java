import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.net.*;
import java.sql.Time;

public class Client {
    public final static String HOST = "localhost";
    public final static int PORT = 2345;
    static String confirm = "confirm";
    static byte[] confirmBytes = confirm.getBytes();
    final static String splitMark = "splitMark";

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(2000);
            InetAddress host = InetAddress.getByName(HOST);
            byte[] reqBytes = null;
            if (args.length == 1) {
                String reqString = args[0];
                reqBytes = reqString.getBytes();
                DatagramPacket req = new DatagramPacket(reqBytes, reqBytes.length, host, PORT);
                socket.send(req);
                byte[] respBytes = new byte[1024];
                DatagramPacket resp = new DatagramPacket(respBytes, respBytes.length);
                socket.receive(resp);
                String result = new String(resp.getData(), 0, resp.getLength());
                System.out.println(result);
            } else if (args.length == 2 && args[0].equals("get")) {
                StringBuilder sb = new StringBuilder();
                sb.append(args[0]).append(" ").append(args[1]);
                String reqString = sb.toString();
                reqBytes = reqString.getBytes();
                DatagramPacket req = new DatagramPacket(reqBytes, reqBytes.length, host, PORT);
                socket.send(req);
                System.out.println(reqString);
                boolean[] mark = null;
                boolean markFlag = false;
                while (true) {
                    byte[] respBytes = new byte[1024];
                    DatagramPacket resp = new DatagramPacket(respBytes, respBytes.length);
                    socket.receive(resp);
                    String result = new String(resp.getData(), 0, resp.getLength());
                    if (result.equals("end")) {
                        break;
                    } else if (!result.equals("error")) {
                        String[] results = result.split(splitMark);
                        int length = Integer.parseInt(results[0]);
                        int index = Integer.parseInt(results[1]);
                        if (!markFlag) {
                            mark = new boolean[length];
                            markFlag = true;
                        }
                        if (!mark[index]) {
                            if (results.length == 2) {
                                System.out.println();
                            } else {
                                String line = results[2];
                                System.out.println(line);
                            }
                            mark[index] = true;

                            String retString = index + splitMark + confirm;
                            DatagramPacket ret = new DatagramPacket(retString.getBytes(), retString.getBytes().length, host, PORT);
                            socket.send(ret);
                        }

                    } else {
                        System.out.println(result);
                        break;
                    }
                }
            } else {
                System.out.println("Input error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
