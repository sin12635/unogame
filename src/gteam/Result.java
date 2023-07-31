/*
 * This class is used to store the result of the game
 * 
 * @author:  Gurjot Singh
 * Student ID:  991675554
 */
package gteam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

public class Result 
{
    private Player[] playersList; // The list of players
    private int roundWinner; // The number of cards of the winner

    // Constructor
    public Result(ArrayList<Player> playersArray) 
    {
        playersList = playersArray.toArray(new Player[playersArray.size()]);
        arrangePlayer();
    }

    // method to arrange the players
    private void arrangePlayer() 
    {
        Arrays.sort(playersList, Comparator.comparingInt(Player::getScore).reversed());
        roundWinner = playersList[0].getCardCount();
    }

    // method to get the list of players
    public int getRoundWinner() 
    {
        return roundWinner;
    }

    // method to print the result
    public void printResult() 
    {
        StringJoiner sj = new StringJoiner(" | ");
        for (Player player : playersList) 
        {
            sj.add("Player " + (player.getCardCount() + 1) + " : " + player.getScore());
        }
        System.out.println(sj);
    }
}

