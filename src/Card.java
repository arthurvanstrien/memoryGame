import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Tabitha on 23-5-2017.
 */
public class Card {
    private String name;
    private String imagePath;
    private boolean faceDown;
    private boolean onBoard;
    private int index; //place on the board and index in CardsList
    private JButton button;

    public Card(String name, int index){
        this.name = name;
        this.index = index;
        imagePath = "resources/images/" + name +".jpg";
        onBoard = true;
        faceDown = true;

        try {
            Image back = ImageIO.read(getClass().getResource("/resources/images/back.jpg"));
            button = new JButton(new ImageIcon(back));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getImagePath() {
        return imagePath;
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
}
