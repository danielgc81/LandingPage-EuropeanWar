package GameApp;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainPanel extends JPanel implements ActionListener {
   // Creando los botones de la interfaz principal del juego
   JButton playButton;
   JButton rulesButton;
   JButton loadGameButton;
   JButton exitButton;

   // Creando el color del fondo de los botones creados
   Color buttonsColor = new Color(155, 27, 27);

   public MainPanel () {
      // Definiendo el layout del panel
      this.setLayout(new FlowLayout());

      // Inicializando los botones
      playButton = new JButton("Play");
      rulesButton = new JButton("Game Rules");
      loadGameButton = new JButton("Load Game");
      exitButton = new JButton("ExitButton");

      // Añadiendo el color a los botones
      playButton.setBackground(buttonsColor);
      rulesButton.setBackground(buttonsColor);
      loadGameButton.setBackground(buttonsColor);
      exitButton.setBackground(buttonsColor);

      playButton.addActionListener(this);
      rulesButton.addActionListener(this);
      loadGameButton.addActionListener(this);
      exitButton.addActionListener(this);

      // Añadiendo los botones
      this.add(playButton);
      this.add(rulesButton);
      this.add(loadGameButton);
      this.add(exitButton);
   }

   // Implementando el metodo abstacto de la clase ActionListener
   @Override
   public void actionPerformed (ActionEvent e) {
      if (e.getSource() == rulesButton) {
         JFrame actualFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
         actualFrame.remove(this);
         actualFrame.add(new RulesPanel());
         actualFrame.setVisible(true);
      } else if (e.getSource() == exitButton) {
         System.exit(0);
      }
   }
}
