import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(30000);
            System.out.println("Server 30000端口监听中...");

            while (true) {
                Socket s = serverSocket.accept();
                new Thread(new ChatThread(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatThread implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;

    public ChatThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("成功连接：" + socket.getInetAddress().getHostAddress());
        String content = null;
        String send = null;
        int i = 0;
        try {
            while (true){
                if (((content = in.readLine()) != null)) {
                    System.out.println(content);
                    //send = Server.scanner.nextLine();
                    out.println("register#1#15");
                }
            }
        } catch (IOException e) {
            System.out.println("客户端断开连接");
        }
    }
}
