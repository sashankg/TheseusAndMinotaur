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
public class MazeObject
{
    protected Color color;
    
    public MazeObject( Color c )
    {
        color = c;
    }
    
    public void setColor( Color c )
    {
        color = c;
    }
    
    public Color getColor()
    {
        return color;
    }
}
