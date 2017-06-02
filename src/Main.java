import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Arthur on 15-5-2017.
 */
public class Main extends JPanel implements Serializable {

    private int port;

    public static void main(String[] args) {
        new Main();
        makeUI();
    }

    public Main()
    {
        this.port = 8001;
    }

    private static void makeUI(){
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
        IPInput.setColumns(15);
        connectionPanel.add(IPInput);

        JButton connect = new JButton("Connect"); //button that connects to IP address
        connect.setPreferredSize(new Dimension(100,20));
        connectionPanel.add(connect);
        //gameBoard

        //import image
        BufferedImage image;
        try {
            image = ImageIO.read(new File("images/vogel.jpg")); //todo make path work
        } catch (IOException e) {
            e.printStackTrace();
        }
        // todo replace with the function new game uses later
        for (int i = 0; i < 24; i++){
            String name = "button " + i;
            JButton button = new JButton(name);
            button.setSize(100,100);
            gameBoard.add(button);
        }
        //scoreBoard
        //todo create scores field that shows scores
        JButton newGame = new JButton("New Game");
        newGame.setSize(100,25);
        scoreBoard.add(newGame);


        //add everything to pane
        pane.add(connectionPanel, BorderLayout.PAGE_START);
        pane.add(gameBoard, BorderLayout.CENTER);
        pane.add(scoreBoard, BorderLayout.LINE_END);

        //refresh window
        frame.repaint();
        frame.setVisible(true);
    }
}
