/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author kylen
 */
public class RunGame
{

    int grid[][] = new int[14][20];

    /**
     * Receives a username or a maze file name. Scans data from the text file
     * and saves it as a grid.
     */
    public int[][] print(String f)
    {
        int rowcount = 0;
        try
        {

            Scanner scFile = new Scanner(new File(f));

            while (scFile.hasNext())
            {
                String line = scFile.nextLine();
                Scanner scLine = new Scanner(line).useDelimiter(" ");
                for (int col = 0; col < 20; col++)
                {
                    grid[rowcount][col] = scLine.nextInt();

                }
                rowcount++;

            }
            scFile.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }

        return grid;
    }
}
