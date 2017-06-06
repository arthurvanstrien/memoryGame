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
    private CardList cardList;
    private int player;

    public  ConnectGame(JTextField ipInput, int port, Main main) {

        this.port = port;
        this.ipInput = ipInput;
        this.main = main;
        player = 2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new Thread( () -> {

            try {
                Socket socket = new Socket(ipInput.getText(), port);

                //We are now connected with a host so we start the game.
                main.startGame();

                //Create data input and output streams
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                SendData sendData = new SendData(outputStream);

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

                while (main.getGameState()) {

                    String type = inputStream.readUTF();
                    type.toUpperCase(); //In case someone sends lowercase characters.

                    if(type.equals("CLICKED")) {
                        int clickedCard = inputStream.readInt();
                        cardList.getCard(clickedCard).turnAround();
                    }
                    else if(type.equals("MATCH")) {
                        System.out.println("Match Recieved");
                        int card1 = inputStream.readInt();
                        int card2 = inputStream.readInt();
                        main.updateScorePlayerOne();
                        main.updateCardsLeft();
                        cardList.addMatchedCard(card1);
                        cardList.addMatchedCard(card2);
                        cardList.getCard(card1).makeEmpty();
                        cardList.getCard(card2).makeEmpty();
                    }
                    else if (type.equals("ENDTURN")) {
                        System.out.println("END TURN RECIEVED");
                        cardList.toggleCards(true);
                    }

                    main.cardsLeft();
                }

                socket.close();

            } catch (IOException ioExeption) {

                //Message client connected
                main.endGame();
                ioExeption.printStackTrace();
            }
        }).start();
    }
}
