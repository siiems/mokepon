package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JPanel;


import ui.FPSCounter;
import ui.DebugUI;
import level.LevelHandler;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int MaxFPS = 165; 
    double drawInterval = 1000000000/MaxFPS;
    
    

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Font debugFont = new Font("Serif", Font.PLAIN, 15);

    public LevelHandler LevelHandler = new LevelHandler();

    FPSCounter fpscounter = new FPSCounter(this, null, Color.GREEN, debugFont);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void StartGameThead() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        boolean level_changed = LevelHandler.changeLevel("menu_main", keyH, this);

        this.drawInterval = 1000000000/MaxFPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        @SuppressWarnings("unused")
        int drawCount = 0;
        
        int loops = 0;
        long totalDiff = 0;
        double FPS = 0;

        long insideCT = 0;
        long insideLT = 0;

        double fpsRefreshSeconds = 0.5;

        

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); 
            lastTime = currentTime;

            if (delta >= 1 && MaxFPS > 0) {
                insideCT = System.nanoTime();
                totalDiff += insideCT - insideLT;
                insideLT = insideCT;
                loops++;

                update(FPS);
                repaint();
                drawCount++;
                delta = 0;

                if (loops >= 1 && totalDiff > 0) {
                    FPS = (loops/(double)totalDiff*1000000000);
                    loops = 0;
                    totalDiff = 0;
                }
            }
            
            if (timer >= fpsRefreshSeconds*1000000000) {
                drawCount = 0;
                timer = 0;
            }
            
        }
    }

    public void changeFPS(int newFPS) {
        this.drawInterval = 1000000000 / newFPS;
    }

    public void update(double currentFPS) { // Update values
        fpscounter.update(currentFPS);
        LevelHandler.update(currentFPS);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw game first
        LevelHandler.draw(g2);

        // Then draw UI
        fpscounter.draw(g2);

        // free up memory
        g2.dispose();
    }

}