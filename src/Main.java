import javax.swing.*;

public class Main {

    public static void main(String [] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Nick's 2D Game");
        GameWindow gameWindow = new GameWindow();
        window.add(gameWindow);
        window.pack();
        window.setLocationRelativeTo(null);// window will be displayed at center of screen when method is null
        window.setVisible(true);

        gameWindow.startGameThread();







    }





}