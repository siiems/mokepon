package level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import main.GamePanel;
import main.KeyHandler;
import level.SettingsMenu;

import level.Level;

public class LevelHandler {
    public Level currentLevel;



    public void update(double FPS) {
        if (this.currentLevel == null) {return;}
        this.currentLevel.update(FPS);
    }

    public void draw(Graphics2D g2) {
        if (this.currentLevel == null) {return;}
        this.currentLevel.draw(g2);
    }

    public boolean changeLevel(String level, KeyHandler keyH, GamePanel gp) {
        currentLevel = new Level(level, keyH, gp);
        return false;
    }

    


}
