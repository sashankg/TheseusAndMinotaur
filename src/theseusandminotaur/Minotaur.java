/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package theseusandminotaur;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author HEALASST
 */
public class Minotaur extends Character{
    Theseus theseus;
    public Minotaur(int sx, int sy, Model m, Theseus t) {
        super(sx, sy, m);
        theseus = t;
    }
    
    @Override
    public void update()
    {
        super.update();
        if (model.didLose() && px == x && py == y) {
            model.lost = true;
        }
    }
    
    @Override
    public void paint(Graphics g, int xc, int yc)
    {
        int b = size/16;
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(new int[]{xc+b+ax, xc+8*b+ax, xc+2*b+ax}, new int[]{yc+b+ay, yc+4*b+ay, yc+6*b+ay}, 3);
        g.fillPolygon(new int[]{xc+size-b+ax, xc+size-8*b+ax, xc+size-2*b+ax}, new int[]{yc+b+ay, yc+4*b+ay, yc+6*b+ay}, 3);

        g.setColor(Color.RED);
        super.paint(g, xc, yc);
    }
    
    public void thinkAndMove()
    {
        if(theseus.x != x && model.canMakeMove(x, y, x + (theseus.x-x)/Math.abs((theseus.x-x)), y))
        {
            move(0, (theseus.x-x)/Math.abs((theseus.x-x)));
        }
        else if(theseus.y != y && model.canMakeMove(x, y, x, y + (theseus.y-y)/Math.abs((theseus.y-y))))
        {
            move((theseus.y-y)/Math.abs((theseus.y-y)), 0);
        }
    }
    
    @Override
    public int getSpeed()
    {
        return super.getSpeed()*2;
    }
}
