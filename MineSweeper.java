import javax.swing.*;
/**
 * MineSweeper class to initiate the game 
 *
 * @author Austin Boucher
 * @version 23 Jan 2015
 */
public class MineSweeper extends JFrame 
{
public MineSweeper() 
{
        super("MineSweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new MinePanel());
        pack();
        setLocationRelativeTo(null);    //put the gui in the center of the screen
        setVisible(true);
}
public static void main(String[] args) 
{
    MineSweeper newGame = new MineSweeper();   
}
}
    

