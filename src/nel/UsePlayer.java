/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kylen
 */
public class UsePlayer
{

    Connect data = new Connect();
    String sql = "";
    Player[] playerArr = new Player[200];

    int size = 0;
    String[][] details = new String[size][2];

    /**
     * Loads player details from the database into a player array.
     */
    public void loadPlayers()
    {
        size = 0;
        try
        {

            sql = "SELECT * FROM Details";
            ResultSet rs = data.query(sql);
            while (rs.next())
            {
                String u = rs.getString("Username");
                String p = rs.getString("Password");
                int bt = rs.getInt("HighTime");
                int t = rs.getInt("Time");
                String q = rs.getString("Question");
                playerArr[size] = new Player(u, p, bt, t, q);
                size++;
            }
        } catch (SQLException ex)
        {
            System.out.println("Unable to load details.");
        }
    }

    /**
     * Creates a list of existing usernames. Returns a string array of
     * usernames.
     */
    public String[] usernameList()
    {

        String[] usernameL = new String[size];
        for (int i = 0; i < size; i++)
        {
            usernameL[i] = playerArr[i].getUsername();

        }

        return usernameL;

    }

    /**
     * Gets the security questions answer from selected user. Returns the
     * answer.
     */
    public String checkQuestion(String user)
    {
        String[] users = usernameList();
        String question = "";
        for (int i = 0; i < size; i++)
        {
            if (users[i].equalsIgnoreCase(user))
            {
                question = playerArr[i].getQuestion();
            }

        }
        return question;
    }

    /**
     * Receives a username and check for any duplicates in the list of
     * usernames. Returns true if selected username exists.
     */
    public boolean checkDupUser(String user)
    {
        String[] usernames = usernameList();
        for (int i = 0; i < size; i++)
        {
            if (usernames[i].equalsIgnoreCase(user))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Receives username, password and question. Inserts details into the
     * database.
     */
    public void register(String us, String pas, String quest)
    {
        try
        {
            sql = "INSERT INTO Details (Username,Password,HighTime,Time,Question) VALUES('" + us + "','" + pas + "',1000,0,'" + quest + "');";

            System.out.println(sql);
            data.update(sql);

        } catch (Exception e)
        {
            System.out.println("Unable to insert details.");
        }
    }

    /**
     * Receives password, length of the username and whether he is forgetting
     * his password or not. Returns an error message of what your password
     * needs.
     */
    public String validatePass(String password, int len, boolean newOrNot)
    {

        String out = "Password needs\n";
        // Creates a pattern excluding special characters.
// Taken from youtube video https://www.youtube.com/watch?v=8_6IotEcWY4
        Pattern pat = Pattern.compile("[a-zA-Z0-9]*");
        Matcher match = pat.matcher(password);
        boolean digit = false, upper = false, space = false, special = false;
        for (int i = 0; i < password.length(); i++)
        {

            char let;
            let = password.charAt(i);
            // Checks for a digit.
            if (Character.isDigit(let))
            {
                digit = true;
            }
            // Checks for a uppercase letter.
            if (Character.isUpperCase(let))
            {
                upper = true;
            }
            // Checks for a space.
            if (Character.isWhitespace(let))
            {
                space = true;
            }

        }
        // Checks for a special character.
        if (!match.matches())
        {
            special = true;
        }
        // Checks if the password is longer than 7 characters, adds to error message.
        if (password.length() < 8)
        {
            out += "more than 7 characters\n";
        }
        // Adds to error message if there is no digit.
        if (digit == false)
        {
            out += "a digit\n";
        }
        // Adds to error message if there is no uppercase letter.
        if (upper == false)
        {
            out += "a uppercase letter\n";
        }
        // Adds to error message if there is a white space.
        if (space == true)
        {
            out += "Remove white spaces please\n";
        }
        // Adds to error message if there is no special character.
        if (special == false)
        {
            out += "a special character";
        }
        // If user is registering, then checks username.
        if (newOrNot == false)
        {
            // Adds to error message if username is shorter than 4.
            if (len < 4)
            {
                out += "Your username needs to be more than 3 letters\n";
            }

        }

        if (out.equals("Password needs\n"))
        {
            out = "true";
        }
        return out;
    }

    /**
     * Returns the password for the received username.
     */
    public String searchPass(String u)
    {

        for (int i = 0; i < size; i++)
        {
            if (u.equals(playerArr[i].getUsername()))
            {
                return playerArr[i].getPassword();
            }
        }
        return "";
    }

    /**
     * Returns the time for the received username.
     */
    public int searchTime(String us)
    {
        for (int i = 0; i < size; i++)
        {
            if (us.equals(playerArr[i].getUsername()))
            {
                return playerArr[i].getTime();

            }
        }
        return -1;
    }

    /**
     * Deletes all the details for the received username and password.
     */
    public void delete(String u, String p)
    {

        try
        {
            sql = "DELETE * FROM Details WHERE Username='" + u + "' AND Password='" + p + "'";
            System.out.println(sql);
            data.update(sql);

        } catch (Exception e)
        {
            System.out.println("Unable to delete details.");
        }
    }

    /**
     * Updates the old password to the new password for the received username.
     */
    public void changePassword(String passNew, String u)
    {
        try
        {
            sql = "UPDATE Details SET Password='" + passNew + "' WHERE Username='" + u + "'";
            System.out.println(sql);
            data.update(sql);

        } catch (Exception e)
        {
            System.out.println("Unable to update password.");
        }

    }

    /**
     * Updates the time when user saves game,receives username and time.
     */
    public void updateTime(String u, int t)
    {
        try
        {
            sql = "UPDATE Details SET Time=" + t + " WHERE Username='" + u + "'";
            System.out.println(sql);
            data.update(sql);
        } catch (Exception e)
        {
            System.out.println("Unable to update time.");
        }

    }

    /**
     * Updates the best time when user finishes,receives username and best time.
     */
    public void updateHighTime(String u, int ti)
    {
        try
        {
            sql = "UPDATE Details SET HighTime=" + ti + " WHERE Username='" + u + "'";
            System.out.println(sql);
            data.update(sql);
        } catch (Exception e)
        {
            System.out.println("Unable to update high time.");
        }

    }

    /**
     * Updates the best time when user finishes,receives username and best time.
     */
    public String[][] highTimeDetails()
    {
        int count = 0;
        String[][] details = new String[size][2];

        for (int i = 0; i < size; i++)
        {

            if (playerArr[i].getbTime() != 1000)
            {
                details[count][0] = playerArr[i].getUsername();
                details[count][1] = "" + playerArr[i].getbTime();
                count++;
            }

        }
        return details;
    }

    /**
     * Returns the best time for the received username.
     */
    public int searchBTime(String us)
    {
        for (int i = 0; i < size; i++)
        {
            if (us.equals(playerArr[i].getUsername()))
            {
                return playerArr[i].getbTime();

            }
        }
        return -1;
    }

    /**
     * Updates best time if best time is lower than previous attempts
     */
    public void checkBestTime(String us, int time)
    {

        if (searchBTime(us) > time)
        {
            updateHighTime(us, time);
        }
    }

    /**
     * Sorts the table for each player from lowest to highest
     */
    public void sort()
    {
        Player temp;
        for (int x = 0; x < size - 1; x++)
        {
            for (int y = x + 1; y < size; y++)
            {
                if (playerArr[x].getbTime() > playerArr[y].getbTime())
                {
                    temp = playerArr[x];
                    playerArr[x] = playerArr[y];
                    playerArr[y] = temp;
                }
            }
        }
    }
}
