import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Tabitha on 23-5-2017.
 */
public class Card {
    private String name;
    private String imagePath;
    private boolean faceDown;
    private boolean onBoard;
    private int index; //place on the board and index in CardsList

    public Card(String name, int index){
        this.name = name;
        this.imagePath = "resources/images/" + name +".jpg";
        this.onBoard = true;
        this.faceDown = true;
        this.index = index;
    }

    public Card() {
        this.name = "name";//this will become a parameter later on
        this.imagePath = "resources/vogel.jpg";
        this.onBoard = true;
        this.faceDown = true;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void turnAround(){
        if (onBoard) {
            if (faceDown) {
                faceDown = false;
                //todo change image of the card
                this.putOnBoard(); //this function probably does that, can't test yet

                /* if (this catrd is the second card){
                return otherCard
               } else {
               nog een kaart laten kiezen
                    }
                 */
                //call sameCards function
               /* if (sameCards(otherCard)){
                    //add points
                    //remove both this card and other card with function //todo make function
                } */
            }
        }
    }

    //THIS IMPLIES THAT THE WHOLE IS REMOVED, WHILE IT IS NOT.
    //THIS CHOULD BE CALLED SOMETHING LIKE DISABLE CARD.
    //BECAUSE IT DISABLES THE CARD :P
    public void removeMe(){
        //change image to "empty image"
        this.onBoard = false;
    }

    public boolean sameCards(Card other){
        if (this.name == other.name){
            return true;
        } else {
            return false;
        }
    }

    public JButton putOnBoard(){
        JButton button = new JButton();
        if (onBoard) {
            try {
                Image img = ImageIO.read(getClass().getResource(this.imagePath));
                Image back = ImageIO.read(getClass().getResource("/resources/images/back.jpg"));
                if (faceDown) {
                    button.setIcon(new ImageIcon(back));
                } else {
                    button.setIcon(new ImageIcon(img));
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            try {
                Image img = ImageIO.read(getClass().getResource("/resources/images/empty.jpg"));
                button.setIcon(new ImageIcon(img));
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return button;
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
