/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theseusandminotaur;

import APCSAnimation.AnimatedObject;
import APCSAnimation.AnimationWindow;
import APCSAnimation.ClickableObject;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author DSTIGANT
 */
public class MazeDisplay implements AnimatedObject, ClickableObject
{
    protected Maze maze;
    protected int numRows, numCols;
    protected int t;
   
    protected static final double timePerGenStep = 0.1;
    protected double nextStep;
    
    public MazeDisplay( Maze m )
    {
        maze = m;
        numRows = m.getNumRows();
        numCols = m.getNumColumns();
        t = 0;
        
        
        nextStep = timePerGenStep;
    }
    
    
    @Override
    public void update(int dt)
    {   
        
    }

    protected int getSquareSide()
    {
        return 500 / Math.max( numRows, numCols);
    }
    
    protected int getULCx( int r, int c )
    {
        return (int)(400 - numCols*getSquareSide()/2.0 + c*getSquareSide() ); 
    }
    
    protected int getULCy( int r, int c )
    {
        return (int)(300 - numRows*getSquareSide()/2.0 + r*getSquareSide() );
    }
    
    protected int getRow( int x, int y )
    {
        if ( y < 50 || y > 550 || x < 150 || x > 650 ) return -1;
        return (y - 50) / getSquareSide();
    }
    
    protected int getColumn( int x, int y )
    {
        if ( y < 50 || y > 550 || x < 150 || x > 650 ) return -1;
        return (x - 150) / getSquareSide();
    }
    
    protected void paintSquare( Graphics g, MazeSquare ms, int r, int c )
    {
        int ULCx = getULCx( r, c );
        int ULCy = getULCy( r, c );
        
        g.setColor( ms.getColor() );
        g.fillRect( ULCx, ULCy, getSquareSide(), getSquareSide() );
    }
    
    protected void paintEdge( Graphics g, MazeEdge me, int r, int c, int dir )
    {
        if ( !me.isWall() )
        {
            return;
        }
        
        int ULCx = getULCx( r, c );
        int ULCy = getULCy( r, c );
       
        int [] xs = new int[6];
        int [] ys = new int[6];
        
        double d = getSquareSide()*Math.sqrt(2)/30;
        if ( dir == 0 || dir == 2)
        {
            xs[0] = ULCx;
            ys[0] = ULCy + getSquareSide() * dir / 2;
            xs[1] = (int)(xs[0] + d );
            ys[1] = (int)(ys[0] - d );
            xs[2] = (int)(xs[0] + getSquareSide() - d );
            ys[2] = ys[1];
            xs[3] = xs[0] + getSquareSide();
            ys[3] = ys[0];
            xs[4] = xs[2];
            ys[4] = (int)(ys[0] + d );
            xs[5] = xs[1];
            ys[5] = ys[4];
        }
        else if ( dir == 1 || dir == 3)
        {
            xs[0] = ULCx + getSquareSide() * (3-dir)/2;
            ys[0] = ULCy;
            xs[1] = (int)(xs[0] + d );
            ys[1] = (int)(ys[0] + d );
            xs[2] = (int)(xs[0] + d );
            ys[2] = (int)(ys[0] + getSquareSide() - d );
            xs[3] = xs[0];
            ys[3] = ys[0] + getSquareSide();
            xs[4] = (int)(xs[0] - d);
            ys[4] = ys[2];
            xs[5] = xs[4];
            ys[5] = ys[1];
        }
        
        g.setColor( me.getColor() );
        g.fillPolygon( xs, ys, 6 );
        g.setColor( Color.BLACK );
        g.drawPolygon( xs, ys, 6 );
    }
    
    @Override
    public void paint(Graphics g)
    {
        
        for ( int i = 0; i < numRows; i++ )
        {
            for ( int j = 0; j < numCols; j++ )
            {
                MazeSquare ms = maze.getSquare( i, j );
                paintSquare( g, ms, i, j );
                
                paintEdge( g, ms.getEdge(0), i, j, 0 );
                paintEdge( g, ms.getEdge(3), i, j, 3 );
                
                if ( i == numRows - 1 )
                {
                    paintEdge( g, ms.getEdge(2), i, j, 2 );
                }
                if ( j == numCols - 1 )
                {
                    paintEdge( g, ms.getEdge(1), i, j, 1 );
                }
            }
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return true;
    }

    protected void resetMaze()
    {
        for ( int i = 0; i < maze.getNumRows(); i++ )
        {
            for ( int j = 0; j < maze.getNumColumns(); j++ )
            {
                maze.getSquare( i, j ).setColor( Color.WHITE );
            }
        }
    }
    
    @Override
    public void handleMouseClick(int x, int y) {
    
    }
    
}
