import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Arthur on 2-6-2017.
 */
public class HostGame implements ActionListener{

    private int port;
    private Main main;
    private CardList cardList;
    private int player;

    public HostGame(int port, Main main) {
        this.port = port;
        this.main = main;
        player = 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new Thread( () -> {

            ServerSocket serverSocket = null;
            DataOutputStream outputStream = null;
            DataInputStream inputStream  = null;

            try {

                //Create a server socket
                serverSocket = new ServerSocket(port);

                //Message hosting started
                main.updateMessageField("Hosting game, waiting for client", Color.GREEN);

                //Listen for a connection request
                Socket socket = serverSocket.accept();

                //Run startGame script
                main.startGame();

                //Create data input and output streams
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                SendData sendData = new SendData(outputStream);

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
                        main.updateScorePlayerTwo();
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

                outputStream.close();
                inputStream.close();
                socket.close();


            } catch (IOException ioExeption) {

                ioExeption.printStackTrace();
            }
            finally {
                main.endGame();
                try {
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();
    }
}
