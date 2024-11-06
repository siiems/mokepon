package level.levels;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.Button;
import ui.Button.*;
import ui.UserInterface;
import ui.ScrollingUI;
import level.Level;
import main.GamePanel;
import main.KeyHandler;
import animation.Animation;
import animation.SpriteFrame;

public class Menu {
    double butY = 400;
    double butXsize = 200;
    double butFontSize = 50;

    KeyHandler keyH;
    GamePanel gp;    


    Button b_play;
    Button b_setting;

    Button[] buttons;
    UserInterface[] Image;
    ScrollingUI[] Scroll;

    BufferedImage Blank_Image;

    BufferedImage Background_Sky;
    BufferedImage Background_Grass;
    BufferedImage Background_Clouds;
    BufferedImage Background_Border;
    BufferedImage Logo;
    BufferedImage Frog;

    BufferedImage Button_Settings_Base;
    BufferedImage Button_Settings_Selected;

    Animation Button_Settings_Unselected_Animation;
    Animation Button_Settings_Selected_Animation;

    BufferedImage Button_Play1;
    BufferedImage Button_Play2;

    Animation button_play_selected_animation;
    Animation button_play_unselected_animation;




    double constant = 5.12;

    
    void getImages() {
        try {
            Blank_Image = ImageIO.read(getClass().getResourceAsStream("/res/general/blank.png"));
            Button_Settings_Base = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/button_settings_base.png"));
            Button_Settings_Selected = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/button_settings_selected.png"));
            Background_Sky = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/background_sky.png"));
            Background_Grass = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/background_grass.png"));
            Background_Border = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/border.png"));
            Background_Clouds = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/clouds.png"));
            Logo = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/logo.png"));
            Frog = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/frog.png"));

            Button_Play1 = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/button_play_1.png"));
            Button_Play2 = ImageIO.read(getClass().getResourceAsStream("/res/levels/menu_main/button_play_2.png"));

            SpriteFrame[] button_settings_unselected_seq = {
                new SpriteFrame(Button_Settings_Base, 10)
            };

            SpriteFrame[] button_settings_selected_seq = {
                new SpriteFrame(Button_Settings_Selected, 10)
            };


            SpriteFrame[] button_play_selected_sequence = {
                new SpriteFrame(Button_Play1, 45),
                new SpriteFrame(Blank_Image, 45)
            };

            SpriteFrame[] button_play_unselected_sequence = {
                new SpriteFrame(Button_Play2, 45),
                new SpriteFrame(Blank_Image, 45)
            };

            Button_Settings_Unselected_Animation = new Animation(button_settings_unselected_seq);
            Button_Settings_Selected_Animation = new Animation(button_settings_selected_seq);

            button_play_selected_animation = new Animation(button_play_selected_sequence);
            button_play_unselected_animation = new Animation(button_play_unselected_sequence);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Menu(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;

        getImages();

        String[] b_play_args = {"level_change", "lobby"}; 
        String[] b_setting_args = {"level_change", "menu_settings"}; 

        b_play = new Button(b_play_args, keyH, gp, 400, 100, gp.getWidth()/2-200, 300, button_play_unselected_animation, button_play_selected_animation);
        b_setting = new Button(b_setting_args, keyH, gp, 12*constant, 12*constant, gp.getWidth()-(12*constant)-25, 25, Button_Settings_Unselected_Animation, Button_Settings_Selected_Animation);

        b_play.rightButton = b_setting;


        b_setting.leftButton = b_play;

        
        UserInterface background_sky = new UserInterface();
        UserInterface background_grass = new UserInterface();
        ScrollingUI background_clouds = new ScrollingUI(0.75, 0);
        UserInterface logo = new UserInterface();
        UserInterface frog = new UserInterface();

        UserInterface background_border = new UserInterface();

        

        background_sky.sizeX = gp.getWidth();
        background_sky.sizeY = gp.getHeight();
        background_sky.PosX = 0;
        background_sky.PosY = 0;
        background_sky.Sprite = Background_Sky;

        background_grass.sizeX = gp.getWidth();
        background_grass.sizeY = gp.getHeight();
        background_grass.PosX = 0;
        background_grass.PosY = 0;
        background_grass.Sprite = Background_Grass;

        background_border.sizeX = gp.getWidth();
        background_border.sizeY = gp.getHeight();
        background_border.PosX = 0;
        background_border.PosY = 0;
        background_border.Sprite = Background_Border;

        background_clouds.sizeX = gp.getWidth()*2;
        background_clouds.sizeY = gp.getHeight();
        background_clouds.PosX = 0;
        background_clouds.PosY = 0;
        background_clouds.Sprite = Background_Clouds;




        logo.sizeX = gp.getWidth()/1.5;
        logo.sizeY = gp.getHeight()/2;
        logo.PosX = gp.getWidth()/1.5/4;
        logo.PosY = 0;
        logo.Sprite = Logo;

        ScrollingUI[] scrolling = {background_clouds, null, null, null};
        this.Scroll = scrolling;

        UserInterface[] images = {background_sky, background_grass, background_border, logo};
        this.Image = images;
        
        buttons = new Button[2];
        buttons[0] = b_play;
        buttons[1] = b_setting;
        
    }


    
    public void initialise(Level level) {
        level.Button = buttons;
        level.currentButton = buttons[0];
        level.Image = Image;
        level.ScrollingImage = Scroll;
    }
    
}
