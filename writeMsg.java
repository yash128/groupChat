import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class writeMsg extends Thread{
    DataOutputStream stream;
    Scanner s;
    public writeMsg(DataOutputStream stream,Scanner s){
        this.stream = stream;this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (s.hasNext()) {
                    String data = s.nextLine();
                    stream.writeInt(Main.id);
                    stream.writeUTF(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
