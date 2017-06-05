import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tabitha on 24-5-2017.
 */
public class CardList {

    private ArrayList<Card> cards;
    private ArrayList<Integer> matchedCards;

    //Create an empty cardslist.
    //This is for construction a list of cards from the names recieved by the client.
    public CardList() {
        cards = new ArrayList<>();
        matchedCards = new ArrayList<>();
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

    //Fills the arrayList with cards.
    public void fillList(SendData sendData){
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
            cards.add(new Card(names.get(i), i, sendData));
            cards.add(new Card(names.get(i), secondIndex, sendData));

        }
    }

    //This method toggles all of the cards in the arrayList on or of.
    //When turned of, they will be disabled in the GUI (cannot be clicked).
    //If the card is matched, it wont get enabled again when its the players turn.
    public void toggleCards(Boolean value) {
        for (int i = 0; i < 24; i++) {
            if (!matchedCards.contains(i))
                cards.get(i).getButton().setEnabled(value);
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
    public void addCardByString(String name, int index, SendData sendData) {
        Card card = new Card(name, index, sendData);
        cards.add(card);
    }

    public void addMatchedCard(int cardNumber) {
        matchedCards.add(cardNumber);
        cards.get(cardNumber).getButton().setEnabled(false);
    }

    public void cardSelected(int cardNumber) {
        if(selectedCardOne == -1)
            selectedCardOne = cardNumber;
        else
            match(selectedCardOne, cardNumber);
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

    private void match(int cardOne, int cardTwo) {
        if(cards.get(cardOne).getName().equals(cards.get(cardTwo).getName())) {
            System.out.println("Match: " + cardOne + " and " +cardTwo);
            sendData.match(cardOne, cardTwo);
            main.updateCardsLeft();
            matchedCards.add(cardOne);
            matchedCards.add(cardTwo);

            if(player == 1)
                main.updateScorePlayerOne();
            else
                main.updateScorePlayerTwo();
            
            sendData.endTurn();
        }
        else {
            System.out.println("No Match: " + cardOne + " and " +cardTwo);
            sendData.endTurn();
        }
    }
}

