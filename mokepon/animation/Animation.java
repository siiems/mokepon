package animation;

import java.awt.image.BufferedImage;
import animation.SpriteFrame;


// Animation handler
public class Animation {
    SpriteFrame[] sequence;
    int index = 0;
    BufferedImage sprite;
    long currenttime;
    long lasttime = System.nanoTime();
    long diff = 0;

    // Constructor; each instance of this class is defined with a sequence of SpriteFrame 
    public Animation(SpriteFrame[] sequence) {
        this.sequence = sequence;
    }

    /* Checks if the delay has passed between the last update call and the current one.
        Changes image if this is the case.
        Also allows each update to modify the delay by a multiplier (like when sprinting it speeds up) */

    public void update(double delay_modifier) {
        currenttime = System.nanoTime();
        diff += currenttime - lasttime;
        lasttime = currenttime;

        if (diff > this.sequence[this.index].delay*delay_modifier) {
            diff = 0;
            if (this.sequence.length > index+1) {
                index++;
            } else {
                index = 0;
            }
        }
    }

    // Getter method: Returns the current sprite
    public BufferedImage getCurrentSprite() {
        return this.sequence[this.index].sprite;
    }

}


