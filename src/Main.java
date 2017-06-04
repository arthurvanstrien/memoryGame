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
    private JLabel scorePlayer1;
    private JLabel scorePlayer2;
    private JLabel cardsLeft;

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
        numberOfCards = 24;

        // Create Border Layout
        setLayout(new BorderLayout());

        //create JPanels
        JPanel connectionPanel = new JPanel();
        JPanel gameBoard = new JPanel();
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

        //import image
        //THIS SHOULD BE DONE IN THE CARDLIST CLASS AND SHOULD BE REMOVED.
        /*
        BufferedImage image;
        try {
            image = ImageIO.read(new File("images/vogel.jpg")); //todo make path work
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Generate a empty list of buttons for an empty board.
        for (int i = 0; i < numberOfCards; i++){
            JButton button = new JButton(Integer.toString((i + 1)));
            button.setSize(100,100);
            button.setEnabled(false);
            gameBoard.add(button);
        }

        //ScoreBoard
        scorePlayer1 = new JLabel("Player 1: -");
        scorePlayer2 = new JLabel("Player 2: -");
        cardsLeft = new JLabel("Cards left: -");
        JButton newGame = new JButton("New Game");
        newGame.setSize(100,25);
        scoreBoard.add(scorePlayer1);
        scoreBoard.add(scorePlayer2);
        scoreBoard.add(cardsLeft);
        scoreBoard.add(newGame);

        //Add everything to pane
        add(connectionPanel, BorderLayout.PAGE_START);
        add(gameBoard, BorderLayout.CENTER);
        add(scoreBoard, BorderLayout.LINE_END);

        //Display default startmessage
        updateMessageField("Start a game by hosting or connecting", Color.BLACK);

        //refresh window
        repaint();
    }

    public void updateMessageField(String text, Color color) {
        messageField.setText(text);
        messageField.setForeground(color);
    }

    public void updateScorePlayerOne(int score) {
        scorePlayer1.setText("Player 1: " + Integer.toString(score));
    }

    public void updateScorePlayerTwo(int score) {
        scorePlayer2.setText("Player 2: " + Integer.toString(score));
    }

    public void updateCardsLeft(int cardsLeft) {
        this.cardsLeft.setText("Cards left: " + Integer.toString(cardsLeft));
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

    /*
    THIS SHOULD NOT BE DONE IN THE MAIN CLASS.
    THE CLASS CARDLIST IS CREATED TO HANDLE EVERYTHING AROUND THE CARDLIST.
    IF THE CARDLIST CLASS DOES NOT GENERATE THE LIST OF CARDS, THE CARDLIST CLASS -
    IS NO MORE THAN A COMPLICATED ARRAYLIST.

    //creates arraylist that has all the cards
    public static ArrayList<Card> createCardsArrayList(){
        ArrayList<Card> cards = new ArrayList<>(24);
        ArrayList<String> names = new ArrayList<>(12);
        names.add("case");
        names.add("cpu");
        names.add("cpufan");
        names.add("fan");
        names.add("gpu");
        names.add("hdd");
        names.add("mobo");
        names.add("monitor");
        names.add("mouse");
        names.add("psu");
        names.add("ram");
        names.add("ssd");

        for (int i = 0; i < 12; i++){
            int secondIndex = i + 12;
            cards.add(new Card(names.get(i), i));
            cards.add(new Card(names.get(i), secondIndex));
            //adds two indentical cards to cards
            System.out.println(names.get(i));
        }
        return cards;
    }
    */
}