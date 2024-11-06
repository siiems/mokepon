package ui;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Font;

import main.GamePanel;

public class FPSCounter extends UserInterface {
    
    GamePanel gp;
    Color backgroundColor;
    Color textColor;
    double FPS;
    Font font;
    
    public FPSCounter (GamePanel gp, Color backgroundColor, Color textColor, Font font) {
        
        this.gp = gp;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.font = font;

        setDefaultValues();
    }

    public void setDefaultValues() {
        PosX = 0;
        PosY = 0;

        sizeX = font.getSize();
        sizeY = font.getSize();

        FPS = 0;
    }

    public void update(double FPS) {
        this.FPS = Math.round(FPS);
    }

    public void draw(Graphics2D g2) {

        
        if (backgroundColor != null) {
            g2.setColor(backgroundColor);
            Rectangle2D background = new Rectangle2D.Double(PosX, PosY, sizeX*(String.valueOf(this.FPS).length())/2, sizeY);
            g2.fill(background);
        }

        g2.setColor(textColor);
        g2.setFont(font);
        g2.drawString(""+this.FPS, (int)PosX, (int)(PosY+sizeY-(sizeY/5)));
        
    }
}