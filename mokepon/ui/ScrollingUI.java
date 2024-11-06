package ui;

import main.GamePanel;

public class ScrollingUI extends UserInterface {
    public double ScrollDistanceX;
    public double ScrollDistanceY;

    public ScrollingUI(double ScrollDistanceX, double ScrollDistanceY) {
        this.ScrollDistanceX = ScrollDistanceX;
        this.ScrollDistanceY = ScrollDistanceY;
    }

    public void update(double FPS, GamePanel gp) {
        this.PosX += ScrollDistanceX*(60/FPS);
        this.PosY += ScrollDistanceY*(60/FPS);
        if (this.PosX >= gp.getWidth()) {
            this.PosX = -(sizeX);
        }

        if (this.PosY >= gp.getHeight()) {
            this.PosY = -(sizeY);
        }
    }
}
