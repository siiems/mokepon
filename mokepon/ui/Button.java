package ui;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import java.awt.Font;

import main.GamePanel;
import main.KeyHandler;
import animation.Animation;
import animation.SpriteFrame;

public class Button extends UserInterface {
    public Color color_standard;
    public Color color_selected;
    public Font font;
    public int fontSize;
    public Color textColor;
    public String text;
    public int text_x_diff;
    public int text_y_diff;


    public Button upButton;
    public Button downButton;
    public Button leftButton;
    public Button rightButton;

    public Animation unselectedAnim;
    public Animation selectedAnim;

    GamePanel gp;
    KeyHandler keyH;

    String[] args;


    public Button(String[] args, KeyHandler keyH, GamePanel gp, double sizeX, double sizeY, double PosX, double PosY, Animation unselectedAnim, Animation selectedAnim) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.PosY = PosY;
        this.PosX = PosX;


        this.args = args;

        this.keyH = keyH;
        this.gp = gp;

        this.selectedAnim = selectedAnim;
        this.unselectedAnim = unselectedAnim;

        Sprite = unselectedAnim.getCurrentSprite();


        font = new Font("Serif", Font.PLAIN, (int)(sizeY*0.9));
    }


    public Button Execute() {
        switch (args[0]) {
            case "level_change": {
                gp.LevelHandler.changeLevel(args[1], keyH, gp);
                return gp.LevelHandler.currentLevel.currentButton;
            }
        }
        return this;
    }

    public void update(double FPS, boolean selected) {
        selectedAnim.update(1);
        unselectedAnim.update(1);
        if (selected == true) {
            Sprite = selectedAnim.getCurrentSprite();
        } else {
            Sprite = unselectedAnim.getCurrentSprite();
        }
    }

    public void draw(Graphics2D g2) {

        if (this.Sprite == null) {
            Rectangle2D base = new Rectangle2D.Double(PosX, PosY, sizeX, sizeY);
            g2.setColor(Color.GRAY);

            g2.fill(base);
            g2.setColor(Color.BLACK);
            g2.setFont(font);
            g2.drawString(text, (int)PosX+text_x_diff, (int)(PosY+sizeY-(sizeY/5))+text_y_diff);
        } else {
            g2.drawImage(Sprite, (int)PosX, (int)PosY, (int)sizeX, (int)sizeY, null);
        }


        
        
    }


}
