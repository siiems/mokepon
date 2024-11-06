package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ui.Button;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, enterPressed;

    public boolean upDown, downDown, leftDown, rightDown, shiftDown, enterDown;

    long current;
    long last = System.nanoTime();
    long diff = 0;

    long buttonInterval = (long)(0.5 * 1000000000);

    public Button changeButton(Button Button) {
        if (upPressed == true) {
            if (Button.upButton != null) {
                upDown = true;
                return Button.upButton;
            }
        }
        else if (downPressed == true) {
            if (Button.downButton != null) {
                downDown = true;
                return Button.downButton;
            }
        }
        else if (leftPressed == true) {
            if (Button.leftButton != null) {
                leftDown = true;
                return Button.leftButton;
            }
        }
        else if (rightPressed == true) {
            if (Button.rightButton != null) {
                rightDown = true;
                return Button.rightButton;
            }
        } else if (enterPressed == true) {
            current = System.nanoTime();
            diff += current - last;
            last = current;
            if (diff < buttonInterval || enterDown == true) {return Button;}
            enterDown = true;
            Button.Execute();
            diff = 0;
        }
        return Button;
    }




    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;   
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;   
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }   

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;   
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;   
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
            enterDown = false;
        }
    }
    
}
