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
import java.util.Scanner;

public class Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Player[] players = new Player[4];
        Dealer dealer = new Dealer();

        //int numberOfRounds = 1; //number of initial rounds
        int totalNumberOfRounds; // total number of rounds to play
        int numberOfTimesShuffled = 0;
        String result;

        boolean playerDone;
        boolean dealerDone;
        //Used for inputs
        Scanner input = new Scanner(System.in);

        System.out.println("How many Players are we playing with? \n");
        int playerNumber;
        playerNumber = input.nextInt();

        //initialize amount of players
        if (playerNumber > 5) {
            System.out.println("Sorry only up to five players allowed.");
        } else {
            for (int i = 0; i < playerNumber; i++) {
                players = new Player[playerNumber];
            }
        }

        //input player names and id
        for (int j = 0; j < playerNumber; j++) {
            System.out.println("Player: " + (j + 1) + ", Please write your name here.\n");
            String names;
            int numbers = j + 1;
            names = input.next();
            players[j] = new Player(names, numbers);
        }

        //Deal cards for all hands
        System.out.println("How many Decks would you like to play with?");
        int deckNumber;
        deckNumber = input.nextInt();

        //initialize Deck of cards
        Deck myDeck = new Deck(deckNumber, true);

        System.out.println("How many rounds would you like to play?");
        totalNumberOfRounds = input.nextInt();
        totalNumberOfRounds++;

        //loop of blackjack "rounds" begins
        for (int numberOfRounds = 1; numberOfRounds < totalNumberOfRounds; numberOfRounds++) {
            System.out.println("Round: " + numberOfRounds);
            //adding first cards for players and dealer
            for (int k = 0; k < playerNumber; k++) {
                players[k].addCard(myDeck.dealCard());
            }
            dealer.addCard(myDeck.dealCard());

            //adding second card
            for (int k = 0; k < playerNumber; k++) {
                players[k].addCard(myDeck.dealCard());
            }
            dealer.addCard(myDeck.dealCard());

            System.out.println("Cards are dealt\n");

            //Showing player hands
            for (int i = 0; i < playerNumber; i++) {
                players[i].printHand();
            }
            //showing dealer hands, except for first card
            dealer.printHand(false);

            /**
             * Here is the work involved for the game play after cards are dealt
             */
            //Player's turn
            //playerDone
            playerDone = false;

            for (int i = 0; i < playerNumber; i++) {
                while (!playerDone) {
                    System.out.println("\n");
                    System.out.println("Player: " + (i + 1) + ": \n Hit or Stay (Enter H or S): ");
                    result = input.next();
                    System.out.println("\n");

                    if ("S".equals(result) || "s".equals(result)) {
                        playerDone = true;
                    } else {
                        players[i].addCard(myDeck.dealCard());
                        players[i].printHand();
                    }
                    if (players[i].getHandSum() > 21) {
                        System.out.println("Player: " + (i + 1) + " has busted!\n");
                        players[i].addBusts();
                        playerDone = true;
                    }

                    if (players[i].getHandSum() == 21 && players[i].numberOfCards() == 2) {
                        System.out.println("You have Blackjack!\n");
                        players[i].addBlackjacks();
                        playerDone = true;
                    }
                }
                playerDone = false;
            }

            //dealer's turn
            dealerDone = false;
            while (!dealerDone) {
                if (dealer.getHandSum() < 17) {
                    System.out.println("The Dealer hits\n");
                    dealer.addCard(myDeck.dealCard());
                    dealer.printHand(true);
                } else {
                    System.out.println("The Dealer stays\n");
                    dealer.printHand();
                    dealerDone = true;
                }
                if (dealer.getHandSum() > 21) {
                    System.out.println("Dealer has busted\n");
                    dealer.addBusts();
                    dealerDone = true;
                }
                if (dealer.getHandSum() == 21 && dealer.numberOfCards() == 2) {
                    System.out.println("Dealer has Blackjack!\n");
                    dealer.addBlackjacks();
                    dealerDone = true;
                }
            }
            //print hands and see who wins or losses
            System.out.println("\n");
            for (int c = 0; c < playerNumber; c++) {
                players[c].printHand();
                System.out.println("\n");
            }
            dealer.printHand();
            int playerSum;
            int dealerSum = dealer.getHandSum();
            for (int c = 0; c < playerNumber; c++) {
                playerSum = players[c].getHandSum();
                if (playerSum > dealerSum && playerSum <= 21) {
                    System.out.println("\n" + "Player " + (c + 1) + " beat the Dealer!\n");
                    players[c].addWins();
                    if (playerNumber == 1) {
                        dealer.addLosses();
                    }
                } else if (playerSum <= 21 && dealerSum > 21) {
                    System.out.println("\n" + "Dealer busted so you win by default. Player: " + players[c].getId());
                    players[c].addWins();
                } else if (playerSum == dealerSum) {
                    if (playerSum == 21) {
                        System.out.println("You and dealer have blackjack. Player: " + players[c].getId());
                        players[c].addWins();
                        players[c].addBlackjacks();
                        dealer.addWins();
                        dealer.addBlackjacks();
                    } else {
                        System.out.println("Dealer just tied you Player: " + players[c].getId() + "\n");
                        players[c].addTies();
                        dealer.addTies();
                    }
                } else {
                    System.out.println("Dealer beat you Player: " + players[c].getId() + "\n");
                    players[c].addLosses();
                    if (playerNumber == 1) {
                        dealer.addWins();
                    }
                }

                //empty hands of players
                players[c].emptyHand();
            }
            //empty dealer hand
            dealer.emptyHand();

            //Shuffle Card if there are 10 cards left in deck
            if (myDeck.getNumberOfCards() <= 10) {
                myDeck = new Deck(deckNumber, true);

                numberOfTimesShuffled++;
            }
            
            //End of Round loop
        }

        //close scanner
        input.close();
        //stats shown here
        for (int i = 0;
                i < playerNumber;
                i++) {
            System.out.print(players[i].getStats());
            System.out.println("\n");
        }
        if (playerNumber
                == 1) {
            System.out.print(dealer.getStats());
            System.out.println("\n");
        } else {
            System.out.print(dealer.getStatsMultiple());
            System.out.println("\n");
        }

        System.out.println("Number of times shuffled: " + numberOfTimesShuffled);

    }
}
