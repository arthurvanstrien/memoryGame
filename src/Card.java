import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Tabitha on 23-5-2017.
 */
public class Card implements ActionListener{
    private String name;
    private ImageIcon image;
    private ImageIcon backImage;
    private boolean faceDown;
    private boolean onBoard;
    private int index; //place on the board and index in CardsList
    private JButton button;

    public Card(String name, int index){
        this.name = name;
        this.index = index;
        onBoard = true;
        faceDown = true;

        //Create the imageIcon for this specific card.
        try {
            image = new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/" + name + ".jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create the imageIcon that is used as back by all cards.
        try {
            backImage = new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/back.jpg")));
            button = new JButton(backImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        button.addActionListener(this);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void turnAround(){
        if (faceDown) {
            faceDown = false;
            button.setIcon(image);
        }
        else {
            faceDown = true;
            button.setIcon(backImage);
        }
    }

    //THIS IMPLIES THAT THE WHOLE IS REMOVED, WHILE IT IS NOT.
    //THIS SHOULD BE CALLED SOMETHING LIKE DISABLE CARD.
    //BECAUSE IT DISABLES THE CARD :P
    public void removeMe(){
        //change image to "empty image"
        this.onBoard = false;
    }

    public boolean sameCards(Card other){
        return (this.name == other.name);
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public boolean isOnBoard() {
        return onBoard;
    }

    public String toString(){
        return "name: " + this.name + " index: " + this.index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        turnAround();
    }
}
