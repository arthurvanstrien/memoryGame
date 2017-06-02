import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Arthur on 2-6-2017.
 */
public class ConnectGame {

    private String ip;
    private int port;

    public  ConnectGame(String ip, int port) {
        this.ip = ip;
        this.port = port;
        connectToGame();
    }

    private void connectToGame(){

        try {

            Socket socket = new Socket(ip, port);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println(inputStream.readUTF());
            outputStream.writeUTF("This is a message to the server.");

            socket.close();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }
}
