/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import java.util.Random;

/**
 *
 * @author WEISS
 */


public class Deck {

    private Card[] cards;
    private int numberOfCards;

    //default  deck no shuffling
    public Deck(){
        this(1, false);
    }
    
    public Deck(int numberOfDecks, boolean shuffle) {
        /*
         Allows more than one deck
         */
        numberOfCards = numberOfDecks * 52;
        cards = new Card[numberOfCards];

        //instead of doing by hand I can use a for loop
        int i = 0;
        
        for (int d = 0; d < numberOfDecks; d++) { //deck initialization
            for (int n = 0; n < 4; n++) { //name of card initialization
                for (int v = 1; v <= 13; v++) {//v has to be 1
                    cards[i] = new Card(Suit.values()[n], v);
                    i++; //adds a car one by one
                }
            }

        }
        
        // Important because this shuffles the deck after its initialized
        // do not remove thinking that it doesn't work
        
        if (shuffle) {
            shuffle();
        }
         
    }
    
    //shuffle card method
    public void shuffle(){
        
        //using bubble sort method
        Random random = new Random();
        
        Card temp; // temp card holder
        
        int j;
        for (int i = 0; i < numberOfCards; i++){
            j = random.nextInt(numberOfCards);
            
            temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }
    
    public Card dealCard(){
        
        //the top card
        Card top = cards[0];
        //now shift cards towards the top of deck
        for(int c = 1; c < numberOfCards; c++){
            cards[c - 1] = cards[c];
        }
        cards[numberOfCards - 1] = null; // leaves empty space in array
        
        numberOfCards--; //decreases it 1 at a time
        
        return top;
    }
    
    //get NumberOfCards left in deck
    public int getNumberOfCards(){
        return numberOfCards;
    }
    
    // Used to test out deck
    /*
    public void printDeck(int numToPrint){
        for(int c = 0; c < numToPrint; c++){
            System.out.printf("% 3d/%d %s \n", c + 1, this.numberOfCards,
                    this.cards[c].toString());
        }
        System.out.printf("\t[%d other]\n", this.numberOfCards - numToPrint);
    }
    */
    
    
    
    
    
}
