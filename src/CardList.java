import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tabitha on 24-5-2017.
 */
public class CardList {

    private ArrayList<Card> cards;

    public CardList() {

    }

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
        return cards;
    }

    //puts all the cards om the board/refreshes
    //GUI STUFF SHOULD NOT BE HERE. NEEDS TO BE MOVED URGENT.
    public void drawBoard(){

        for (int i = 0;  i < 24; i++){

            cards.get(i).putOnBoard();
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int getNumberOf(){
        return cards.size();
    }

    //Cards can be added by just there image string.
    public void addCardByString(String value) {
        Card card = new Card(); //Why cant I give parameters to a card.
        cards.add(card);
    }
}

