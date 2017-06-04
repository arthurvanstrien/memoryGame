import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tabitha on 24-5-2017.
 */
public class CardList {
    public ArrayList<Card> cards;

    public CardList(ArrayList<Card> cards) {
        this.cards = cards;
        System.out.println("Cardlist intialized");
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
    public void drawBoard(){
        for (int i = 0;  i < 24; i++){
            cards.get(i).putOnBoard();
        }
    }

    public void printCardList(){
        for (int i = 0; i < cards.size(); i++){
            System.out.println(cards.get(i));
        }
    }
}

