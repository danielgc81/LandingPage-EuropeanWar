package GameApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RulesPanel extends JPanel implements ActionListener {
   // Creando el boton de volver hacia atras
   JButton goBackButton;

   JLabel rulesHeader;
   JTextArea rulesText;

   // Personalizacion del boton de volver a atrás
   Color textButtonColor = new Color(155, 27, 27);
   Font textFontButton = new Font("Arial", Font.BOLD, 17);
   Dimension buttonSize = new Dimension(150, 40);

   public RulesPanel () {
      this.setLayout(new BorderLayout());

      // Inicializamos el boton de voler hacia atras
      goBackButton = new JButton("VOLVER");
      goBackButton.setForeground(textButtonColor);
      goBackButton.setFont(textFontButton);
      goBackButton.setPreferredSize(buttonSize);
      goBackButton.setMaximumSize(buttonSize);

      rulesHeader = new JLabel();
      rulesHeader.setText("¿COMO FUNCIONA EL JUEGO?");

      rulesText = new JTextArea();
      rulesText.setText("La partida constara de 5 paises, cada País tendra 50 misiles. Con ellos podrás decidir atacar o defender. La suma de los misiles usados tanto en ataque como defensa tendrá que dar 50 misiles, los misiles usados en defensa valen doble (10 misiles ataque + 20 misiles defensa = 50 misiles)");
      rulesText.setLineWrap(true);
      rulesText.setWrapStyleWord(true);
      rulesText.setEditable(false);
      rulesText.setMaximumSize(new Dimension(1920 / 3, 600));
      rulesText.setFont(new Font("Arial", Font.PLAIN, 14));

      goBackButton.addActionListener(this);

      JPanel rulesPanel = new JPanel();
      rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
      rulesPanel.setOpaque(false);
      rulesPanel.add(rulesHeader);
      rulesPanel.add(rulesText);
      rulesPanel.add(goBackButton);

      this.add(rulesPanel, BorderLayout.CENTER);
   }

   @Override
   public void actionPerformed (ActionEvent e) {
      if (e.getSource() == goBackButton) {
         JFrame actualFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
         actualFrame.remove(this);
         actualFrame.add(new MainPanel());
         actualFrame.setVisible(true);
      }
   }
}
