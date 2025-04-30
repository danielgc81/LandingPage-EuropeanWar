import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCreditos extends JPanel {
    public VentanaCreditos() {
        setLayout(new BorderLayout());

        JTextArea txtCreditos = new JTextArea();
       
txtCreditos.setText("Desarrollado por:\n- Yago Fernández Loza y Daniel Garrido Cortés\n -Información de contacto:yagofl02@educastur.es || Danielgc81@educastur.es  \n- Link Github: https://github.com/danielgc81/LandingPage-EuropeanWar\n");
        txtCreditos.setFont(new Font("Arial", Font.BOLD, 30));
        txtCreditos.setForeground(Color.BLACK);
        txtCreditos.setOpaque(false);
        txtCreditos.setEditable(false);
        add(new JScrollPane(txtCreditos), BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Old English Text MT", Font.BOLD, 20));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(Color.BLACK);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(VentanaCreditos.this);

                frame.getContentPane().removeAll();
                
                frame.add(new VentanaPrincipal().getContentPane());

                frame.revalidate();
                frame.repaint();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setOpaque(false);
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}