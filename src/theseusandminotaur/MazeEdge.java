/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theseusandminotaur;

import java.awt.Color;

/**
 *
 * @author DSTIGANT
 */
public class MazeEdge extends MazeObject
{
    protected MazeSquare [] squares;
    protected boolean isWall;
    
    public MazeEdge()
    {
        super( Color.BLACK );
        squares = new MazeSquare[4];
        for ( int i = 0; i < 4; i++ )
        {
            squares[i] = null;
        }
        isWall = true;
    }
    
    public void setSquare( int dir, MazeSquare ms )
    {
        squares[dir] = ms;
    }
    
    public MazeSquare getSquare( int dir )
    {
        return squares[dir];
    }
    
    public MazeSquare [] getSquares()
    {
        MazeSquare [] scarlet = new MazeSquare[2];
        int n = 0;
        for ( int i = 0; i < 4; i++ )
        {
            if ( squares[i] != null )
            {
                scarlet[n] = squares[i];
                n++;
            }
        }
        return scarlet;
    }
    
    public boolean isInteriorEdge()
    {
        int n = 0;
        for ( int i = 0; i < 4; i++ )
        {
            if ( squares[i] != null )
            {
                n++;
            }
        }
        return n >= 2;
    }
    
    
    public void setPassage()
    {
        isWall = false;
    }
    
    public void setWall()
    {
        isWall = true;
    }
    
    public boolean isWall()
    {
        return isWall;
    }
    
    public boolean isPassage()
    {
        return !isWall;
    }
}
