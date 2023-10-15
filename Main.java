import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static HashMap<Integer, ArrayList<handle>> clients = new HashMap<>();
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1000);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream inp = new DataInputStream(socket.getInputStream());
            int id = inp.readInt();
            String name = inp.readUTF();
            handle h = new handle(name,inp,new DataOutputStream(socket.getOutputStream()), socket.getInetAddress());
            if (clients.containsKey(id)){
                for(handle s : clients.get(id)){
                    String str = s.addr.getAddress().toString() + ";" + name;
                    s.write.writeUTF(str);
                    String t = (s.addr.getAddress().toString() + ";" + s.name);
                    h.write.writeUTF(t);
                }
                clients.get(id).add(h);
            } else {
                ArrayList<handle> list = new ArrayList<>();
                list.add(h);
                clients.put(id,list);
            }
            h.start();
        }
    }
}
