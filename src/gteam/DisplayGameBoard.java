/*
 * This class is used to display the game board
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */

package gteam;

import java.util.ArrayList;

public class DisplayGameBoard 
{
    private ArrayList<Card> cards; // cards on the board
    private int graphicType; // 1 for text, 2 for graphic

    // constructor
    public DisplayGameBoard(ArrayList<Card> cards, int graphicType) 
    {
        this.cards = cards;
        this.graphicType = graphicType;
        printBoard(1);
    }

    // constructor 
    public DisplayGameBoard(Card middleCard, int graphicType) 
    {
        cards = new ArrayList<>();
        cards.add(middleCard);
        this.graphicType = graphicType;
        printBoard(0);
    }

    // method to print the board
    private void printBoard(int middleCard) 
    {
        // loop to print the board
        for (int i = 0; i < cards.size(); i++) 
        {
            // check if graphic type is text
            if (graphicType == 1)
                System.out.print("********* ");

            else 
            {
                switch (cards.get(i).getCardColor()) 
                {
                    case 1:
                        System.out.print("\u001b[31m" + "*********" + "\u001b[0m" + " ");
                        break;
                    case 2:
                        System.out.print("\u001b[34m" + "*********" + "\u001b[0m" + " ");
                        break;
                    case 3:
                        System.out.print("\u001b[32m" + "*********" + "\u001b[0m" + " ");
                        break;
                    case 4:
                        System.out.print("\u001b[33m" + "*********" + "\u001b[0m" + " ");
                        break;
                    default:
                        System.out.print("********* ");
                }
            }
        }
        System.out.print("\n");
        
        for (int i = 0; i < cards.size(); i++) 
        {
            if (cards.get(i) instanceof RegularCard || cards.get(i) instanceof BonusCard) 
            {
                Card tempCard = (Card) cards.get(i);
                if (graphicType == 1) {
                    String color = null;
                    switch (tempCard.getCardColor()) 
                    {
                        case 1:
                            color = "RED";
                            break;
                        case 2:
                            color = "BLU";
                            break;
                        case 3:
                            color = "GRN";
                            break;
                        case 4:
                            color = "YLW";
                            break;
                    }
                    System.out.print("*  " + color + "  * ");
                } 
                else 
                {
                    switch (tempCard.getCardColor()) 
                    {
                        case 1:
                            System.out.print("\u001b[31m" + "*       *" + "\u001b[0m" + " ");
                            break;
                        case 2:
                            System.out.print("\u001b[34m" + "*       *" + "\u001b[0m" + " ");
                            break;
                        case 3:
                            System.out.print("\u001b[32m" + "*       *" + "\u001b[0m" + " ");
                            break;
                        case 4:
                            System.out.print("\u001b[33m" + "*       *" + "\u001b[0m" + " ");
                            break;
                    }
                }
            } 
            else 
            {
                WildCard wildCard = (WildCard) cards.get(i);
                if (wildCard.getWildCardType().equals("wildDraw"))
                    System.out.print("* Wild  * ");
                else
                    System.out.print("* Color * ");
            }
        }
        System.out.print("\n");
        for (int i = 0; i < cards.size(); i++) 
        {
            if (cards.get(i) instanceof RegularCard) 
            {
                RegularCard normalCard = (RegularCard) cards.get(i);
                if (graphicType == 1)
                    System.out.print("*   " + normalCard.getCardCount() + "   * ");
                else 
                {
                    switch (normalCard.getCardColor()) 
                    {
                        case 1:
                            System.out.print(
                                    "\u001b[31m" + "*   " + normalCard.getCardCount() + "   *" + "\u001b[0m" + " ");
                            break;
                        case 2:
                            System.out.print(
                                    "\u001b[34m" + "*   " + normalCard.getCardCount() + "   *" + "\u001b[0m" + " ");
                            break;
                        case 3:
                            System.out.print(
                                    "\u001b[32m" + "*   " + normalCard.getCardCount() + "   *" + "\u001b[0m" + " ");
                            break;
                        case 4:
                            System.out.print(
                                    "\u001b[33m" + "*   " + normalCard.getCardCount() + "   *" + "\u001b[0m" + " ");
                            break;
                    }
                }
            } 
            else if (cards.get(i) instanceof WildCard) 
            {
                WildCard wildCard = (WildCard) cards.get(i);
                if (wildCard.getWildCardType().equals("wildDraw"))
                    System.out.print("* Draw  * ");
                else
                    System.out.print("*       * ");
            } 
            else 
            {
                BonusCard bonusCard = (BonusCard) cards.get(i);
                if (graphicType == 1) 
                {
                    if (bonusCard.getBonusType().equals("skip"))
                        System.out.print("* Skip  * ");
                    else if (bonusCard.getBonusType().equals("reverse"))
                        System.out.print("*Reverse* ");
                    else
                        System.out.print("* Draw  * ");
                } 
                else 
                {
                    if (bonusCard.getBonusType().equals("skip")) 
                    {
                        switch (bonusCard.getCardColor()) 
                        {
                            case 1:
                                System.out.print("\u001b[31m" + "* Skip  * " + "\u001b[0m");
                                break;
                            case 2:
                                System.out.print("\u001b[34m" + "* Skip  * " + "\u001b[0m");
                                break;
                            case 3:
                                System.out.print("\u001b[32m" + "* Skip  * " + "\u001b[0m");
                                break;
                            case 4:
                                System.out.print("\u001b[33m" + "* Skip  * " + "\u001b[0m");
                                break;
                        }
                    } 
                    else if (bonusCard.getBonusType().equals("reverse")) 
                    {
                        switch (bonusCard.getCardColor()) 
                        {
                            case 1:
                                System.out.print("\u001b[31m" + "*Reverse* " + "\u001b[0m");
                                break;
                            case 2:
                                System.out.print("\u001b[34m" + "*Reverse* " + "\u001b[0m");
                                break;
                            case 3:
                                System.out.print("\u001b[32m" + "*Reverse* " + "\u001b[0m");
                                break;
                            case 4:
                                System.out.print("\u001b[33m" + "*Reverse* " + "\u001b[0m");
                                break;
                        }
                    } 
                    else 
                    {
                        switch (bonusCard.getCardColor()) 
                        {
                            case 1:
                                System.out.print("\u001b[31m" + "* Draw  * " + "\u001b[0m");
                                break;
                            case 2:
                                System.out.print("\u001b[34m" + "* Draw  * " + "\u001b[0m");
                                break;
                            case 3:
                                System.out.print("\u001b[32m" + "* Draw  * " + "\u001b[0m");
                                break;
                            case 4:
                                System.out.print("\u001b[33m" + "* Draw  * " + "\u001b[0m");
                                break;
                        }
                    }
                }
            }
        }
        System.out.print("\n");
        for (int i = 0; i < cards.size(); i++) 
        {
            if (graphicType == 1)
                System.out.print("********* ");
            else 
            {
                switch (cards.get(i).getCardColor()) 
                {
                    case 1:
                        System.out.print("\u001b[31m" + "*********" + "\u001b[0m" + " ");
                        break;
                    case 2:
                        System.out.print("\u001b[34m" + "*********" + "\u001b[0m" + " ");
                        break;
                    case 3:
                        System.out.print("\u001b[32m" + "*********" + "\u001b[0m" + " ");
                        break;
                    case 4:
                        System.out.print("\u001b[33m" + "*********" + "\u001b[0m" + " ");
                        break;
                    default:
                        System.out.print("********* ");
                }
            }
        }
        System.out.print("\n");
        if (middleCard == 1) 
        {
            for (int i = 0; i < cards.size(); i++) 
            {
                String format = i < 10 ? "   (%d)    " : "   (%d)   ";
                System.out.print(String.format(format, i + 1));
            }
            System.out.print("\n");
        }
    }
}

