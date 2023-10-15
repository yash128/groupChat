import java.io.DataInputStream;
import java.io.IOException;

public class readMsg extends Thread{
    DataInputStream read;
    public readMsg(DataInputStream stream){
        read = stream;
    }

    @Override
    public void run() {
        while (true){
            try {
                String msg = read.readUTF();
                if (msg.contains(";")){
                    String[] sub = msg.split(";");
                    Main.clients.put(sub[0], sub[1]);
                    System.out.println(sub[1] + " is now in");
                }else {
                    String[] sub = msg.split(":");
                    System.out.println(sub[1]);
                    /*System.out.println(Main.clients.get(sub[0])+ " " + sub[1]);
                    System.out.println(sub[0] + " keys we have :: ");
                    for (String s : Main.clients.keySet()){
                        System.out.println(s);
                    }*/
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
