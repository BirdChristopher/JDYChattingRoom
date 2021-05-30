import java.io.*;
import java.net.*;
import java.util.*;
public class Client {
    public int port = 8080;
    Socket socket = null;
    public static void main(String[] args) {
        new Client();
    }
    public Client() {
        try {
            socket = new Socket("127.0.0.1", port);
            new Cthread().start();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg1;
            while ((msg1 = br.readLine()) != null) {
                System.out.println(msg1);//收到服务器传回的消息
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class Cthread extends Thread {
        public void run() {
            try {
                BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                String msg2;
                while (true) {
                    msg2 = re.readLine();
                    pw.println(msg2);//传给服务器的消息
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}