package ui;

import java.math.RoundingMode;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import main.GamePanel;

public class DebugUI extends UserInterface {
    
    GamePanel gp;
    Color backgroundColor;
    Color textColor;
    double x, y;
    Font font;
    double truespeed;
    double rootspeed;
    
    public DebugUI (GamePanel gp, Color textColor, Font font) {
        this.gp = gp;
        this.textColor = textColor;
        this.font = font;
        setDefaultValues();
    }

    public void setDefaultValues() {
        PosX = 0;
        PosY = font.getSize()*2;

        sizeX = font.getSize();
        sizeY = font.getSize();

        x = 0;
        y = 0;
        truespeed = 0;
        rootspeed = 0;
    }

    public void update(double x, double y, double rootspeed, double truespeed) {
        this.x = round(x, 2);
        this.y = round(y, 2);
        this.truespeed = truespeed;
        this.rootspeed = rootspeed;
    }

    private static double round(double num, int rounding_places) {
        BigDecimal bd = BigDecimal.valueOf(num);
        bd = bd.setScale(rounding_places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public void draw(Graphics2D g2) {
        g2.setColor(textColor);
        g2.setFont(font);

        g2.drawString("X:"+x+" Y:"+y, (int)PosX, (int)PosY);
        g2.drawString("RtSpd:"+round(rootspeed,2)+" TruSpd:"+round(truespeed,2), (int)PosX, (int)PosY+font.getSize());
    }
}