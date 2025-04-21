package GameApp;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel implements ActionListener {
   // Creando los botones de la interfaz principal del juego
   JButton playButton;
   JButton rulesButton;
   JButton loadGameButton;
   JButton exitButton;

   // Creando el color del fondo de los botones creados
   Color textButtonColor = new Color(155, 27, 27);

   // Imagen del background
   Image gameBackground;

   public MainPanel () {
      // Definiendo el layout del panel
      this.setLayout(new BorderLayout());

      // Inicializando los botones
      playButton = new JButton("PLAY");
      rulesButton = new JButton("GAME RULES");
      loadGameButton = new JButton("LOAD GAME");
      exitButton = new JButton("QUIT");

      // Creando el size fijo de los botones
      Dimension buttonSize = new Dimension(150, 40);
      // Creando un tamaño de fuente
      Font buttonFont = new Font("Arial",Font.BOLD, 17);

      ArrayList<JButton> mainPanelButtons = new ArrayList<>(List.of(playButton, rulesButton, loadGameButton, exitButton));
      for (JButton button : mainPanelButtons) {
         // Color del texto de los botones
         button.setForeground(textButtonColor);
         // Tamaño de los botones
         button.setPreferredSize(buttonSize);
         button.setMaximumSize(buttonSize);
         // Aplicando fuente
         button.setFont(buttonFont);
         // Texto de los botones en el centro
         button.setHorizontalAlignment(SwingConstants.CENTER);
         button.setAlignmentX(Component.CENTER_ALIGNMENT);
         button.addActionListener(this);
      }

      // Cargando el background del juego
      gameBackground = new ImageIcon(getClass().getResource("../background-juego.png")).getImage();

      // Creando el logo del juego
      ImageIcon gameLogo = new ImageIcon(getClass().getResource("../EUROPEAN WAR XX transparent.png"));

      // Escalando a un tamño mayor el logo del juego
      Image scaledLogo = gameLogo.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
      ImageIcon resizedLogo = new ImageIcon(scaledLogo);

      // Añadiendo la imagen del título del juego en la parte superior
      JPanel logoPanel = new JPanel();
      logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
      logoPanel.setOpaque(false);
      logoPanel.add(new JLabel(resizedLogo));

      // Panel para los botones
      JPanel buttonsPanel = new JPanel();
      buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
      buttonsPanel.setOpaque(false);
      buttonsPanel.add(logoPanel);
      buttonsPanel.add(Box.createVerticalStrut(60));
      for (JButton button : mainPanelButtons) {
         buttonsPanel.add(button);
         // Creando un margen entre botones
         buttonsPanel.add(Box.createVerticalStrut(10));
      }

      // Panel para centrar los botones
      JPanel centerPanel = new JPanel(new GridBagLayout());
      centerPanel.setOpaque(false);
      centerPanel.add(buttonsPanel);

      // Añadiendo los botones al centro del frame
      this.add(centerPanel, BorderLayout.CENTER);
   }

   // Implementando el metodo abstacto de la clase ActionListener
   @Override
   public void actionPerformed (ActionEvent e) {
      if (e.getSource() == rulesButton) {
         JFrame actualFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
         actualFrame.remove(this);
         actualFrame.add(new RulesPanel());
         actualFrame.setVisible(true);
      } else if (e.getSource() == playButton) {

      } else if (e.getSource() == loadGameButton) {

      } else if (e.getSource() == exitButton) {
         System.exit(0);
      }
   }

   public void paintComponent (Graphics g) {
      super.paintComponent(g);
      draw(g);
   }

   public void draw (Graphics g) {
      g.drawImage(gameBackground, 0, 0, 1920, 1080, null);
   }
}
