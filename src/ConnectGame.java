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

    public  ConnectGame(JTextField ipInput, int port, Main main) {

        this.port = port;
        this.ipInput = ipInput;
        this.main = main;
        this.connected = false;
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

                    //Create an empty cardlist.
                    cardList = new CardList();

                    //We are now connected with a host.
                    main.updateStatusField("Connected, game started", Color.GREEN);
                    main.toggleHostButton(false);
                    main.toggleConnectButton(false);
                    main.toggleIpInputField(false);
                    connected = true;

                    //Create data input and output streams
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                    //Send how many cards we are going to send.
                    int numberOfCards = inputStream.readInt();

                    //Send list of cards
                    for(int i = 0; i < numberOfCards; i++) {

                        String nameOfCard = inputStream.readUTF();
                        cardList.addCardByString(nameOfCard);
                    }

                    //The client always begins.
                    myTurn = true;


                    while (connected == true) {

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