import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

public class handle extends Thread{
    DataOutputStream write;
    DataInputStream read;
    String name;
    InetAddress addr;
    public handle(String name, DataInputStream read, DataOutputStream write, InetAddress addr){
        this.read = read;
        this.write = write;
        this.addr = addr;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int id = read.readInt();
                String msg = read.readUTF();
                new Thread(() -> {
                    try {
                        writeMsg(id, msg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();//write msg
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeMsg(int id,String msg) throws IOException{
        for (handle s : Main.clients.get(id)) {
            if (!s.equals(this)) {
                s.write.writeUTF(this.addr.getAddress()+":"+msg);
            }
        }
    }
}
