/*
 * This class is the parent class for all the cards in the game.
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */
package gteam;

public class Card 
{
    protected int cardColor; // 0 for red, 1 for blue, 2 for green, 3 for yellow
    protected String cardType; // "normal" or "bonus" or "wild"
    protected int cardValue; // 0-9 for normal cards, 20 for bonus cards, 50 for wild cards

    // Constructor
    public Card(int cardColor, String cardType, int cardValue) 
    {
        this.cardColor = cardColor;
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    // Getter method for cardType
    public String getCardType() 
    {
        return cardType;
    }

    // Getter method for cardColor
    public int getCardColor() 
    {
        return cardColor;
    }

    // Getter method for cardValue
    public int getCardValue() 
    {
        return cardValue;
    }
}

