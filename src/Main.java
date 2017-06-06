import javax.swing.*;
import java.awt.*;

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
    private JLabel scoreLabelPlayer1;
    private JLabel scoreLabelPlayer2;
    private int scorePlayer1;
    private int scorePlayer2;
    private JLabel cardsLeftLabel;
    private JPanel gameBoard;
    private int cardsLeft;

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
        cardsLeft = numberOfCards;

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
        scoreLabelPlayer1 = new JLabel("-");
        JLabel playerTwo = new JLabel("Player 2:");
        scoreLabelPlayer2 = new JLabel("-");
        JLabel cards = new JLabel("Cards left:");
        cardsLeftLabel = new JLabel(Integer.toString(cardsLeft));

        scoreBoard.add(playerOne);
        scoreBoard.add(scoreLabelPlayer1);
        scoreBoard.add(playerTwo);
        scoreBoard.add(scoreLabelPlayer2);
        scoreBoard.add(cards);
        scoreBoard.add(cardsLeftLabel);

        //Add everything to pane
        add(connectionPanel, BorderLayout.PAGE_START);
        add(gameBoard, BorderLayout.CENTER);
        add(scoreBoard, BorderLayout.LINE_END);

        //Display default startmessage
        updateMessageField("Start a game by hosting or connecting", Color.BLACK);
    }

    public void setCardList(CardList cardList) {
        gameBoard.removeAll();

        for (int i = 0;  i < 24; i++){
            //Adds a new cards with this index to the board.
            gameBoard.add(cardList.getCard(i).getButton());
        }

        revalidate();
    }

    public void updateMessageField(String text, Color color) {
        messageField.setText(text);
        messageField.setForeground(color);
    }

    public void updateScorePlayerOne() {
        scorePlayer1 = scorePlayer1 + 1;
        scoreLabelPlayer1.setText(Integer.toString(scorePlayer1));
    }

    public void updateScorePlayerTwo() {
        scorePlayer2 = scorePlayer2 + 2;
        scoreLabelPlayer2.setText(Integer.toString(scorePlayer2));
    }

    public void updateCardsLeft() {
        cardsLeft = cardsLeft - 2;
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
}