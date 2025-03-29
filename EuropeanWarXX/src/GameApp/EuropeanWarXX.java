package GameApp;

import javax.swing.JFrame;

public class EuropeanWarXX extends JFrame {
   MainPanel panelPrincipal = new MainPanel();

   EuropeanWarXX () {
      this.setBounds(0 , 0, 1920, 1080);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setTitle("European War XX");

      // Añadiendo el panel principal al marco
      this.add(panelPrincipal);
   }
}
