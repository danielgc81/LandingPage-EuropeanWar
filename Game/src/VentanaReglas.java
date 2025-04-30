import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaReglas extends JPanel {
    public VentanaReglas() {
        setLayout(new BorderLayout());

        JTextArea txtReglas = new JTextArea(
                "Reglas del Juego:\n\n" +
                        "1. Selecciona un país y un nombre para tu jugador.\n" +
                        "2. En cada ronda, puedes atacar a un país enemigo o defenderte.\n" +
                        "3. Tienes un máximo de 50 misiles para distribuir entre ataque y defensa.\n" +
                        "4. Cada misil de ataque causa 1 punto de daño.\n" +
                        "5. Cada 8 misiles de defensa bloquean 1 punto de daño.\n" +
                        "6. Debes asignar todos tus misiles (hasta 50) antes de pasar a la siguiente ronda.\n" +
                        "7. Los países enemigos atacarán automáticamente después de tu turno a todos los países vivos.\n"
                        +
                        "8. Usa tu habilidad especial cuando esté disponible (cooldown de 3 rondas).\n" +
                        "9. Cada país tiene una habilidad pasiva que se activa cada 3 o 5 rondas.\n" +
                        "10. Gana el último país que quede en pie.\n\n" +
                        "¡Buena suerte!");
        txtReglas.setFont(new Font("Old English Text MT", Font.PLAIN, 30));
        txtReglas.setForeground(Color.BLACK);
        txtReglas.setOpaque(false);
        txtReglas.setEditable(false);
        add(new JScrollPane(txtReglas), BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Old English Text MT", Font.BOLD, 20));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(Color.BLACK);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(VentanaReglas.this);

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