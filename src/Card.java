import javax.swing.*;

/**
 * Created by Tabitha on 23-5-2017.
 */
public class Card {
    private String name;
    private String imagePath;
    private boolean faceDown;
    private boolean onBoard;

    private int index;

    public void Card(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
        this.onBoard = true;
        this.faceDown = true;
    }

    public void Card() {
        this.name = "name";//this will become a parameter later on
        this.imagePath = "hoi";
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
                //call function that checks if this is the second card //todo create this function
                //if this is the second card, get the first card with other function //todo make function
                //call sameCards function
               /* if (sameCards(otherCard)){
                    //add points
                    //remove both this card and other card with function //todo make function
                } */
            }
        }
    }

    /*
    public Card CheckIfSecond(){
        for (int i= 0; i <= cards.length; i++){
            if (cards)
            }
        }
    }
    */

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
        //todo make button look like card

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
}
