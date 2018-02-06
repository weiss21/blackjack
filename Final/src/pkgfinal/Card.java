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
public class Card {

    private Suit name; //Enumeration apparently keeps it to only four suits
    private int value; //numbers 2-10,k,q,j,a

    public Card(Suit name, int value) {
        this.name = name;
        
        
        this.value = value;
    }
    
    public void setValue(int v){
        value = v;
    }
    public int getValue() {
        return value;
    }

    public Suit getName() {
        return name;
    }
    
    @Override
    public String toString() {
        String result;

        switch (this.value) {
            case 1:
                result = "ACE";
                break;
            case 2:
                result = "TWO";
                break;
            case 3:
                result = "THREE";
                break;
            case 4:
                result = "FOUR";
                break;
            case 5:
                result = "FIVE";
                break;
            case 6:
                result = "SIX";
                break;
            case 7:
                result = "SEVEN";
                break;
            case 8:
                result = "EIGHT";
                break;
            case 9:
                result = "NINE";
                break;
            case 10:
                result = "TEN";
                break;
            case 11:
                result = "JACK";
                break;
            case 12:
                result = "QUEEN";
                break;
            case 13:
                result = "KING";
                break;
            default:
                result = "Probably not a card";

        }
        return result + " OF " + name.toString();
    }

}
