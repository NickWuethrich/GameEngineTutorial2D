import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel implements Runnable {

    final int originalTileSize = 16; // standard size for 2d game(characters ...) 16x16
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //48x48

    //How many tiles cans be displayed on a single screen hor and ver  (with 4:3 ratio)
    final int maxScreenCol = 16;
    final int maxscreenRow = 12;
    final int ScreenWidth = tileSize * maxScreenCol;  // 768 pixels
    final int ScreenHeight = tileSize * maxscreenRow; // 576 pixels

    int FPS = 60;

    private Player player;

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread; // usefull for repeating actions such as refreshing fps ; use this to make game 60fps



    int playerPosX = 100;
    int playerPosY = 100;
    int playerSpeed = 4;


    public GameWindow() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);// when true will draw on an offscreen buffer, can improve performannce
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        player = new Player();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    //passsing game Panel to Constructor

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 0.01666 seconds draw screen ever 0.0116 draw scrreen 60 times per second
        double nextDrawTime = System.nanoTime() + drawInterval;  //
        while (gameThread != null) {
            //game loop
            update();
            repaint(); // calls paintComponnent method

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0)
                    remainingTime = 0;
                Thread.sleep((long) remainingTime); // pause game loop until sleep time is over
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            //System.out.println("game loop working");

            //update
            // draw
        }

    }


    public void update() {
        if (keyHandler.up == true) {
            playerPosY -= playerSpeed;
        } else if (keyHandler.down == true) {
            playerPosY += playerSpeed;
            System.out.println("down key working");
        } else if (keyHandler.left == true) {
            playerPosX -= playerSpeed;
        } else if (keyHandler.right == true) {
            playerPosX += playerSpeed;
        }

    }


    public void paintComponent(Graphics g) { // method from jPanel // graphics class is like ur paintbrush
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  // changes g to g2 because graphics 2d has more functions
        g2.setColor(Color.black);
        g2.fillRect(playerPosX, playerPosY, tileSize, tileSize);
        g2.dispose();



    }
}


