/*
 * This class is the wild card class that extends the card class and has a wild card type.
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */

package gteam;

public class WildCard extends Card 
{
    private String wildCardType; // The type of the wild card

    // Constructor
    public WildCard(int cardColor, String wildCardType)
    {
        super(cardColor, "wild", 50);
        this.wildCardType = wildCardType;
    }

    // method to get the type of the wild card
    public String getWildCardType() 
    {
        return wildCardType;
    }
}
