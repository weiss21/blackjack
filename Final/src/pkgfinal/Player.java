/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

/**
 *
 * @author WEISS
 */
public class Player {

    private String name;
    private int id;
    private int rounds;
    
    private int wins;
    private int losses;
    private int ties;
    private int busts = 0;
    private int blackjacks = 0;

    private Card[] hand = new Card[10];
    private int numOfCards;

    //initializes player with an empty hand
    public Player(String name, int id) {
        this.name = name;
        this.id = id; //to account for array being 0
        emptyHand();
        
        wins =0;
        losses = 0;
    }

    public String getName() {
        return name;
    }
    public int getId(){
        return id;
    }
    public int getNumberOfCards(){
        return numOfCards;
    }
    
    public String getCardToString(int n){
        return hand[n].toString();
    }

    public void emptyHand() {
        for (int i = 0; i < 5; i++) {
            hand[i] = null;
        }
        numOfCards = 0;
    }

    public boolean addCard(Card hit) {

        //limit so you don't go over 10 cards
        if (numOfCards == 10) {
            System.err.println("You already have 5 cards. "
                    + "Cannot add another. \n");
        }
        //actually adding cards
        hand[numOfCards] = hit;
        numOfCards++;

        return (getHandSum() <= 21);
    }
    public int numberOfCards(){
        return numOfCards;
    }

    //summation of the values of the cards in the 
    //player's deck
    public int getHandSum() {
        int handSum = 0;
        int cardNumber;
        int aces = 0; //number of acces

       //hand summation
        for (int a = 0; a < numOfCards; a++) {
            // get the number for the current card
            cardNumber = hand[a].getValue();

            if (cardNumber == 1) { // if we have an ace
                aces++;
                handSum += 11;
            } else if (cardNumber > 10) { //Basically a face card
                handSum += 10;
            } else {
                handSum += cardNumber;
            }
        }
        //So that we don't bust if greater than 21 aces will revert to 1;
        while (handSum > 21 && aces > 0) {
            handSum -= 10;
            aces--;
        }
        return handSum;
    }
    
    

    /**
     * Prints cards in players hand
     *
     * @param showCard
     */
    public void printHand() {
        System.out.printf("%s's cards:\n", name);
        for (int n = 0; n < numOfCards; n++) {
            System.out.printf("  %s\n", hand[n].toString());
        }
    }
    
    public void setRounds( int r){
        rounds = r;
    }
    
    public int getRounds(){
        return rounds;
    }
    public void addWins(){
        wins = wins + 1;
    }
    public int getWins(){
        return wins;
    }
    public int getLosses(){
        return losses;
    }
    public void addLosses(){
        losses = losses + 1;
    }
    public void addTies(){
        ties++;
    }
    public int getTies(){
        return ties;
    }
    public void addBusts(){
        busts++;
    }
    public int getBusts(){
        return busts;
    }
    public void addBlackjacks(){
        blackjacks++;
    }
    public int getBlackjacks(){
        return blackjacks;
    }
    public String getStats(){
        return "ID: "+ id +" Player: "+ name + "\n"
                + "Amount of Wins: "+ wins +"\n"+
                "Amount of Losses: " + losses +"\n" +
                "Number of Ties: " + ties + "\n" +
                "Number of Busts: " + busts + "\n" +
                "Number of Blackjacks: " + blackjacks + "\n";
    }
    
}
