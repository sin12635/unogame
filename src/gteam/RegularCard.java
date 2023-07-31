/*
 * This class is used to create a regular card object
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */

package gteam;

public class RegularCard extends Card 
{
    private int cardCount; // The number of the card

    // Constructor
    public RegularCard(int cardColor, int cardCount) 
    {
        super(cardColor, "normal", cardCount);
        this.cardCount = cardCount;
    }

    // method to get the number of the card
    public int getCardCount() 
    {
        return cardCount;
    }
}

