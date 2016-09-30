/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package theseusandminotaur;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author HEALASST
 */
public class Character {
    int x, y;
    int px, py;
    ArrayList<int[]> path;
    boolean isAnimating;
    int ax, ay;
    int size;
    Model model;
    public Character(int sx, int sy, Model m)
    {
        x = sx;
        y = sy;
        px = sx;
        py = sy;
        model = m;
        path = new ArrayList<>();
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getPercievedX()
    {
        return px;
    }
    
    public int getPercievedY()
    {
        return py;
    }
    
    public void update()
    {
        if(path.size() > 0)
        {
            animateTo(path.get(0)[0], path.get(0)[1]);
        }
    }
    public void paint(Graphics g, int xc, int yc)
    {
        int b = size/16;
        g.fillOval(xc+b+ax, yc+b+ay, size-2*b, size-2*b);
    }
    
    public void animateTo(int nx, int ny)
    {
        isAnimating = true;
        int speed = getSpeed();
        ax += speed*(nx-px);
        ay += speed*(ny-py);
        if(Math.abs(ax) > size || Math.abs(ay) > size)
        {
            ax = 0;
            ay = 0;
            int[] r = path.remove(0);
            px = r[0];
            py = r[1];
            isAnimating = false;
        }
    }
    
    public int getSpeed()
    {
        return size/5 + 1;
    }
    
    public void move(int nx, int ny)
    {
        ax = 0;
        ay = 0;
        path.add(new int[]{x + ny, y + nx});
        x += ny;
        y += nx;
    }
}
