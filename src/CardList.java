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
    public CardList() {
    }

    //Create a cardlist with the given number of cards.
    public CardList(int numberOf) {

        for (int i = 0; i <= numberOf; i++){

            Card card = new Card();//todo maybe add parameters later
            card.setIndex(i);

            final int cardIndex= i;

            card.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println( "Index =" + cardIndex );
                }
            });

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

    //creates arraylist that has all the cards
    public static ArrayList<Card> createCardsArrayList(){
        ArrayList<Card> cards = new ArrayList<>(24);
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
            cards.add(new Card(names.get(i), i));
            cards.add(new Card(names.get(i), secondIndex));
            //adds two indentical cards to cards
            System.out.println(names.get(i));
        }
        return cards;
    }
}

