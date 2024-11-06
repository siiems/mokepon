package level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import ui.Button;
import main.GamePanel;
import main.KeyHandler;
import level.LevelHandler;

import entity.Mokepon;



public class SettingsMenu {
    
    KeyHandler keyH;
    LevelHandler LevelHandler;

    double butY = 400;
    double butXsize = 200;
    double butFontSize = 50;

    
    Button b_play = new Button("Play", 15, Color.BLACK, butXsize, 50, butXsize/3, 400, Color.LIGHT_GRAY, Color.DARK_GRAY, 50, 0);
    Button b_settings = new Button("Settings", 15, Color.BLACK, butXsize, 50, 800-(butXsize)-(butXsize/2), 400, Color.LIGHT_GRAY, Color.DARK_GRAY, 20 ,0);

    class button {
        Button button;
        button up;
        button down;
        button left;
        button right;
    }

    button button_settings;
    button button_play;

    Button[] buttons = {b_settings, b_play};
    button CurrentButton;

    long buttonReset;
    long prevLoop;
    long currLoop;
    long diff;

    boolean enter_held;

    GamePanel gp;



    public SettingsMenu(LevelHandler LevelHandler, KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;
        this.LevelHandler = LevelHandler;

        this.buttonReset = (long)(0.1 * 1000000000);
        this.prevLoop = System.nanoTime();

        // Create buttons
        button_settings = new button();
        button_play = new button();

        // Link buttons 
            // settings button
        button_settings.button = b_settings;
        button_settings.left = button_play;

            // play button
        button_play.button = b_play;
        button_play.right = button_settings;

        // Set default button
        this.CurrentButton = this.button_settings;


    }

    public void update(double FPS) {
        currLoop = System.nanoTime();
        diff += currLoop - prevLoop;
        prevLoop = currLoop;

        // When button is pressed
        
        if (this.keyH.enterPressed) {
            if (this.CurrentButton.button == this.b_settings && diff >= this.buttonReset) {
                System.out.println("DOING");
                this.LevelHandler.changeLevel("settings_menu", keyH, gp);
            }
            
        } 

        // Navigate to different buttons
        if (this.keyH.upPressed) {
            if (this.CurrentButton.up != null) {
                this.CurrentButton = this.CurrentButton.up;
            }
        } else if (this.keyH.downPressed) {
            if (this.CurrentButton.down != null) {
                this.CurrentButton = this.CurrentButton.down;
            }
        } else if (this.keyH.leftPressed) {
            if (this.CurrentButton.left != null) {
                this.CurrentButton = this.CurrentButton.left;
            } 
        } else if (this.keyH.rightPressed) {
            if (this.CurrentButton.right != null) {
                this.CurrentButton = this.CurrentButton.right;
            }
        }

    }
    

    public void draw(Graphics2D g2) {
        for (int index = 0; index < buttons.length; index++) {
            boolean selected = false;
            if (buttons[index] == this.CurrentButton.button) {
                selected = true;
            }
            
            buttons[index].draw(g2, selected);
        }


        
    }


   

}