/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theseusandminotaur;

import APCSAnimation.AnimatedObject;
import APCSAnimation.ClickableObject;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author HEALASST
 */
public class AIControl implements AnimatedObject, ClickableObject {

    Model model;
    ArrayList<Integer> steps;
    boolean thinking;
    int level;

    public AIControl(Model m) {
        model = m;
        level = m.level;
    }

    @Override
    public void update(int dt) {

    }

    @Override
    public void paint(Graphics g) {
    }

    public void solution() {
        ArrayList<Model> fringe = new ArrayList<>();
        ArrayList<ArrayList<Integer>> movesArray = new ArrayList<>();
        fringe.add(model);
        movesArray.add(new ArrayList<>());
        ArrayList<int[]> visited = new ArrayList<>();
        while (!fringe.get(0).didWin()) {
            Model pop = fringe.remove(0);
            ArrayList<Integer> moves = movesArray.remove(0);
            for (int i = 0; i < 5; i++) {
                Model temp = new Model(pop);
                if (temp.canMakeMove(i) || i > 3) {
                    temp.makeMove(i);
                    if (!temp.didLose()
                            && !beenThere(visited, temp.theseus.getX(), temp.theseus.getY(), temp.minotaur.getX(), temp.minotaur.getY())) {
                        fringe.add(temp);
                        ArrayList<Integer> newMoves = new ArrayList<>();
                        for (int n = 0; n < moves.size(); n++) {
                            newMoves.add(moves.get(n).intValue());
                        }
                        newMoves.add(i);
                        movesArray.add(newMoves);
                        visited.add(new int[]{temp.theseus.getX(), temp.theseus.getY(), temp.minotaur.getX(), temp.minotaur.getY()});
                    }
                }
            }
            System.out.println(fringe);
        }
        thinking = false;
        steps = movesArray.get(0);
        System.out.println(steps);
    }

    private boolean beenThere(ArrayList<int[]> a, int tx, int ty, int mx, int my) {
        for (int[] p : a) {
            if (p[0] == tx && p[1] == ty && p[2] == mx && p[3] == my) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(int x, int y) {
        return true;
    }

    @Override
    public void handleMouseClick(int x, int y) {
        if (model.loadedLevel) {
            if (steps == null && !thinking) {
                thinking = true;
                solution();
            } else if (!model.theseus.isAnimating) {
                model.makeMove(steps.remove(0));
                if (model.didWin()) {
                    steps = null;
                    level++;
                }
            }
        }
    }

}
