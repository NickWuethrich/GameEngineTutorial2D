import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Player {
   private BufferedImage image;
   private Point pos;
   private int score;

   public Player(){
       loadImage();

   }

   private  void loadImage(){
       try {
           image = ImageIO.read(new File("SpriteImage/idle.png"));
       } catch (IOException e) {
           System.out.println("Error oopening image file  " + e.getMessage());
       }
   }

   public void draw (Graphics g , ImageObserver observer ){

       g.drawImage(image, pos.x, pos.y, observer);


   }
}
