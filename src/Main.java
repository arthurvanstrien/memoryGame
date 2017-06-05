import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tabitha on 15-5-2017.
 * Refactored by Arthur on 03-06-2017.
 */
public class Main extends JPanel {

    private JTextField IPInput;
    private JButton connectButton;
    private JButton hostButton;
    private JTextField messageField;
    private int numberOfCards;
    private JLabel scorePlayer1;
    private JLabel scorePlayer2;
    private JLabel cardsLeft;
    private JPanel gameBoard;


    public static void main(String[] args) {

        // Create window
        JFrame frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700,420);
        frame.setContentPane(new Main());
        frame.setVisible(true);
    }

    public Main()
    {
        //Set default port
        int port = 8001;

        //Set default number of cards.
        numberOfCards = 24;

        // Create Border Layout
        setLayout(new BorderLayout());

        //create JPanels
        JPanel connectionPanel = new JPanel();
         gameBoard = new JPanel();
        JPanel scoreBoard = new JPanel();

        //set layout managers
        connectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        gameBoard.setLayout(new GridLayout(4,6));
        scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));

        //set sizes
        connectionPanel.setSize(new Dimension(700,20));
        gameBoard.setSize(new Dimension(600,400));
        scoreBoard.setSize(new Dimension(100,400));

        //add things to JPanels

        //connectionPanel

        //input field for IP address
        IPInput = new JTextField();
        IPInput.setSize(new Dimension(400,20));
        IPInput.setColumns(10);
        connectionPanel.add(IPInput);

        //button that connects to IP address
        connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(100,20));
        connectButton.addActionListener(new ConnectGame(IPInput, port, this));
        connectionPanel.add(connectButton);

        //Button that enables clients to connect to this client.
        hostButton = new JButton("Host Game");
        hostButton.setPreferredSize(new Dimension(100, 20));
        hostButton.addActionListener(new HostGame(port, this));
        connectionPanel.add(hostButton);

        //Field that displays of you are hosting, connected or disconnected.
        messageField = new JTextField();
        messageField.setSize(new Dimension(400,20)); //todo make fixed
        messageField.setColumns(30);
        messageField.setEditable(false);
        connectionPanel.add(messageField);

        //GameBoard

        //Generate a empty list of buttons for an empty board.
        for (int i = 0; i < numberOfCards; i++){
            JButton button = new JButton(Integer.toString((i + 1)));
            button.setSize(100,100);
            button.setEnabled(false);
            gameBoard.add(button);
        }

        //ScoreBoard
        JLabel playerOne = new JLabel("Player 1:");
        scorePlayer1 = new JLabel("-");
        JLabel playerTwo = new JLabel("Player 2:");
        scorePlayer2 = new JLabel("-");
        JLabel cards = new JLabel("Cards left:");
        cardsLeft = new JLabel("-");

        scoreBoard.add(playerOne);
        scoreBoard.add(scorePlayer1);
        scoreBoard.add(playerTwo);
        scoreBoard.add(scorePlayer2);
        scoreBoard.add(cards);
        scoreBoard.add(cardsLeft);

        //Add everything to pane
        add(connectionPanel, BorderLayout.PAGE_START);
        add(gameBoard, BorderLayout.CENTER);
        add(scoreBoard, BorderLayout.LINE_END);

        //Display default startmessage
        updateMessageField("Start a game by hosting or connecting", Color.BLACK);
    }

    public void updateMessageField(String text, Color color) {
        messageField.setText(text);
        messageField.setForeground(color);
    }

    public void updateScorePlayerOne(int score) {
        scorePlayer1.setText(Integer.toString(score));
    }

    public void updateScorePlayerTwo(int score) {
        scorePlayer2.setText(Integer.toString(score));
    }

    public void updateCardsLeft(int cardsLeft) {
        this.cardsLeft.setText(Integer.toString(cardsLeft));
    }

    public void toggleHostButton(boolean value) {
        hostButton.setEnabled(value);
    }

    public void toggleConnectButton (boolean value) {
        connectButton.setEnabled(value);
    }

    public void updateConnectButton (String text) {
        connectButton.setText(text);
    }

    public void toggleIpInputField (boolean value) {
        IPInput.setEnabled(value);
    }

    public  void putCardsOnBoard(ArrayList<Card> cards) {
        gameBoard.removeAll();

        for (int i = 0;  i < 24; i++){
            //Adds a new cards with this index to the board.
            gameBoard.add(cards.get(i).getButton());
        }

        revalidate();
    }
}