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
    private ImageIcon emptyIcon;
    private boolean faceDown;
    private boolean onBoard;
    private int index; //place on the board and index in CardsList
    private JButton button;
    private SendData sendData;
    private CardList cards;

    public Card(String name, int index, SendData sendData, CardList cards){
        this.name = name;
        this.sendData = sendData;
        this.index = index;
        this.cards = cards;
        onBoard = true;
        faceDown = true;

        try {
            image = new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/" + name + ".jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create the imageIcon that is used as back by all cards.
        try {
            backImage = new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/back.jpg")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create the empty icon used when the card is mathed.
        try {
            emptyIcon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/empty.jpg")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        button = new JButton(backImage);
        button.addActionListener(this);
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

    public JButton getButton() {
        return button;
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

    //When the card is guessed make it look like and empty card.
    public void makeEmpty() {
        button.setIcon(emptyIcon);
    }

    public String toString(){
        return "name: " + this.name + " index: " + this.index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        turnAround();
        sendData.clicked(index);
        cards.cardSelected(index);
    }
}
