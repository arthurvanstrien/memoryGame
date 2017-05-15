import javax.swing.*;
import java.awt.*;

/**
 * Created by Arthur on 15-5-2017.
 */
public class Main extends JPanel {
    public static void main(String[] arghs){
        JFrame frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,600));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(new Main());
        frame.setVisible(true);

    }
}
