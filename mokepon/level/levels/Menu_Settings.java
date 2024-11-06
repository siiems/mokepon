package level.levels;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import animation.Animation;
import animation.SpriteFrame;
import ui.Button;
import ui.Button.*;
import level.Level;
import main.GamePanel;
import main.KeyHandler;

import ui.UserInterface;

public class Menu_Settings {
    double butY = 400;
    double butXsize = 200;
    double butFontSize = 50;

    KeyHandler keyH;
    GamePanel gp;    


    Button b_back;
    Button b_setting;

    Button[] buttons;
    UserInterface[] Image;

    BufferedImage Button_Back_Selected;
    BufferedImage Button_Back_Unselected;
    BufferedImage Background;
    BufferedImage blankimg;

    Animation button_back_selected_anim;
    Animation button_back_unselected_anim;
    Animation blank_anim;

    void getImages() {
        try {

            this.Background = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_settings/background.png"));
            Button_Back_Selected = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_settings/button_back_selected.png"));
            Button_Back_Unselected = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_settings/button_back_base.png"));
            blankimg = ImageIO.read(getClass().getResourceAsStream("/res/general/blank.png"));

            SpriteFrame[] button_back_selected_seq = {new SpriteFrame(Button_Back_Selected, 10)};
            SpriteFrame[] button_back_unselected_seq = {new SpriteFrame(Button_Back_Unselected, 10)};
            SpriteFrame[] blank_seq = {new SpriteFrame(blankimg, 10)};

            button_back_selected_anim = new Animation(button_back_selected_seq);
            button_back_unselected_anim = new Animation(button_back_unselected_seq);
            blank_anim = new Animation(blank_seq);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Menu_Settings(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;
        getImages();

        String[] b_back_args = {"level_change", "menu_main"}; 
        String[] b_setting_args = {"", ""}; 

        double constant = 5.12;

        b_back = new Button(b_back_args, keyH, gp, 39*constant, 15*constant, 5*constant, 5*constant, button_back_unselected_anim, button_back_selected_anim);
        b_setting = new Button(b_setting_args, keyH, gp, butXsize, 50, butXsize*0.1, 500,  blank_anim, blank_anim);

        b_setting.text = "Settings";

        b_back.rightButton = b_setting;


        b_setting.leftButton = b_back;


        UserInterface background = new UserInterface();
        background.PosX = 0;
        background.PosY = 0;
        background.sizeX = gp.getWidth();
        background.sizeY = gp.getHeight();
        background.Sprite = Background;

        
        
        

        

        UserInterface[] images = {background};
        this.Image = images;


        buttons = new Button[2];
        buttons[0] = b_back;
        buttons[1] = b_setting;
        
    }


    
    public void initialise(Level level) {
        level.Button = buttons;
        level.currentButton = buttons[1];
        level.Image = Image;
    }
    
}
