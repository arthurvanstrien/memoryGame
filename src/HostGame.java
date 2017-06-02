import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Arthur on 2-6-2017.
 */
public class HostGame {

    private int port;
    private boolean gameState;

    public HostGame(int port) {
        this.port = port;
        hostGame();
    }

    private void hostGame() {

        //Generate a list of cards.
        //CardList cardList = new CardList();
        //cardList.shuffle();
        //ArrayList<Card> cards = new ArrayList<>();

        new Thread( () ->
        {
            try
            {
                //Create a server socket
                ServerSocket serverSocket = new ServerSocket(port);

                //Listen for a connection request
                Socket socket = serverSocket.accept();

                //A client connected so the gamestate is changed to true.
                gameState = true;

                //Create data input and output streams
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                outputStream.writeUTF("Dit is het verhaal van Jan de Bakker. Hij was een bakker. Einde verhaal.");
                System.out.println(inputStream.readUTF());

                /*
                //Send how many cards we are going to send.
                outputStream.writeInt(cards.size());

                System.out.println(cards.get(1).getName());

                //Send list of cards
                for(int i = 0; i < cards.size(); i++) {

                    outputStream.writeUTF(cards.get(i).getName());
                }

                while (gameState == true) {

                    String type = inputStream.readUTF();

                    if(type.equals("match")) {

                    }
                    else if (type.equals("score")) {
                        int score = inputStream.readInt();
                        //Roep update score method hier aan.
                    }
                    else if (type.equals("endTurn")) {
                        //Deze speler is weer aan de beurt.
                    }

                    // Receive radius from the client
                    double inputValue = inputStream.readDouble();
                    System.out.println(inputValue);
                }
                */

                socket.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }).start();
    }
}
