/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package theseusandminotaur;

import APCSAnimation.AnimatedObject;
import APCSAnimation.Typeable;
import java.awt.Graphics;

/**
 *
 * @author HEALASST
 */
public class Control implements AnimatedObject, Typeable{
    Model model;
    public Control(Model m)
    {
        model = m;
    }
    @Override
    public void update(int dt) {
        
    }

    @Override
    public void paint(Graphics g) {
        
    }

    @Override
    public void handleKeyPressed(char c, int kc) {
    }

    @Override
    public void handleKeyReleased(char c, int kc) {
        if(!model.lost)
        {
            model.makeMove(kc-37);
        }
        else
        {
            model.loadLevel();
        }
    }
    
}
