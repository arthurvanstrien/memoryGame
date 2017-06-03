import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


/**
 * Created by Tabitha on 15-5-2017.
 */
public class Main extends JPanel implements MouseListener{
    public static void main(String[] args) {
        //create cardslist
        CardList cardList = new CardList(createCardsArrayList());
        cardList.shuffle();
        cardList.printCardList();
        // Create window
        JFrame frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700,420);

        frame.setContentPane(new Main());

        // Create Border Layout
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

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
        JTextField IPInput = new JTextField(); //input for IP address
        IPInput.setSize(new Dimension(400,20)); //todo make fixed
        connectionPanel.add(IPInput);

        JButton connect = new JButton("Connect"); //button that connects to IP address
        connect.setPreferredSize(new Dimension(100,20));
        connectionPanel.add(connect);

        JLabel connection = new JLabel("connection status");
        connectionPanel.add(connection);

        //scoreBoard
        JLabel scorePlayer1 = new JLabel("Player 1: -");
        JLabel scorePlayer2 = new JLabel("Player 2: -");
        JLabel cardsLeft = new JLabel("Cards left: -");
        JButton newGame = new JButton("New Game");
        newGame.setSize(100,25);
        scoreBoard.add(scorePlayer1);
        scoreBoard.add(scorePlayer2);
        scoreBoard.add(cardsLeft);
        scoreBoard.add(newGame);

        // todo replace with the function new game uses later
        for (int i = 0; i < 24; i++){
            Card card = cardList.cards.get(i);
            JButton button = card.putOnBoard();
            button.setSize(100,100);
            gameBoard.add(button);
        }

        //add everything to pane
        pane.add(connectionPanel, BorderLayout.PAGE_START);
        pane.add(gameBoard, BorderLayout.CENTER);
        pane.add(scoreBoard, BorderLayout.LINE_END);

        //refresh window
        frame.repaint();
        frame.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

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
}