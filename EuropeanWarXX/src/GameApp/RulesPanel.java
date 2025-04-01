package GameApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RulesPanel extends JPanel implements ActionListener {
   // Creando el boton de volver hacia atras
   JButton goBackButton;

   JLabel rules;

   Color buttonsColor = new Color(155, 27, 27);

   public RulesPanel () {
      this.setLayout(new BorderLayout());

      // Inicializamos el boton de voler hacia atras
      goBackButton = new JButton("Volver");
      goBackButton.setBackground(buttonsColor);

      rules = new JLabel();

      goBackButton.addActionListener(this);

      this.add(rules, BorderLayout.CENTER);
      this.add(goBackButton);
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
