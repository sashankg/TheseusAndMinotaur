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
public class Theseus extends Character{

    public Theseus(int sx, int sy, Model m) {
        super(sx, sy, m);
    }
    
    @Override
    public void update()
    {
        super.update();
        if (model.didWin() && px == x && py == y) {
            model.level++;
            model.loadLevel();
        }
    }
    
    @Override
    public void paint(Graphics g, int xc, int yc)
    {
        g.setColor(Color.BLUE);
        super.paint(g, xc, yc);
    }
}
