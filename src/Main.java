import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
    private boolean gameState;

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

        //Game has not started yet.
        gameState = false;

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

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/empty.jpg")));

            //Generate a empty list of buttons for an empty board.
            for (int i = 0; i < numberOfCards; i++){
                JButton button = new JButton(imageIcon);
                button.setSize(100,100);
                button.setEnabled(false);
                gameBoard.add(button);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FATAL ERROR. DEFAULT BUTTONS COULD NOT BE CREATED.");
        }

        //ScoreBoard
        JLabel playerOne = new JLabel("Player 1:");
        scoreLabelPlayer1 = new JLabel("-");
        JLabel playerTwo = new JLabel("Player 2:");
        scoreLabelPlayer2 = new JLabel("-");
        JLabel cards = new JLabel("Cards left:");
        cardsLeftLabel = new JLabel("-");

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
        scorePlayer1 = scorePlayer1 + 2;
        scoreLabelPlayer1.setText(Integer.toString(scorePlayer1));
    }

    public void updateScorePlayerTwo() {
        scorePlayer2 = scorePlayer2 + 2;
        scoreLabelPlayer2.setText(Integer.toString(scorePlayer2));
    }

    public void updateCardsLeft() {
        cardsLeft = cardsLeft - 2;
        cardsLeftLabel.setText(Integer.toString(cardsLeft));
    }

    public void toggleHostButton(boolean value) {
        hostButton.setEnabled(value);
    }

    public void toggleConnectButton (boolean value) {
        connectButton.setEnabled(value);
    }

    public void toggleIpInputField (boolean value) {
        IPInput.setEnabled(value);
    }

    public void startGame() {
        toggleHostButton(false);
        toggleConnectButton(false);
        toggleIpInputField(false);
        setGameState(true);
        cardsLeftLabel.setText(Integer.toString(cardsLeft));
        scoreLabelPlayer1.setText(Integer.toString(0));
        scoreLabelPlayer2.setText(Integer.toString(0));
        updateMessageField("Game started", Color.GREEN);
    }

    public void endGame() {
        gameState = false;
        IPInput.setEnabled(true);
        connectButton.setEnabled(true);
        hostButton.setEnabled(true);
        scoreLabelPlayer1.setText("-");
        scoreLabelPlayer2.setText("-");
        cardsLeftLabel.setText("-");
        messageField.setText("End of game. P1: " + scorePlayer1 + " P2: " + scorePlayer2);
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }

    public void cardsLeft() {
        if(cardsLeft == 0)
            endGame();
    }

    public boolean getGameState() {
        return gameState;
    }

    public void setGameState(boolean value) {
        this.gameState = value;

    }
}