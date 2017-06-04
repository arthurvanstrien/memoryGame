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
    public CardList() {
    }

    //Create a cardlist with the given number of cards.
    public CardList(int numberOf) {
        for (int i = 0; i <= numberOf; i++){

            Card card = new Card();//todo maybe add parameters later
            card.setIndex(i);
            cards.add(card);
        }
    }

    //shuffles all the cards
    public ArrayList shuffle(){
        Collections.shuffle(cards);
        for (int i = 0; i < cards.size(); i++){
            this.cards.get(i).setIndex(i);
        } //makes the index match the index again
        System.out.println("cardlist shuffled");
        return cards;
    }

    //puts all the cards om the board/refreshes
    //GUI STUFF SHOULD NOT BE HERE. NEEDS TO BE MOVED URGENT.
    public void drawBoard(){

        for (int i = 0;  i < 24; i++){

            cards.get(i).putOnBoard();
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
    public void addCardByString(String value) {
        Card card = new Card(); //Why cant I give parameters to a card.
        cards.add(card);
    }

    //Prints the list of cards in the console.
    //This is for debugging.
    public void printCardList() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i));
        }
    }
}

