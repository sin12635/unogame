/*
 * This class is used to create the game board
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */

package gteam;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameBoard 
{
    private ArrayList<Player> players; // list of players
    private ArrayList<Card> cards; // list of cards

    private int isWildCardDraw; // number of wild cards
    private int wildCardColor; // color of wild card
    private int isGameDraw; // number of draw cards
    private boolean isClockwise; // direction of the game
    private Card middleCard; // the card in the middle of the table
    private int turn; // turn of the player
    private int graphicType; // graphic type
    private boolean isFirstRound; // check if it is the first round

    // Constructor
    public GameBoard(int totalGamePlayers, int gameMode, int graphicType) 
    {
        players = new ArrayList<>();
        cards = new ArrayList<>();
        middleCard = null;
        isWildCardDraw = 0;
        wildCardColor = 0;
        this.graphicType = graphicType;
        Random r = new Random();
        this.turn = r.nextInt(totalGamePlayers);
        this.isClockwise = true;
        this.isFirstRound = true;
        createCardsGame();
        createGamePlayers(gameMode, totalGamePlayers);
        setMiddleCard();
    }

    // Method to set the first round
    public void setFirstRound() 
    {
        isFirstRound = false;
    }

    // Method to get the turn
    public int getTurn() 
    {
        return turn;
    }

    // Method to get the middle card
    public boolean isClockwise() 
    {
        return isClockwise;
    }

    // Method to create cards for the game
    private void createCardsGame() 
    {
        ArrayList<Card> tempList = new ArrayList<>(); // temporary list of cards

        // Loop to create cards
        for (int i = 1; i < 5; i++) 
        {            
            for (int j = 0; j < 10; j++) 
            {
                if (j == 0) 
                {
                    RegularCard tempCard = new RegularCard(i, j);
                    tempList.add(tempCard);
                } 
                else 
                {
                    RegularCard temp1 = new RegularCard(i, j);
                    RegularCard temp2 = new RegularCard(i, j);
                    tempList.add(temp1);
                    tempList.add(temp2);
                }
            }
            // Loop to create bonus cards
            for (String bonusType : new String[] { "reverse", "skip", "draw" }) 
            {
                BonusCard tempBonus1 = new BonusCard(i, bonusType);
                BonusCard tempBonus2 = new BonusCard(i, bonusType);
                tempList.add(tempBonus1);
                tempList.add(tempBonus2);
            }
        }

        // Loop to create wild cards
        for (int i = 0; i < 4; i++) 
        {
            WildCard tempWild1 = new WildCard(0, "wildDraw");
            WildCard tempWild2 = new WildCard(0, "changeColor");
            tempList.add(tempWild1);
            tempList.add(tempWild2);
        }

        Random random = new Random();

        // Loop to shuffle cards
        while (true) 
        {
            if (tempList.size() == 0)
                break;
            int index = random.nextInt(tempList.size());
            cards.add(tempList.get(index));
            tempList.remove(index);
        }
    }

    // Method to create players for the game
    private void createGamePlayers(int gameMode, int totalGamePlayers) 
    {
        if (gameMode == 1) 
        {
            Player tempPlayer = new Player(0, 0);
            players.add(tempPlayer);

            // Loop to create computer players
            for (int i = 1; i < totalGamePlayers; i++) 
            {
                ComputerPlayer tempCom = new ComputerPlayer(i);
                players.add(tempCom);
            }
        } 
        else if (gameMode == 2) 
        {
            // Loop to create players
            for (int i = 0; i < totalGamePlayers; i++) 
            {
                Player tempCom = new Player(i, 0);
                players.add(tempCom);
            }
        }

        // Loop to distribute cards
        for (int i = 0; i < players.size(); i++) 
        {
            for (int j = 0; j < 7; j++) 
            {
                distributeCards(i);
            }
        }
        setMiddleCard();
    }

    // Method to set the middle card
    private void setMiddleCard() 
    {
        int i = 0;

        // Loop to set the middle card
        while (true) 
        {
            if (!(cards.get(i) instanceof WildCard)) 
            {
                middleCard = cards.get(i);
                cards.remove(i);
                break;
            } 
            else 
            {
                i++;
            }
        }
    }

    // Method to shuffle cards
    public void shuffleCards() 
    {
        ArrayList<Card> templist = new ArrayList<>(); // temporary list of cards
        Random random = new Random();

        // Loop to shuffle cards
        while (true) 
        {
            if (cards.size() == 0)
                break;
            int index = random.nextInt(cards.size());
            templist.add(cards.get(index));
            cards.remove(index);
        }
        cards = templist;
    }

    // Method to distribute cards
    public void distributeCards(int index) 
    {
        players.get(index).addCard(cards.get(0));
        cards.remove(0);
    }

    // Method to print players
    public void checkPlayerTurn() 
    {
        if (isClockwise) 
        {
            turn++;
            if (turn > players.size() - 1) 
            {
                turn = 0;
            }
        } 
        else 
        {
            turn--;
            if (turn < 0) 
            {
                turn = players.size() - 1;
            }
        }
    }

    // Method to get the number of cards
    public int getPlayerCardsCount() 
    {
        if (players.get(turn).getPlayerType() == 0) 
        {
            return players.get(turn).getCardCount();
        }
        return 0;
    }

    // Method to play turn
    public boolean playTurn(int index) 
    {
        Card temp = players.get(turn).getCard(index);

        if (checkCard(temp)) 
        {
            cards.add(middleCard);
            middleCard = temp;
            players.get(turn).updateChanges();
            checkMiddle();
            return true;
        } 
        else 
        {
            return false;
        }
    }

    // Method to play turn
    public void playTurn(Card temp) 
    {
        cards.add(middleCard);
        middleCard = temp;
        players.get(turn).updateChanges();
        checkMiddle();
    }

    // Method to check the middle card
    public void checkMiddle() 
    {
        if (middleCard instanceof WildCard) 
        {
            WildCard newMiddleCard = (WildCard) middleCard;

            if (newMiddleCard.getWildCardType().equals("wildDraw")) 
            {
                isWildCardDraw++;
                if (isComputerTurn()) 
                {
                    Random random = new Random();
                    wildCardColor = random.nextInt(4) + 1;
                } 
                else 
                {
                    Scanner scanner = new Scanner(System.in);
                    while (true) 
                    {
                        System.out.print(
                                "\nPlease select a color by pressing corresponding key: \n1 for red, \n2 for blue, \n3 for green, \n4 for yellow.: ");
                        wildCardColor = scanner.nextInt();
                        if (wildCardColor < 5 && wildCardColor > 0)
                            break;
                    }
                }
                checkPlayerTurn();
                
                if (!players.get(turn).checkIfDraw() || players.get(turn).checkCardColor(wildCardColor, -2, null)) 
                {
                    System.out.println(String.format(
                            "\nSorry Player %d, The Wild Draw Got You. You Get %d Cards. And You Lose A Turn.\n",
                            (turn + 1), (4 * isWildCardDraw)));
                    int cardsToGive = isWildCardDraw * 4;
                    for (int i = 0; i < cardsToGive; i++) 
                    {
                        distributeCards(turn);
                    }
                    isWildCardDraw = 0;
                    checkPlayerTurn();
                }
            } 
            else 
            {
                if (isComputerTurn()) 
                {
                    Random random = new Random();
                    wildCardColor = random.nextInt(4) + 1;
                } 
                else 
                {
                    Scanner scanner = new Scanner(System.in);
                    while (true) 
                    {
                        System.out.print(
                                "\n" + //
                                        "Please select a color by pressing corresponding key: \n" + //
                                        "1 for red, \n" + //
                                        "2 for blue, \n" + //
                                        "3 for green, \n" + //
                                        "4 for yellow.: ");
                        wildCardColor = scanner.nextInt();
                        if (wildCardColor < 5 && wildCardColor > 0)
                            break;
                    }
                }
                checkPlayerTurn();
            }
        } 
        else if (middleCard instanceof BonusCard) 
        {
            BonusCard newMiddleCard = (BonusCard) middleCard;

            if (newMiddleCard.getBonusType().equals("reverse")) 
            {
                isClockwise = !isClockwise;
                if (players.size() != 2)
                    checkPlayerTurn();

            } 
            else if (newMiddleCard.getBonusType().equals("skip")) 
            {
                checkPlayerTurn();
                System.out.println("\nSorry Player " + (turn + 1) + " You Got Skipped.\n");
                checkPlayerTurn();
            } 
            else 
            {
                isGameDraw++;
                checkPlayerTurn();

                if (!players.get(turn).checkBonusDraw() || isFirstRound) 
                {
                    System.out.println(
                            String.format("\nSorry Player %d, You Got Draw. You Get %d Cards. And You Lose A Turn.\n",
                                    (turn + 1), (2 * isGameDraw)));
                    int cardsToGive = isGameDraw * 2;
                    for (int i = 0; i < cardsToGive; i++) 
                    {
                        distributeCards(turn);
                    }
                    isGameDraw = 0;
                    checkPlayerTurn();
                }
            }
        } 
        else 
        {
            isGameDraw = isWildCardDraw = wildCardColor = 0;
            checkPlayerTurn();
        }
    }

    // Method to check the card
    private boolean checkCard(Card card) 
    {
        if (card instanceof WildCard) 
        {
            if (middleCard instanceof WildCard) 
            {
                return !players.get(turn).checkCardColor(wildCardColor, -2, null);
            } 
            else 
            {
                if (middleCard instanceof BonusCard) 
                {
                    return !players.get(turn).checkCardColor(middleCard.getCardColor(), -1,
                            ((BonusCard) middleCard).getBonusType());
                } 
                else 
                {
                    RegularCard newMiddle = (RegularCard) middleCard;
                    return !players.get(turn).checkCardColor(middleCard.getCardColor(), newMiddle.getCardCount(), null);
                }
            }
        } 
        else if (card instanceof BonusCard) 
        {
            BonusCard newCard = (BonusCard) card;
            if (middleCard instanceof WildCard) 
            {
                return newCard.getCardColor() == wildCardColor;
            } 
            else if (middleCard instanceof BonusCard) 
            {
                BonusCard newMiddle = (BonusCard) middleCard;
                return newMiddle.getCardColor() == newCard.getCardColor()
                        || newMiddle.getBonusType().equals(newCard.getBonusType());
            } 
            else 
            {
                RegularCard newMiddle = (RegularCard) middleCard;
                return newMiddle.getCardColor() == newCard.getCardColor();
            }
        } 
        else 
        {
            RegularCard newCard = (RegularCard) card;

            if (middleCard instanceof WildCard) 
            {
                return newCard.getCardColor() == wildCardColor;
            } 
            else if (middleCard instanceof BonusCard) 
            {
                BonusCard newMiddle = (BonusCard) middleCard;
                return newMiddle.getCardColor() == newCard.getCardColor();
            } 
            else 
            {
                RegularCard newMiddle = (RegularCard) middleCard;
                return newMiddle.getCardColor() == newCard.getCardColor()
                        || newMiddle.getCardCount() == newCard.getCardCount();
            }
        }
    }

    // Method to check the hand
    public boolean handCheck() 
    {
        ArrayList<Card> playerCards = players.get(turn).getCards(); // get the cards in hand
        
        // Loop to check the hand
        for (Card i : playerCards) 
        {
            if (checkCard(i))
                return true;
        }
        return false;
    }

    // Method to check if it is computer turn
    public boolean isComputerTurn() 
    {
        return players.get(turn).getPlayerType() == 1;
    }

    // Method to play computer turn
    public void computerPlay() 
    {
        ComputerPlayer temp = (ComputerPlayer) players.get(turn);
        playTurn(temp.sendInfo(wildCardColor, middleCard));
    }

    // Method to print hand card
    public void printHandCard() 
    {        
        if (players.get(turn).getPlayerType() == 0) 
        {
            System.out.println(String.format("Player %d, here is your cards : ", (turn + 1)));
            DisplayGameBoard showBoard = new DisplayGameBoard(players.get(turn).getCards(), graphicType);
        }
    }

    // Method to print middle card
    public void printMiddleCard() 
    {
        DisplayGameBoard showBorad = new DisplayGameBoard(middleCard, graphicType);

        // check if the middle card is a wild card
        if (middleCard instanceof WildCard) 
        {
            String color = null;
            switch (wildCardColor) 
            {
                case 1:
                    color = "RED";
                    break;
                case 2:
                    color = "BLUE";
                    break;
                case 3:
                    color = "GREEN";
                    break;
                case 4:
                    color = "YELLOW";
                    break;
            }
            System.out.println("The Ground Color Is : " + color);
        }
    }

    // Method to check game
    public boolean checkGame() 
    {
        return players.stream().anyMatch(player -> player.getCardCount() == 0);
    }

    // Method to print players
    public ArrayList<Player> getPlayersList() 
    {
        return players;
    }
}

