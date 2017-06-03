import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Arthur on 2-6-2017.
 */
public class ConnectGame implements ActionListener {

    private int port;
    private JTextField ipInput;

    public  ConnectGame(JTextField ipInput, int port) {

        this.port = port;
        this.ipInput = ipInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            Socket socket = new Socket(ipInput.getText(), port);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println(inputStream.readUTF());
            outputStream.writeUTF("This is a message to the server.");

            socket.close();
        }
        catch (IOException ioExeption) {

            ioExeption.printStackTrace();
        }
    }
}
