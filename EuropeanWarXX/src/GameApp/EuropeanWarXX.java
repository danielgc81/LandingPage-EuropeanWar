package GameApp;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class EuropeanWarXX extends JFrame {
   MainPanel panelPrincipal = new MainPanel();

   Dimension ScreenSize;

   EuropeanWarXX () {
      // Inicializando la variable con el tamaño de la pantalla de nuestro dispositivo
      this.ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.setSize(ScreenSize);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setTitle("European War XX");

      // Añadiendo el panel principal al marco
      this.add(panelPrincipal);
   }
}
