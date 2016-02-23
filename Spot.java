/**
 * Spot class to generate the board 
 *
 * @author Austin Boucher
 * @version 23 Jan 2015
 */

public class Spot {
public boolean mine;                                             //used to end the game if clicked upon
public boolean clicked;                                          //used to assure that only clicked on spots maybe reveiled
public String touching;                                          //used to generate the showed values for near by mines
public int i;                                                    //used to assure that only open spots can be increased
public Spot(boolean inmine, boolean inclicked, String intouching)//generates a class that accounts for three varibales
{
    mine = inmine;
    clicked = inclicked;
    touching = intouching;
}
public boolean getMine()
{
    return mine;
}
public boolean getClicked()
{
    return clicked;
}
public String getTouched()
{
    return touching;
}
public int touched()                                //used in combination with the minechecker class to check the 3x3 area around the board
{
    if(touching == "0")
    {i = 0;}
    i++;    
    touching = Integer.toString(i);                 //converts the i value to show the number of touching mines
    if(mine == true)
    {
        touching = "M";
    }
    return i;
    }
}




