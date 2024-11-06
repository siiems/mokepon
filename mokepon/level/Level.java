package level;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;

import java.awt.image.BufferedImage;
import animation.SpriteFrame;

import ui.Button;
import ui.UserInterface;
import ui.ScrollingUI;

import level.levels.*;

public class Level {

    public Button[] Button;
    public UserInterface[] Image;
    public ScrollingUI[] ScrollingImage;
    public Button currentButton;
    KeyHandler keyH;
    GamePanel gp;

    public Level(String levelname, KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;


        switch (levelname) {
            case "menu_main": {
                Menu menu = new Menu(keyH, gp);
                menu.initialise(this);
                break;
            }
            case "menu_settings": {
                Menu_Settings menu_settings = new Menu_Settings(keyH, gp);
                menu_settings.initialise(this);
                break;
            }
        }



    }

    public void update(double FPS) {
        currentButton = keyH.changeButton(currentButton);
        if (ScrollingImage != null) {
            for (int index = 0; index < ScrollingImage.length; index++) {
                if (ScrollingImage[index] == null) {continue;}
                ScrollingImage[index].update(FPS, gp);
            }
        }
        

        for (int index = 0; index < Button.length; index++) {
            boolean selected = false;
            if (Button[index] == currentButton) {
                selected = true;
            }
            Button[index].update(FPS, selected);
        }
    }


    public void draw(Graphics2D g2){
        
        boolean scrollNull = false;
        if (ScrollingImage == null) {
            scrollNull = true;
        }

        if (Image != null) {
            for (int index = 0; index < Image.length; index++) {
                g2.drawImage(Image[index].Sprite, (int)Image[index].PosX, (int)Image[index].PosY, (int)Image[index].sizeX, (int)Image[index].sizeY, null);
                if (scrollNull == true) {continue;}
                if (ScrollingImage[index] == null) {continue;}
                g2.drawImage(ScrollingImage[index].Sprite, (int)ScrollingImage[index].PosX, (int)ScrollingImage[index].PosY, (int)ScrollingImage[index].sizeX, (int)ScrollingImage[index].sizeY, null);
            }
        }
        

        if (Button == null) {
            return;
        }
        for (int index = 0; index < Button.length; index++) {
            Button[index].draw(g2);
        } 

        
    }
}
