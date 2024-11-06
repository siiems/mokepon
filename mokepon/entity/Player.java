package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import animation.Animation;
import animation.SpriteFrame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.FileInputStream;
import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    public double trueSpeed;
    public double rootspeed;

    Animation walking_up;
    Animation walking_down;
    Animation walking_left;
    Animation walking_right;
    

    



    SpriteFrame[] walking_right_sequence = {new SpriteFrame(right1, 20), new SpriteFrame(right2, 20)};

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        rootspeed = 0;
        trueSpeed = 0;

        direction = "down";

        getPlayerImage();

    }

    public void getPlayerImage() {
        try {

            double walking_animation_delay = 8;
            
            SpriteFrame[] walking_up_sequence = {
                new SpriteFrame(ImageIO.read(getClass().getResourceAsStream("/res/player/up1.png")), walking_animation_delay), 
                new SpriteFrame(up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/up2.png")), walking_animation_delay)
            };


            SpriteFrame[] walking_down_sequence = {
                new SpriteFrame(ImageIO.read(getClass().getResourceAsStream("/res/player/down1.png")), walking_animation_delay),
                new SpriteFrame(ImageIO.read(getClass().getResourceAsStream("/res/player/down2.png")), walking_animation_delay)
            };


            SpriteFrame[] walking_left_sequence = {
                new SpriteFrame(ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png")), walking_animation_delay),
                new SpriteFrame(ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png")), walking_animation_delay)
            };  

            SpriteFrame[] walking_right_sequence = {
                new SpriteFrame(ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png")), walking_animation_delay),
                new SpriteFrame(ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png")), walking_animation_delay)
            };


            walking_up = new Animation(walking_up_sequence);
            walking_down = new Animation(walking_down_sequence);
            walking_left = new Animation(walking_left_sequence);
            walking_right = new Animation(walking_right_sequence);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(double FPS) {
        trueSpeed = (double)speed * (60 / FPS);
        if (trueSpeed > 300) {
            trueSpeed = 0;
        }

        int ydir = 0;
        int xdir = 0;

        double prevX = x;
        double prevY = y;
        
        double delay_modifier = 1;


        if(keyH.shiftPressed) {
            trueSpeed *= 2;
            delay_modifier = 0.5;
        } else {
            delay_modifier = 1;
        }

        

        if (keyH.upPressed == true) {
            direction = "up";
            walking_up.update(delay_modifier);
            y -= trueSpeed;
            ydir++;
        }
        if (keyH.downPressed == true) {
            direction = "down";
            walking_down.update(delay_modifier);
            y += trueSpeed;
            ydir++;
        }
        
        if (keyH.leftPressed == true) {
            direction = "left";
            walking_left.update(delay_modifier);
            x -= trueSpeed;
            xdir++;
        } 
        if (keyH.rightPressed == true) {
            direction = "right";
            walking_right.update(delay_modifier);
            x += trueSpeed;
            xdir++;
        }

        
        if (xdir+ydir == 2 && xdir < 2 && ydir < 2) {
            rootspeed = trueSpeed-((Math.sqrt(Math.pow(trueSpeed,2)*2)-trueSpeed)/2);
            x = prevX + ((x-prevX)/Math.abs(prevX-x))*rootspeed;
            y = prevY + ((y-prevY)/Math.abs(prevY-y))*rootspeed;
        } 


    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        
        /* 
        //Rectangle2D player = new Rectangle2D.Double(x, y, gp.tileSize, gp.tileSize);
        Ellipse2D player = new Ellipse2D.Double(x, y, gp.tileSize, gp.tileSize);
        g2.fill(player);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        */

        BufferedImage image = null;

        switch(direction) {
            case "up":
                image = walking_up.getCurrentSprite();
                break;
            case "down":
                image = walking_down.getCurrentSprite();
                break;
            case "left":
                image = walking_left.getCurrentSprite();
                break;
            case "right":
                image = walking_right.getCurrentSprite();
                break;
        }


        g2.drawImage(image, (int)x, (int)y, gp.tileSize, gp.tileSize, null);

    }

    
}

