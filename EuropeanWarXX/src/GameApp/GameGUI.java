package GameApp;

import javax.swing.JFrame;

public class GameGUI {
   public static void main(String[] args) {
      int screenWidth = 1920;
      int screenHeight = 1080;

      JFrame frame = new JFrame("");
      frame.setSize(screenWidth, screenHeight);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
