import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Arthur on 2-6-2017.
 */
public class HostGame implements ActionListener{

    private int port;
    private boolean hosting;
    private Main main;
    private CardList cardList;
    private boolean myTurn;

    public HostGame(int port, Main main) {
        this.port = port;
        this.main = main;
        this.hosting = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(hosting) {

            hosting = false;
            //main.getHostButton().setText("Host Game");

        } else {

            //Generate a list of cards.
            cardList = new CardList(24);
            cardList.shuffle();

            new Thread( () -> {

                try {

                    //Create a server socket
                    ServerSocket serverSocket = new ServerSocket(port);

                    //Message hosting started
                    main.updateStatusField("Hosting game, waiting for client", Color.GREEN);
                    main.toggleHostButton(false);
                    main.toggleConnectButton(false);
                    main.toggleIpInputField(false);
                    hosting = true;

                    //Listen for a connection request
                    Socket socket = serverSocket.accept();

                    //Message client connected
                    main.updateStatusField("Client connected, game started", Color.GREEN);

                    //Create data input and output streams
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                    //Send how many cards we are going to send.
                    outputStream.writeInt(cardList.getNumberOf());

                    //Send list of cards
                    for(int i = 0; i < cardList.getNumberOf(); i++) {

                        outputStream.writeUTF(cardList.getCard(i).getName());
                        System.out.println(cardList.getCard(i).getName());
                    }

                    //The client always begins.
                    myTurn = false;


                    while (hosting == true) {

                        if(myTurn = true) {
                            //Sending data to other player.

                            //Stuff from buttons on the screens happens here.
                        }
                        else {
                            //Recieving data from other player

                            while (!myTurn) {
                                String type = inputStream.readUTF();
                                type.toUpperCase(); //In case someone sends lowercase characters.

                                if(type.equals("CLICKED")) {
                                    int clickedCard = inputStream.readInt();

                                    //Turn that card in the GUI.
                                }
                                else if(type.equals("MATCH")) {
                                    int card1 = inputStream.readInt();
                                    int card2 = inputStream.readInt();

                                    //Update the GUI by disabeling the two cards.
                                }
                                else if (type.equals("SCORE")) {
                                    int score = inputStream.readInt();
                                    //Change the score value in the GUI.
                                }
                                else if (type.equals("ENDTURN")) {
                                    myTurn = true;
                                }
                            }
                        }
                    }

                    socket.close();

                    //End game
                    main.updateStatusField("Game ended", Color.GREEN);

                } catch (IOException ioExeption) {

                    //Message client connected
                    main.updateStatusField("PORT IS TAKEN", Color.RED);

                    ioExeption.printStackTrace();
                }
            }).start();
        }
    }
}