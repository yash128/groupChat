import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static int id;
    public static HashMap<String, String> clients = new HashMap<>();
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",1000);
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id()");
        id = scanner.nextInt();
        System.out.println("enter name");
        scanner.nextLine();
        String name = scanner.nextLine();
        DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        stream.writeInt(id);
        stream.writeUTF(name);
        new readMsg(inputStream).start();
        new writeMsg(stream,scanner).start();
    }
}
