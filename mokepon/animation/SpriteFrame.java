package animation;

import java.awt.image.BufferedImage;

// Record structure to store each animation sprite in a sequence
public class SpriteFrame {
    BufferedImage sprite;
    long delay;

    public SpriteFrame(BufferedImage sprite, double delay) {
        // Delay in frames -> converted to nanoseconds
        this.delay = (long)(delay/60*1000000000);
        this.sprite = sprite;
    }
}
