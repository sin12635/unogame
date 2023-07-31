/*
 * This class is used to create a player object
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */
package gteam;

import java.util.ArrayList;

public class Player 
{
    private int playerCount; // The number of the player
    private ArrayList<Card> cards; // The cards in hand
    private int indexChosen; // The index of the card chosen
    private int playerType; // The type of the player

    // Constructor
    public Player(int playerCount, int playerType) 
    {
        this.playerCount = playerCount;
        cards = new ArrayList<>();
        this.playerType = playerType;
    }

    // method to add card to hand
    public void addCard(Card card) 
    {
        cards.add(card);
    }

    // method to remove card from hand
    public void removeCard(int indexOfCard) 
    {
        cards.remove(indexOfCard);
    }

    // method to get the number of the player
    public int getPlayerCount() 
    {
        return playerCount;
    }

    // method to get the number of cards in hand
    public int getCardCount() 
    {
        return cards.size();
    }

    // method to get the cards in hand
    public ArrayList<Card> getCards() 
    {
        return cards;
    }

    // method to get the type of the player
    public int getPlayerType() 
    {
        return playerType;
    }

    // method to get the card chosen
    public Card getCard(int indexOfCard) 
    {
        indexChosen = indexOfCard;
        return cards.get(indexOfCard);
    }

    // method to get the index of the card chosen
    public void updateChanges() 
    {
        removeCard(indexChosen);
    }

    // method to check the card color
    public boolean checkCardColor(int color, int number, String type) 
    {
        // Loop to check if the player has a card to play
        for (Card i : cards) 
        {
            if (i.getCardColor() == color)
                return true;
            if (i instanceof RegularCard && (number != -1 && number != -2)) 
            {
                RegularCard card = (RegularCard) i;
                if (card.getCardCount() == number)
                    return true;
            }
            if (number == -1 && i instanceof BonusCard) 
            {
                BonusCard card = (BonusCard) i;
                if (card.getBonusType().equals(type)) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    // method to check if the card is draw card
    public boolean checkIfDraw() 
    {
        // Loop to check if the player has a card to play
        for (Card i : cards) 
        {
            if (i instanceof WildCard) 
            {
                if (((WildCard) i).getWildCardType().equals("wildDraw"))
                    return true;
            }
        }
        return false;
    }

    // method to check if the card is bonus draw card
    public boolean checkBonusDraw() 
    {
        // Loop to check if the player has a card to play
        for (Card i : cards) 
        {
            if (i instanceof BonusCard) 
            {
                if (((BonusCard) i).getBonusType().equals("draw"))
                    return true;
            }
        }
        return false;
    }

    // method to get the score of the player
    public int getScore() 
    {
        int score = 0;
        for (Card i : cards)
            score += i.getCardValue();
        return score;
    }
}

