import javax.swing.*;
import java.awt.*;

/**
 * Created by Arthur on 15-5-2017.
 * Refactored by Arthur on 03-06-2017.
 */
public class Main extends JPanel {

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
        int port = 8001;

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
        JTextField IPInput = new JTextField(); //input for IP address
        IPInput.setSize(new Dimension(400,20)); //todo make fixed
        IPInput.setColumns(15);
        connectionPanel.add(IPInput);

        //button that connects to IP address
        JButton connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(100,20));
        connectButton.addActionListener(new ConnectGame(IPInput, port));
        connectionPanel.add(connectButton);

        //Button that enables clients to connect to this client.
        JButton hostButton = new JButton("Host Game");
        hostButton.setPreferredSize(new Dimension(100, 20));
        hostButton.addActionListener(new HostGame(port));
        connectionPanel.add(hostButton);

        //gameBoard

        //import image
        /*
        BufferedImage image;
        try {
            image = ImageIO.read(new File("images/vogel.jpg")); //todo make path work
        } catch (IOException e) {
            e.printStackTrace();
        }*/

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
        add(connectionPanel, BorderLayout.PAGE_START);
        add(gameBoard, BorderLayout.CENTER);
        add(scoreBoard, BorderLayout.LINE_END);

        //refresh window
        repaint();
    }
}
