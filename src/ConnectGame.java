import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
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
    private Pane pane;
    private Main main;
    private boolean connected;
    private boolean myTurn;
    private CardList cardList;
    private int player;

    public  ConnectGame(JTextField ipInput, int port, Main main) {

        this.port = port;
        this.ipInput = ipInput;
        this.main = main;
        this.connected = false;
        player = 2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(connected) {

            connected = false;
            //main.getHostButton().setText("Host Game");

        } else {

            new Thread( () -> {

                try {
                    Socket socket = new Socket(ipInput.getText(), port);

                    //We are now connected with a host.
                    main.updateMessageField("Connected, game started", Color.GREEN);
                    main.toggleHostButton(false);
                    main.toggleConnectButton(false);
                    main.toggleIpInputField(false);
                    connected = true;

                    //The client always begins.
                    myTurn = true;

                    //Create data input and output streams
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                    SendData sendData = new SendData(outputStream, myTurn);

                    //Create an empty cardlist.
                    cardList = new CardList(sendData, main, player);

                    //Send how many cards we are going to send.
                    int numberOfCards = inputStream.readInt();

                    //Send list of cards
                    for(int i = 0; i < numberOfCards; i++) {

                        String nameOfCard = inputStream.readUTF();
                        cardList.addCardByString(nameOfCard, i, sendData);
                    }

                    main.setCardList(cardList);


                    while (connected) {

                        if(myTurn) {
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
                                    cardList.getCard(clickedCard).turnAround();
                                }
                                else if(type.equals("MATCH")) {
                                    int card1 = inputStream.readInt();
                                    int card2 = inputStream.readInt();
                                    main.updateScorePlayerTwo();
                                    main.updateCardsLeft();
                                    cardList.addMatchedCard(card1);
                                    cardList.addMatchedCard(card2);
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
