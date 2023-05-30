/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nel;

/**
 *
 * @author kylen
 */
public class Player
{

    private String username = "", password = "", question = "";

    private int btime = 0, time = 0;

    /**
     * Receives the username, password, best time, time and question from the
     * database This is saved into the Player object.
     */
    Player(String u, String p, int bt, int t, String q)
    {
        username = u;
        password = p;
        btime = bt;
        time = t;
        question = q;
    }

    /**
     * Returns the desired username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Returns the desired password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Returns the desired best time
     */
    public int getbTime()
    {
        return btime;
    }

    /**
     * Returns the desired time
     */
    public int getTime()
    {
        return time;
    }

    /**
     * Returns the desired question
     */
    public String getQuestion()
    {
        return question;
    }
}
