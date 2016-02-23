import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.Scanner;
/**
 * MinePanel class to build the face and the back of the game 
 *
 * @author Austin Boucher
 * @version 23 Jan 2015
 */
public class MinePanel extends JPanel
{
Scanner in = new Scanner(System.in);        //allows for reading of key strokes
private Spot [][] board;                    //generates a spot class board
private int column = 10;                    //sets the number of columns
private int rows = 10;                      //sets the number of rows
private int totalmines = 25;                //sets the number of mines
private int t;                              //saves a random spot for a mine
private int u;                              //saves a random spot for a mine
private int total;                          //outputs the total open spots on the board
private int clicked = 0;                    //saves the total number of clicks on a unclicked 
private final int size = (251);             //basic size for a 10x10 game
private boolean firstclick = false;         //assures that the first click is checked to see if it is a mine
int x;                                      //saves the clicked column value
int y;                                      //saves the clicked row value
public MinePanel()                          
{
    /*Allows the user to set custom settings doesnt change the size of the board
    System.out.println("Please enter the number of columns and rows you wish to play with");
    column = in.nextInt();
    rows = column;
    System.out.println("How many mines do you wish to play with? has to be within " + (column * rows));
    totalmines = in.nextInt();
    */
    setPreferredSize(new Dimension(size, size));//Creates the visable game
    addMouseListener(new MineMouseListener());  //allows for clicking
    board = new Spot[column][rows];         //creates a board of class spot
    initialize();                           //generates a blank board
    randomize();                            //randomizes the mine placement on the board
    getCount();                             //generates the total avalable open spots
}
private void initialize()                   //fills the board with blank variables
{
    for (int i = 0; i < column; i++)        
    {
    for (int j = 0; j < rows; j++) 
     {
    board[i][j] = new Spot(false, false, "0");
     }
    }
}
private void randomize()                    //fills the board with randomly places mines
{
    for(int x=0; x < totalmines; x++)         
    {
         t = (int)(Math.random()*column);
         u = (int)(Math.random()*rows);
        if(board[t][u].getMine() == true)   //generates an infinate loop till the total mines are made
        {
            x--;
        }
        board[t][u].mine = true;            //converts the mine value to be true
        board[t][u].touching = "M";         //converts the string value to show a M
    }
    minechecker();                          //runs the minechecker
}
private void minechecker()                  //checks the board to change the touching value to show how many conjoining mines there are
{
    for (int i = 0; i < column; i++)    
    {
    for (int j = 0; j < rows; j++)          //makes a spot on the board
     {
    for (int o = (i - 1); o <= (i+1); o++)  //checks a 3x3 radius around the spot on the board
    {
    for (int l = (j - 1); l <= (j+1); l++) 
     {
       if(o >= 0 && o < column)             //if the area check is within the board
       {
           if(l >= 0 && l <  rows)          //adds changes to the spot on the board
           {
               if(board[o][l].getMine() == true)
               {
               board[i][j].touched();       //runs the touched class if the spot on the 3x3 checked spot has a true mine value
               }
           }
           
       }
         
     }
     }
     }
    }
}

private void gameover()                      //ends the game if you click on a mine
{
     if(board[x][y].getMine() == true)         
     {
     JOptionPane.showMessageDialog(this, "Just like the Seahawks, too bad, soooo sad");
     System.exit(0);
     }
     if(clicked == total)                       //ends the game if you win the game
     {
        JOptionPane.showMessageDialog(this, "Congratz You Win");
        System.exit(0);
     }
         
}
private void renewboard()   //renews the board if the first spot is a mine
{
                while(firstclick == false)         //assures that you cannot first click on a mine
                        {
                            if(board[x][y].getMine() == true)
                            {
                                initialize();
                                randomize();//if the first click is a mine, will completely renew the board
                            }
                            if(board[x][y].getMine() == false)
                            {
                                firstclick = true; //ends the loop and makes sure your first click will not end the game
                            }
                        }
}
private void clickedspot()                  //assures that you cannot click on a reveiled spot
{
    if(board[x][y].getClicked() == false) 
            {
            board[x][y].clicked = true;
            clicked++;
            }
}
class MineMouseListener implements MouseListener 
{
        @Override
        public void mousePressed(MouseEvent e) {
            //System.out.println("press");
        }     
        public void mouseReleased(MouseEvent e) {
            //System.out.println("release");
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            //System.out.println("mouse entered");
        }
        public void mouseExited(MouseEvent e) {
            //System.out.println("mouse exited");
        }
        public void mouseClicked(MouseEvent e) 
        {
            x = (e.getX() / 25);                 //generates the array's column location
            y = (e.getY() / 25);                 //generates the array row location
            renewboard();                        //makes sure your first click is on a non mine location
            clickedspot();                       //changes the spots visable status
            repaint();                           //repaints the board
            gameover();                          //checks to see if the game is over         
        }
}

@Override
public void paint(Graphics g)               //generates the board's outline and generates values based on spots you've clicked upon
{
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.BLACK);
        int a; int c;
        int e; int k;
        int b = 0; int f = 0;
        int d = 0; int h = 25;
        for(int x = 0; x < column; x++)         //generates the bracketing within the window
        {
            a = 0;
            e = 0;
            k = 0;
            c = 25;
            for(int y = 0; y < rows; y++)
            {  
                g.drawLine(a, b, c, d);
                g.drawLine(e, f, k, h);
                a = a+25; e = e+25;
                c = c+25; k = k+25;
            }
             g.drawLine(e, f, k, h);
             f = h;  h = h+25;
             b = b+25; d = d+25;
        }
        for(int x = 0; x < 1; x++)
        {
            a = 0;
            c = 25;
            for(int y = 0; y < rows; y++)
            {
            g.drawLine(a, b, c, d);
            a = a +25;
            c = c+25;
            }
        }
        
        Font p = new Font("Times", Font.PLAIN, 25);
        g.setFont(p);
        FontMetrics fm = g.getFontMetrics();
        int q = fm.getAscent();
        int z = fm.getHeight();
        
       
    for (int n = 0; n < column; n++)        //fills the board with the repainted spots
        {
    for (int t = 0; t < rows; t++) 
        { 
        if(board[n][t].getClicked() == true)//assure on the clicked on spots are revealed
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < rows; j++) {
                String curSquare = board[n][t].touching;
                int w = fm.stringWidth(curSquare);
                g.drawString(curSquare, 25 * n + 12 - w / 2, 25 * t + 15 + q - z / 2);
            }
}
        }
 }
}
private int getCount() // sets the total number of open spots needed to be clicked to win the game
{
    total = ((column * rows) - totalmines);
    return total;
}
}







/**
 *
 * @author Austin Boucher
 */
