/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author kylen
 */
public class SavedGame
{

    int time = 0;
    int[][] game = new int[14][20];

    boolean save = false;
    String colour = "";

    /**
     * Returns the time from text file
     */
    public int getTime()
    {
        return time;
    }

    /**
     * Returns the grid from text file
     */
    public int[][] getGame()
    {
        return game;
    }

    /**
     * Returns the colour of the player from text file
     */
    public String getColour()
    {
        return colour;
    }

    /**
     * Receives the username,time, grid and colour that the player used Converts
     * it to a string
     */
    public void convertGame(String user, int time, int[][] gam, String colour)
    {
        String end = "" + time + "\n" + colour + "\n";
        for (int i = 0; i < 14; i++)
        {

            for (int j = 0; j < 20; j++)
            {
                if (j == 19)
                {
                    end += gam[i][j] + "\n";
                } else
                {
                    end += gam[i][j] + " ";
                }

            }

        }

        convertToTxt(user, end);
    }

    /**
     * Receives the username and the string of information to be saved. Creates
     * a file with the name being the username and prints the data into the file
     */
    public void convertToTxt(String us, String out)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new FileWriter(us + ".txt"));
            writer.println(out);
            writer.close();
        } catch (IOException ex)
        {
            System.out.println("Unable to print to file.");
        }

    }

    /**
     * Receives the username the user selected. Loads the user's time, colour
     * and chosen maze/saved maze from their text file
     */
    public int[][] loadSavedGame(String username)
    {

        int rowcount = 0;

        try
        {

            Scanner scFile = new Scanner(new File(username + ".txt"));

            time = Integer.parseInt(scFile.nextLine());
            colour = scFile.nextLine();
            while (scFile.hasNext())
            {

                String line = scFile.nextLine();

                Scanner scLine = new Scanner(line).useDelimiter(" ");
                for (int col = 0; col < 20; col++)
                {
                    game[rowcount][col] = scLine.nextInt();

                }
                rowcount++;

            }
            scFile.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
        return game;
    }
}
