package GameApp;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;

public class MainPanel extends JPanel {
   // Creando los botones de la interfaz principal del juego
   JButton playButton;
   JButton rulesButton;
   JButton loadGameButton;
   JButton exitButton;

   public MainPanel () {
      // Definiendo el layout del panel
      this.setLayout(new FlowLayout());

      // Inicializando los botones
      playButton = new JButton("Play");
      rulesButton = new JButton("Game Rules");
      loadGameButton = new JButton("Load Game");
      exitButton = new JButton("ExitButton");

      // Añadiendo los botones
      this.add(playButton);
      this.add(rulesButton);
      this.add(loadGameButton);
      this.add(exitButton);
   }
}
