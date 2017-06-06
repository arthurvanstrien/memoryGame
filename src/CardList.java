import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tabitha on 24-5-2017.
 */
public class CardList {

    private ArrayList<Card> cards;
    private ArrayList<Integer> matchedCards;
    private int selectedCardOne;
    private int selectedCardTwo;
    private SendData sendData;
    private Main main;
    private int player;

    //Create an empty cardslist.
    //This is for construction a list of cards from the names recieved by the client.
    public CardList(SendData sendData, Main main, int player) {
        this.sendData = sendData;
        this.main = main;
        this.player = player;
        cards = new ArrayList<>();
        matchedCards = new ArrayList<>();
        selectedCardOne = -1;
        selectedCardTwo = -1;
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
            cards.add(new Card(names.get(i), i, sendData, this));
            cards.add(new Card(names.get(i), secondIndex, sendData, this));

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

    public void toggleCards(boolean value, int card1, int card2) {
        for (int i = 0; i < 24; i++) {
            if (!matchedCards.contains(i) && i != card1 && i != card2)
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
        Card card = new Card(name, index, sendData, this);
        cards.add(card);
    }

    public void addMatchedCard(int cardNumber) {
        matchedCards.add(cardNumber);
        cards.get(cardNumber).getButton().setEnabled(false);
    }

    public void cardSelected(int cardNumber) {
        if(cardNumber == selectedCardOne) {
            if(selectedCardTwo != -1)
                checkBothTurnedBack();
        }
        else if(cardNumber == selectedCardTwo) {
            checkBothTurnedBack();
        }
        else {
            if(selectedCardOne == -1) {
                System.out.println("SelectedCardOne");
                selectedCardOne = cardNumber;
            }
            else {
                System.out.println("SelectedCardTwo");
                selectedCardTwo = cardNumber;
                toggleCards(false, selectedCardOne, selectedCardTwo);
                match(selectedCardOne, cardNumber);
            }
        }
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

            if(!cards.get(cardOne).isFaceDown())
                cards.get(cardOne).turnAround();

            if (!cards.get(cardTwo).isFaceDown())
                cards.get(cardTwo).turnAround();

            sendData.match(cardOne, cardTwo);
            main.updateCardsLeft();
            matchedCards.add(cardOne);
            matchedCards.add(cardTwo);
            cards.get(cardOne).makeEmpty();
            cards.get(cardTwo).makeEmpty();
            cards.get(cardOne).getButton().setEnabled(false);
            cards.get(cardTwo).getButton().setEnabled(false);

            if(player == 1)
                main.updateScorePlayerOne();
            else
                main.updateScorePlayerTwo();

            checkBothTurnedBack();
        }
        else {
            System.out.println("No Match: " + cardOne + " and " +cardTwo);
        }
    }

    private void checkBothTurnedBack() {
        if(cards.get(selectedCardOne).isFaceDown() && cards.get(selectedCardTwo).isFaceDown()) {
            selectedCardOne = -1;
            selectedCardTwo = -1;
            toggleCards(false);
            sendData.endTurn();
        }
    }
}

