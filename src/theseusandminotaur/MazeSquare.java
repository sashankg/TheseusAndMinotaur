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
public class MazeSquare extends MazeObject
{
    private MazeEdge north, east, south, west;
    
    public MazeSquare( MazeEdge n, MazeEdge e, MazeEdge s, MazeEdge w )
    {
        super( Color.WHITE );
        north = n;
        n.setSquare( 2, this );
        east = e;
        e.setSquare( 3, this );
        south = s;
        s.setSquare( 0, this );
        west = w;
        w.setSquare( 1, this );
    }
    
    public MazeEdge getEdge( int dir )
    {
        if ( dir == 0 )
        {
            return north;
        }
        else if ( dir == 1 )
        {
            return east;
        }
        else if ( dir == 2 )
        {
            return south;
        }
        else
        {
            return west;
        }
    }
    
    public MazeSquare getNeighbor( int dir )
    {
        return getEdge( dir ).getSquare( dir );
    }
}
