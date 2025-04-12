package GameApp;

import javax.swing.SwingUtilities;

public class GameGUI implements Runnable{
   public void run () {
      EuropeanWarXX GameInterface = new EuropeanWarXX();
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new GameGUI());
   }
}
