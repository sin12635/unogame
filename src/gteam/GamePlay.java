/*
 * This class is the main class of the game.
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */
package gteam;

import java.util.Scanner;

public class GamePlay {

    public static void main(String[] args) 
    {
        Scanner scannerObj = new Scanner(System.in);
        int totalGamePlayers;
        int gameMode;
        int graphicType;

        System.out.print("Press 1 to start the game: ");
        graphicType = scannerObj.nextInt();
        System.out.print(
                "\nPress 1 If you want to play in single mode\nPress 2 if you want to play Multiplayer Mode : ");
        gameMode = scannerObj.nextInt();

        switch (gameMode) 
        {
            case 1:
                System.out.print("Please Enter Total Number of Players (3-4-5) : ");
                break;
            case 2:
                System.out.print("Please Enter The Number Of Players : ");
                break;
            default:
                System.out.println("Wrong Input");
                return;
        }

        totalGamePlayers = scannerObj.nextInt();
        Game UNOGame = new Game(totalGamePlayers, gameMode, graphicType);
        UNOGame.playNewGame();
    }
}

