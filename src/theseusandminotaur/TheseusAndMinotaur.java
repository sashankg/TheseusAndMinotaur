/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package theseusandminotaur;

import APCSAnimation.AnimationWindow;
import java.io.FileNotFoundException;

/**
 *
 * @author HEALASST
 */
public class TheseusAndMinotaur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Model model = new Model(1);
        
        AIControl ai = new AIControl(model);        
        AnimationWindow aw = new AnimationWindow("Theseus and the Minotaur", 800, 800, 50);
        aw.addAnimatedObject(new View(model));
        aw.addAnimatedObject(ai);
        //aw.addAnimatedObject(new Control(model));
        aw.run();
    }
    
}
