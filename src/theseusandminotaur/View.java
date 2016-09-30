/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theseusandminotaur;

import APCSAnimation.AnimatedObject;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author HEALASST
 */
public class View implements AnimatedObject {

    Model model;
    int level;
    MazeDisplay mDisplay;
    private Graphics Graphics;

    public View(Model m) {
        model = m;
        level = m.level;
        mDisplay = new MazeDisplay(m.getMaze());
        model.getTheseus().size = mDisplay.getSquareSide();
        model.getMinotaur().size = mDisplay.getSquareSide();
    }

    @Override
    public void update(int dt) {
        model.getTheseus().update();
        model.getMinotaur().update();
        if (model.getMaze() != mDisplay.maze && model.loadedLevel) {
            mDisplay = new MazeDisplay(model.getMaze());
            model.getTheseus().size = mDisplay.getSquareSide();
            model.getMinotaur().size = mDisplay.getSquareSide();
            level = model.level;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("Lato", 0, 20));
        if (model.loadedLevel) {
            if (!model.lost) {
                mDisplay.paint(g);
                int tx = model.getTheseus().getPercievedX();
                int ty = model.getTheseus().getPercievedY();
                model.getTheseus().paint(g, mDisplay.getULCx(ty, tx), mDisplay.getULCy(ty, tx));

                int mx = model.getMinotaur().getPercievedX();
                int my = model.getMinotaur().getPercievedY();

                model.getMinotaur().paint(g, mDisplay.getULCx(my, mx), mDisplay.getULCy(my, mx));
                g.drawString(model.moves + "/" + model.perfect + "", 800 - 100, 800 - 100);
            } else {
                g.drawString("You Got Eaten", 300, 350);
                g.drawString("Click any button to retry", 300, 400);

            }
        } else {
            g.drawString("No More Levels :(", 300, 350);
        }
    }
}
