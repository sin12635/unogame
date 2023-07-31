/*
 * This class represents a bonus card in the game of Uno.
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */
package gteam;

public class BonusCard extends Card 
{
    private String bonusType; // "draw2" or "reverse" or "skip"

    // Constructor
    public BonusCard(int cardColor, String bonusType) 
    {
        super(cardColor, "bonus", 20);
        this.bonusType = bonusType;
    }

    // Getter method for bonusType
    public String getBonusType() 
    {
        return bonusType;
    }
}

