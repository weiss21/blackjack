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
public class Dealer extends Player{
    
    public Dealer(String n){
        super(n, 0);
        emptyHand();
    }
    
    public Dealer(){
        super("Dealer", 0);
        emptyHand();
    }
    
    @Override
    public String getStats(){
        return " Player: "+ this.getName() + "\n"
                + "Amount of Wins: "+ this.getWins() +"\n"+
                "Amount of Losses: " + this.getLosses() +"\n"
                + "Number of Ties: " + this.getTies() + "\n"
                + "Number of Busts: " + this.getBusts() + "\n" +
                "Number of Blackjacks: " + this.getBlackjacks() + "\n";
    }
    public String getStatsMultiple(){
        return "Player: " + this.getName() + "\n"
                + "Number of Busts: " + this.getBusts() + "\n" +
                "Number of Blackjacks: " + this.getBlackjacks() + "\n";
    }
    
    public void printHand(boolean showCard) {
        System.out.printf("%s's cards:\n", getName());
        for (int n = 0; n < this.getNumberOfCards(); n++) {
            if (n == 0 && !showCard) {
                System.out.println(" [hidden]");
            } else {
                System.out.printf("  %s\n", this.getCardToString(n));
            }
        }
    }
    
}
