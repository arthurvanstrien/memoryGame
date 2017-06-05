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
    private int player;
    private int cardClickedOne;
    private int cardClickedTwo;

    public HostGame(int port, Main main) {
        this.port = port;
        this.main = main;
        this.hosting = false;
        player = 1;
        cardClickedOne = -1;
        cardClickedTwo = -1;
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
                    cardList = new CardList(sendData, main, player);
                    cardList.fillList();
                    cardList.shuffle();

                    //Draw the cards on the board of the host.
                    main.setCardList(cardList);

                    //Send how many cards we are going to send.
                    outputStream.writeInt(cardList.getNumberOf());

                    //Send list of cards
                    for(int i = 0; i < cardList.getNumberOf(); i++) {

                        outputStream.writeUTF(cardList.getCard(i).getName());
                    }


                    cardList.toggleCards(false);

                    while (hosting) {

                        if(myTurn) {

                        }
                        else {
                            //Recieving data from other player

                            while (!myTurn) {

                                String type = inputStream.readUTF();
                                type.toUpperCase(); //In case someone sends lowercase characters.

                                if(type.equals("CLICKED")) {
                                    int clickedCard = inputStream.readInt();
                                    cardList.getCard(clickedCard).turnAround();
                                    if(cardClickedOne == -1)
                                        cardClickedOne = clickedCard;
                                    else
                                        cardClickedTwo = clickedCard;
                                }
                                else if(type.equals("MATCH")) {
                                    System.out.println("Match Recieved");
                                    int card1 = inputStream.readInt();
                                    int card2 = inputStream.readInt();
                                    main.updateScorePlayerTwo();
                                    main.updateCardsLeft();
                                    cardList.toggleCards(true);
                                    cardList.addMatchedCard(card1);
                                    cardList.addMatchedCard(card2);
                                    cardList.getCard(cardClickedOne).turnAround();
                                    cardList.getCard(cardClickedTwo).turnAround();
                                }
                                else if (type.equals("ENDTURN")) {
                                    System.out.println("EBD TURN RECIEVED");
                                    cardList.getCard(cardClickedOne).turnAround();
                                    cardList.getCard(cardClickedTwo).turnAround();
                                    cardClickedOne = -1;
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
