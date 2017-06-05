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

    public void clicked() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(hosting) {

            hosting = false;
            //main.getHostButton().setText("Host Game");

        } else {

            new Thread( () -> {

                try {

                    //Create a server socket
                    ServerSocket serverSocket = new ServerSocket(port);

                    //Message hosting started
                    main.updateMessageField("Hosting game, waiting for client", Color.GREEN);
                    main.toggleHostButton(false);
                    main.toggleConnectButton(false);
                    main.toggleIpInputField(false);

                    hosting = true;

                    //Listen for a connection request
                    Socket socket = serverSocket.accept();

                    //Message client connected
                    main.updateMessageField("Client connected, game started", Color.GREEN);

                    //The client always begins.
                    myTurn = false;

                    //Create data input and output streams
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                    SendData sendData = new SendData(outputStream, myTurn);

                    //Generate a list of cards.
                    cardList = new CardList();
                    cardList.fillList(sendData);
                    cardList.shuffle();

                    //Draw the cards on the board of the host.
                    main.putCardsOnBoard(cardList.getCards());

                    //Send how many cards we are going to send.
                    outputStream.writeInt(cardList.getNumberOf());

                    //Send list of cards
                    for(int i = 0; i < cardList.getNumberOf(); i++) {

                        outputStream.writeUTF(cardList.getCard(i).getName());
                        System.out.println(cardList.getCard(i).getName());
                    }


                    cardList.toggleCards(false);

                    while (hosting == true) {

                        if(myTurn = true) {

                        }
                        else {
                            //Recieving data from other player

                            while (!myTurn) {
                                String type = inputStream.readUTF();
                                type.toUpperCase(); //In case someone sends lowercase characters.

                                if(type.equals("CLICKED")) {
                                    int clickedCard = inputStream.readInt();
                                    cardList.getCard(clickedCard).turnAround();
                                }
                                else if(type.equals("MATCH")) {
                                    int card1 = inputStream.readInt();
                                    int card2 = inputStream.readInt();

                                    //Update the GUI by disabeling the two cards.
                                }
                                else if (type.equals("SCORE")) {
                                    int score = inputStream.readInt();
                                    main.updateScorePlayerTwo(score);
                                }
                                else if (type.equals("ENDTURN")) {
                                    myTurn = true;
                                }
                            }
                        }
                    }

                    socket.close();

                    //End game
                    main.updateMessageField("Game ended", Color.GREEN);

                } catch (IOException ioExeption) {

                    //Message client connected
                    main.updateMessageField("PORT IS TAKEN", Color.RED);

                    ioExeption.printStackTrace();
                }
            }).start();
        }
    }
}
