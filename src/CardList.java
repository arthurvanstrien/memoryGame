import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tabitha on 24-5-2017.
 */
public class CardList {

    private ArrayList<Card> cards;

    public CardList(ArrayList<Card> cards) {
        this.cards = cards;
        System.out.println("Cardlist intialized");
    }

    //Create an empty cardslist.
    //This is for construction a list of cards from the names recieved by the client.
    public CardList() {
        cards = new ArrayList<>();
    }

    //shuffles the cards in the ArrayList of the CardList class.
    public void shuffle(){

        Collections.shuffle(cards);

        //makes the index match the index again
        for (int i = 0; i < cards.size(); i++){
            this.cards.get(i).setIndex(i);
        }
        System.out.println("cardlist shuffled");
    }

    //puts all the cards om the board/refreshes
    public void drawOnBoard(){

        for (int i = 0;  i < 24; i++){
            cards.get(i).putOnBoard();
        }
    }

    //Fills the arrayList with cards.
    public void fillList(){
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

            //adds two indentical cards to cards
            cards.add(new Card(names.get(i), i));
            cards.add(new Card(names.get(i), secondIndex));

        }
    }

    //Returns an arrayList with all the cards.
    public ArrayList<Card> getCards() {
        return cards;
    }

    //Returns the card that is located in the list on the given index.
    public Card getCard(int index) {
        return cards.get(index);
    }

    //Returns the number of cards in the list.
    public int getNumberOf(){
        return cards.size();
    }

    //Cards can be added by just there image string.
    public void addCardByString(String name, int index) {
        Card card = new Card(name, index);
        cards.add(card);
    }

    //Prints the list of cards in the console.
    //This is for debugging.
    public void printCardList() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i));
        }
    }

    //Prints the names of the cards in the list of cards in the console.
    //This is for debugging.
    public void printCardListNames() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i).getName());
        }
    }
}

