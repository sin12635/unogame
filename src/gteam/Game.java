/*
 * This class is used to play the game.
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */

package gteam;

import java.util.ArrayList;
import java.util.Scanner;

public class Game 
{
    private GameBoard board; // GameBoard object
    private int totalGamePlayers; // Total number of players
    private int gameMode; // Game mode
    private ArrayList<Result> gameResults; // List of results
    private int graphicType; // Graphic type

    // Constructor
    public Game(int totalGamePlayers, int gameMode, int graphicType) 
    {
        this.totalGamePlayers = totalGamePlayers;
        this.gameMode = gameMode;
        this.graphicType = graphicType;
        gameResults = new ArrayList<>();
    }

    // Method to start a new game
    public void playNewGame() 
    {
        Scanner scanner = new Scanner(System.in);
        int input;

        // Loop to play new round
        while (true) 
        {
            System.out.print("Press 1 to start a new round\nPress 2 to Exit the game :"
                    + " ");
            input = scanner.nextInt();
            if (input == 1)
                playUNOGame();
            else if (input == 2)
                break;
            else
                System.out.print("Your input value is invalid please try again!.");
        }

        printAllResult();
        System.out.print("Thank you for playing.");
    }

    // Method to play UNO game
    private void playUNOGame() 
    {
        board = new GameBoard(totalGamePlayers, gameMode, graphicType);

        Scanner scanner = new Scanner(System.in);
        int input;
        board.checkMiddle();
        board.setFirstRound();

        // Loop to play the game
        while (true) 
        {
            printPlayers();
            board.printMiddleCard();
            if (board.checkGame()) 
            {
                System.out.print("\nRound Finished.\n");
                Result resultOfGame = new Result(board.getPlayersList());
                gameResults.add(resultOfGame);
                printCurrentRound();
                break;
            }
            if (board.isClockwise())
                System.out.println("\n Clockwise Rotation. ( ---> )");
            else
                System.out.println("Anti-Clockwise Rotation. ( <--- )");
            board.shuffleCards();
            System.out.println("\nIts Turn for Player " + (board.getTurn() + 1) + ". Play the game.");
            if (board.isComputerTurn()) 
            {
                if (!board.handCheck())
                    board.distributeCards(board.getTurn());
                if (!board.handCheck()) 
                {
                    board.checkPlayerTurn();
                    continue;
                }
                board.computerPlay();
                continue;
            }

            board.printHandCard();

            if (!board.handCheck()) 
            {
                System.out.println("\n You do not have a valid card, try again!");

                // Loop to pick a valid card
                while (true)
                {
                    System.out.print("\nPress 1 to select a card : ");
                    input = scanner.nextInt();
                    if (input == 1) 
                    {
                        board.distributeCards(board.getTurn());
                        break;
                    }
                }

                board.printHandCard();

                if (!board.handCheck()) 
                {
                    // Loop to pass the turn
                    while (true) 
                    {
                        System.out.print("\nPress 1 to pass your turn as you don't have valid card: ");
                        input = scanner.nextInt();
                        if (input == 1)
                            break;
                    }

                    board.checkPlayerTurn();
                    continue;
                }

            }

            // Loop to play the turn
            while (true) 
            {

                System.out.print("\nSelect a Card That you would like to give : ");
                input = scanner.nextInt() - 1;

                if (input > -1 && input < board.getPlayerCardsCount()) 
                {
                    if (board.playTurn(input)) 
                    {
                        break;
                    } 
                    else 
                    {
                        System.out.println("Your card is not valid please try again!");
                        continue;
                    }
                }
                System.out.println("Invalid input please try again!");
            }
        }    
    }

    // Method to print players
    private void printPlayers() {

        ArrayList<Player> players = board.getPlayersList(); // get the players list

        // Loop to print players
        for (int i = 0; i < players.size(); i++)         
        {
            String playerName = "Player " + (i + 1);
            int cardCounts = players.get(i).getCardCount();
            String playerInfo = String.format("%s Cards : %d", playerName, cardCounts);
            System.out.print(playerInfo);
            if (i != players.size() - 1) 
            {
                System.out.print(" | ");
            }
        }
        System.out.println();
    }

    // Method to print current round
    private void printCurrentRound() 
    {
        Result latestRound = gameResults.get(gameResults.size() - 1);
        System.out.printf("%nRound Information:%n");
        latestRound.printResult();
        int winner = latestRound.getRoundWinner() + 1;
        System.out.printf("Winner of the Round Is Player %d%n", winner);
    }

    // Method to print all results
    private void printAllResult() 
    {
        System.out.println("\n Here Is Information Of Round.\n");
        String line = " ------------  ";
        System.out.print("------- | ");
        
        for (int j = 0; j < totalGamePlayers; j++)
            System.out.print(line);

        System.out.print("\n");
        
        // Loop to print all results
        for (Result result : gameResults) 
        {
            System.out.print("Round " + (gameResults.indexOf(result) + 1) + " : ");
            result.printResult();
        }
        System.out.print("\n");
    }
}
