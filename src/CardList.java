import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tabitha on 24-5-2017.
 */
public class CardList {
    public ArrayList<Card> cards;

    public void Cardlist(){
        for (int i = 0; i <= 24; i++){
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
    public void drawBoard(){
        for (int i = 0;  i < 24; i++){
            cards.get(i).putOnBoard();
        }
    }
}

