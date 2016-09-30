/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theseusandminotaur;

/**
 *
 * @author DSTIGANT
 */
public class Maze
{
    protected MazeEdge [][] edges;
    protected MazeSquare [][] squares;
    
    
    public Maze( int numRows, int numCols )
    {
        
        
        edges = new MazeEdge[2*numRows+1][2*numCols+1];
        squares = new MazeSquare[numRows][numCols];
        
        for ( int i = 0; i <= 2*numRows; i++ )
        {
            for ( int j = 0; j <= 2*numCols; j++ )
            {
                if ( i%2 == j%2 )
                {
                    edges[i][j] = null;
                }
                else
                {
                    edges[i][j] = new MazeEdge();
                }
            }
        }
        
        for ( int i = 0; i < numRows; i++ )
        {
            for ( int j = 0; j < numCols; j++ )
            {
                squares[i][j] = new MazeSquare( edges[2*i][2*j+1], edges[2*i+1][2*j+2], edges[2*i+2][2*j+1], edges[2*i+1][2*j] );
            }
        }
    }
    
    public int getNumRows() { return squares.length; }
    public int getNumColumns() { return squares[0].length; }
    
    public MazeSquare getSquare( int r, int c )
    {
        return squares[r][c];
    }
}
