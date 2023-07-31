/*
 * This class is for the computer player
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */
package gteam;

import java.util.ArrayList;

public class ComputerPlayer extends Player 
{
    private Card computerMiddleCard; // The card in the middle of the table
    private int colorOfMiddleWildCard; // The color of the middle wild card

    // Constructor
    public ComputerPlayer(int playerCount) 
    {
        super(playerCount, 1);
    }

    // method to send info to computer player
    public Card sendInfo(int colorOfMiddleWildCard, Card computerMiddleCard) 
    {
        this.colorOfMiddleWildCard = colorOfMiddleWildCard;
        this.computerMiddleCard = computerMiddleCard;
        return selectCard();
    }

    // method for computer player to select card
    private Card selectCard() 
    {
        ArrayList<Card> handCard = getCards(); // get the cards in hand

        // check if the middle card is a wild card
        if (computerMiddleCard instanceof WildCard) 
        {
            WildCard newMiddleCard = (WildCard) computerMiddleCard;

            // check if the new middle card is a wild draw card
            if (newMiddleCard.getWildCardType().equals("wildDraw") && checkIfDraw()
                    && !checkCardColor(colorOfMiddleWildCard, -2, null)) 
                    {
                // check if there is a wild draw card in hand                        
                for (int i = 0; i < handCard.size(); i++) 
                {
                    // if there is a wild draw card in hand, return the card
                    if (handCard.get(i).getCardType().equals("wild")) 
                    {
                        WildCard temp = (WildCard) handCard.get(i);
                        // check if the wild card is a wild draw card
                        if (temp.getWildCardType().equals("wildDraw"))
                            return getCard(i);
                    }
                }
            }
        }

        // check if the middle card is a bonus card
        if (computerMiddleCard instanceof BonusCard) 
        {
            BonusCard newMiddleCard = (BonusCard) computerMiddleCard;

            // check if the new middle card is a draw card
            if (newMiddleCard.getBonusType().equals("draw") && checkBonusDraw()) 
            {
                // check if there is a draw card in hand                
                for (Card card : handCard) 
                {
                    // if there is a draw card in hand, return the card
                    if (card instanceof BonusCard) 
                    {
                        BonusCard bonusCard = (BonusCard) card;

                        // check if the card is a draw card
                        if (bonusCard.getBonusType().equals("draw")) 
                        {
                            return getCard(handCard.indexOf(card));
                        }
                    }
                }
            }
        }

        // check if there is a card that can be played
        for (Card card : handCard) 
        {
            // if there is a card that can be played, return the card
            if (checkWithMiddleCard(card)) 
            {
                return getCard(handCard.indexOf(card));
            }
        }
        return null;
    }

    // method to check if there is a draw card in hand
    private boolean checkWithMiddleCard(Card card) 
    {
        // check if the middle card is a wild card
        if (card instanceof WildCard) 
        {
            // check if the new middle card is a wild draw card
            if (computerMiddleCard instanceof WildCard) 
            {
                return !checkCardColor(colorOfMiddleWildCard, -2, null);
            } 
            else 
            {
                // check if the new middle card is a draw card
                if (computerMiddleCard instanceof BonusCard) 
                {
                    return !checkCardColor(computerMiddleCard.getCardColor(), -1,
                            ((BonusCard) computerMiddleCard).getBonusType());
                } 
                else 
                {
                    RegularCard newMiddleCard = (RegularCard) computerMiddleCard;
                    return !checkCardColor(computerMiddleCard.getCardColor(), newMiddleCard.getCardCount(), null);
                }
            }
        }
        
        // check if the middle card is a bonus card
        else if (card instanceof BonusCard) 
        {

            BonusCard newCard = (BonusCard) card;

            // check if the new middle card is a wild draw card
            if (computerMiddleCard instanceof WildCard) 
            {
                return newCard.getCardColor() == colorOfMiddleWildCard;
            }
            
            // check if the new middle card is a draw card
            else if (computerMiddleCard instanceof BonusCard) 
            {
                BonusCard newMiddleCard = (BonusCard) computerMiddleCard;
                return newMiddleCard.getCardColor() == newCard.getCardColor()
                        || newMiddleCard.getBonusType().equals(newCard.getBonusType());
            }             
            else 
            {
                RegularCard newMiddleCard = (RegularCard) computerMiddleCard;
                return newMiddleCard.getCardColor() == newCard.getCardColor();
            }
        } 
        else 
        {
            RegularCard newCard = (RegularCard) card;

            // check if the new middle card is a wild draw card
            if (computerMiddleCard instanceof WildCard) 
            {
                return newCard.getCardColor() == colorOfMiddleWildCard;
            } 

            // check if the new middle card is a draw card
            else if (computerMiddleCard instanceof BonusCard) 
            {
                BonusCard newMiddleCard = (BonusCard) computerMiddleCard;
                return newMiddleCard.getCardColor() == newCard.getCardColor();
            } 
            else 
            {
                RegularCard newMiddleCard = (RegularCard) computerMiddleCard;
                return newMiddleCard.getCardColor() == newCard.getCardColor()
                        || newMiddleCard.getCardCount() == newCard.getCardCount();
            }
        }
    }
}

