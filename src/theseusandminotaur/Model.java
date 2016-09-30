/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theseusandminotaur;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HEALASST
 */
public class Model {

    int level;
    int perfect;
    int moves;
    Theseus theseus;
    Maze maze;
    int ex, ey;
    Minotaur minotaur;
    boolean loadedLevel;
    boolean lost;
    
    public Model(int l) {
        level = l;
        loadLevel();
    }
    
    public Model(Model m)
    {
        level = m.level;
        perfect = m.perfect;
        moves = m.moves;
        theseus = new Theseus(m.getTheseus().getX(), m.getTheseus().getY(), this);
        maze = m.getMaze();
        ex = m.ex;
        ey = m.ey;
        minotaur = new Minotaur(m.getMinotaur().getX(), m.getMinotaur().getY(), this, theseus);
        loadedLevel = true;
        lost = false;
    }

    public Maze getMaze() {
        return maze;
    }

    public Theseus getTheseus() {
        return theseus;
    }

    public Minotaur getMinotaur() {
        return minotaur;
    }

    public void makeMove(int dir) {
        int[][] key = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        if (!theseus.isAnimating && !didLose()) {
            if (dir >= 0 && dir < 4 && maze.getSquare(theseus.y, theseus.x).getEdge((dir + 3) % 4).isPassage())
            {
                theseus.move(key[dir][0], key[dir][1]);
            }
            minotaur.thinkAndMove();
            minotaur.thinkAndMove();
            moves++;
        }
    }

    public boolean didWin() {
        return ex == theseus.getX() && ey == theseus.getY() && !didLose();
    }
    
    public boolean didLose() {
        return minotaur.getX() == theseus.getX() && minotaur.getY() == theseus.getY();
    }

    public boolean canMakeMove(int ox, int oy, int nx, int ny) {
        int dir = 1 + (ny - oy) + 1 * Math.abs(nx - ox) - (nx - ox);
        return maze.getSquare(oy, ox).getEdge(dir).isPassage();
    }
    
    public boolean canMakeMove(int dir)
    {
        return maze.getSquare(theseus.getY(), theseus.getX()).getEdge((dir+3)%4).isPassage();
    }

    public void loadLevel(){
        try {
            Scanner levelFile = new Scanner(new File("Levels/Level" + level + ".txt"));
            int h = levelFile.nextInt();
            int w = levelFile.nextInt();
            maze = new Maze(w, h);
            for (int i = 0; i < maze.getNumRows(); i++) {
                for (int j = 0; j < maze.getNumColumns(); j++) {
                    if (levelFile.nextInt() == 1) {
                        maze.getSquare(i, j).getEdge(3).setWall();
                    } else {
                        maze.getSquare(i, j).getEdge(3).setPassage();
                    }
                }
                levelFile.nextInt();
            }
            for (int i = 0; i < maze.getNumColumns(); i++) {
                for (int j = 0; j < maze.getNumRows(); j++) {
                    if (levelFile.nextInt() == 0) {
                        maze.getSquare(j, i).getEdge(0).setPassage();
                    }
                }
                levelFile.nextInt();
            }
            ex = levelFile.nextInt();
            ey = levelFile.nextInt();
            maze.getSquare(ey, ex).setColor(Color.GRAY);

            int tx = levelFile.nextInt();
            int ty = levelFile.nextInt();
            theseus = new Theseus(tx, ty, this);

            int mx = levelFile.nextInt();
            int my = levelFile.nextInt();
            minotaur = new Minotaur(mx, my, this, theseus);

            perfect = levelFile.nextInt();
            
            loadedLevel = true;
            moves = 0;
            lost = false;
        }
        catch (FileNotFoundException exception)
        {
            loadedLevel = false;
        }
    }
}
