import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("European War XX");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fondo = new ImageIcon(getClass().getClassLoader().getResource("Recursos/image.jpg"));
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        Font frakturFont = new Font("Old English Text MT", Font.BOLD, 40);

        JButton btnJugar = new JButton("Jugar");
        btnJugar.setFont(frakturFont);
        btnJugar.setBackground(Color.WHITE);
        btnJugar.setForeground(Color.BLACK);
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                add(new VentanaSeleccionPais());
                revalidate();
                repaint();
            }
        });

        JButton btnCargarPartida = new JButton("Cargar Partida");
        btnCargarPartida.setFont(frakturFont);
        btnCargarPartida.setBackground(Color.WHITE);
        btnCargarPartida.setForeground(Color.BLACK);
        
        JButton btnReglas = new JButton("Reglas");
        btnReglas.setFont(frakturFont);
        btnReglas.setBackground(Color.WHITE);
        btnReglas.setForeground(Color.BLACK);
        btnReglas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                add(new VentanaReglas());
                revalidate();
                repaint();
            }
        });

        JButton btnCreditos = new JButton("Créditos");
        btnCreditos.setFont(frakturFont);
        btnCreditos.setBackground(Color.WHITE);
        btnCreditos.setForeground(Color.BLACK);
        btnCreditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                add(new VentanaCreditos());
                revalidate();
                repaint();
            }
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(frakturFont);
        btnSalir.setBackground(Color.WHITE);
        btnSalir.setForeground(Color.BLACK);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnJugar, gbc);

        gbc.gridy = 1;
        panel.add(btnCargarPartida, gbc);

        gbc.gridy = 2;
        panel.add(btnReglas, gbc);

        gbc.gridy = 3;
        panel.add(btnCreditos, gbc);

        gbc.gridy = 4;
        panel.add(btnSalir, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}