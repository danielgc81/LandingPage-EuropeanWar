import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/european_war?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public VentanaPrincipal() {
        setTitle("European War XX");
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (VentanaJuego.currentPartida != null && VentanaJuego.currentNombreJugador != null) {
                    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String sqlPartida = "INSERT INTO partidas (nombre_jugador, ronda, pais_jugador) VALUES (?, ?, ?)";
                        PreparedStatement stmtPartida = conn.prepareStatement(sqlPartida,
                                PreparedStatement.RETURN_GENERATED_KEYS);
                        stmtPartida.setString(1, VentanaJuego.currentNombreJugador);
                        stmtPartida.setInt(2, VentanaJuego.currentPartida.getRonda());
                        stmtPartida.setString(3, VentanaJuego.currentPartida.getJugador().getNombre());
                        stmtPartida.executeUpdate();

                        ResultSet rs = stmtPartida.getGeneratedKeys();
                        int partidaId = 0;
                        if (rs.next()) {
                            partidaId = rs.getInt(1);
                        }

                        String sqlPais = "INSERT INTO paises_partida (partida_id, nombre_pais, vida, misiles, defensas, cooldown_habilidad, "
                                +
                                "bono_ataque, duracion_bono_ataque, bono_defensa, duracion_bono_defensa, turnos_desde_inicio) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement stmtPais = conn.prepareStatement(sqlPais);
                        for (Pais pais : VentanaJuego.currentPartida.getPaises()) {
                            stmtPais.setInt(1, partidaId);
                            stmtPais.setString(2, pais.getNombre());
                            stmtPais.setInt(3, pais.getVida());
                            stmtPais.setInt(4, pais.getMisiles());
                            stmtPais.setDouble(5, pais.getDefensas());
                            stmtPais.setInt(6, pais.getHabilidadEspecial().getCooldown());
                            stmtPais.setDouble(7, pais.getBonoAtaque());
                            stmtPais.setInt(8, pais.getDuracionBonoAtaque());
                            stmtPais.setDouble(9, pais.getBonoDefensa());
                            stmtPais.setInt(10, pais.getDuracionBonoDefensa());
                            stmtPais.setInt(11, pais.getTurnosDesdeInicio());
                            stmtPais.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Partida guardada exitosamente.");
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this,
                                "Error al guardar la partida: " + e.getMessage());
                    }
                }
                System.exit(0);
            }
        });

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
        btnCargarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    ArrayList<String> partidas = new ArrayList<>();
                    String sql = "SELECT id, nombre_jugador, pais_jugador, fecha_guardado FROM partidas";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        partidas.add("ID: " + rs.getInt("id") + " - Jugador: " + rs.getString("nombre_jugador") +
                                " - País: " + rs.getString("pais_jugador") + " - Guardado: "
                                + rs.getTimestamp("fecha_guardado"));
                    }

                    if (partidas.isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "No hay partidas guardadas.");
                        return;
                    }

                    String seleccion = (String) JOptionPane.showInputDialog(
                            VentanaPrincipal.this,
                            "Selecciona una partida para cargar:",
                            "Cargar Partida",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            partidas.toArray(),
                            partidas.get(0));

                    if (seleccion != null) {
                        int partidaId = Integer.parseInt(seleccion.split(" - ")[0].replace("ID: ", ""));
                        String sqlPartida = "SELECT nombre_jugador, ronda, pais_jugador FROM partidas WHERE id = ?";
                        PreparedStatement stmtPartida = conn.prepareStatement(sqlPartida);
                        stmtPartida.setInt(1, partidaId);
                        ResultSet rsPartida = stmtPartida.executeQuery();

                        if (!rsPartida.next()) {
                            JOptionPane.showMessageDialog(VentanaPrincipal.this, "Error al cargar la partida.");
                            return;
                        }

                        String nombreJugador = rsPartida.getString("nombre_jugador");
                        int ronda = rsPartida.getInt("ronda");
                        String paisJugador = rsPartida.getString("pais_jugador");

                        String sqlPaises = "SELECT nombre_pais, vida, misiles, defensas, cooldown_habilidad, " +
                                "bono_ataque, duracion_bono_ataque, bono_defensa, duracion_bono_defensa, turnos_desde_inicio "
                                +
                                "FROM paises_partida WHERE partida_id = ?";
                        PreparedStatement stmtPaises = conn.prepareStatement(sqlPaises);
                        stmtPaises.setInt(1, partidaId);
                        ResultSet rsPaises = stmtPaises.executeQuery();

                        ArrayList<Pais> paises = new ArrayList<>();
                        while (rsPaises.next()) {
                            String nombrePais = rsPaises.getString("nombre_pais");
                            Pais pais = null;
                            switch (nombrePais) {
                                case "Francia":
                                    pais = new Francia("Francia", 120, 60, 0.2);
                                    break;
                                case "Espana":
                                    pais = new Espana("Espana", 110, 50, 0.35);
                                    break;
                                case "Portugal":
                                    pais = new Portugal("Portugal", 100, 55, 0.25);
                                    break;
                                case "ReinoUnido":
                                    pais = new ReinoUnido("ReinoUnido", 130, 70, 0.15);
                                    break;
                                case "Polonia":
                                    pais = new Polonia("Polonia", 140, 40, 0.3);
                                    break;
                                case "Italia":
                                    pais = new Italia("Italia", 100, 65, 0.1);
                                    break;
                                case "Alemania":
                                    pais = new Alemania("Alemania", 150, 80, 0.05);
                                    break;
                                case "Yugoslavia":
                                    pais = new Yugoslavia("Yugoslavia", 90, 45, 0.4);
                                    break;
                            }
                            pais.setVida(rsPaises.getInt("vida"));
                            pais.setMisiles(rsPaises.getInt("misiles"));
                            pais.incrementarDefensas(rsPaises.getDouble("defensas") - pais.getDefensas());
                            pais.getHabilidadEspecial().setCooldown(rsPaises.getInt("cooldown_habilidad"));
                            pais.incrementarBonoAtaque(rsPaises.getDouble("bono_ataque"));
                            pais.setDuracionBonoAtaque(rsPaises.getInt("duracion_bono_ataque"));
                            pais.incrementarBonoDefensa(rsPaises.getDouble("bono_defensa"));
                            pais.setDuracionBonoDefensa(rsPaises.getInt("duracion_bono_defensa"));
                            pais.setTurnosDesdeInicio(rsPaises.getInt("turnos_desde_inicio"));
                            paises.add(pais);
                        }

                        Partida partida = new Partida();
                        partida.setRonda(ronda);
                        for (Pais pais : paises) {
                            partida.agregarPais(pais);
                            if (pais.getNombre().equals(paisJugador)) {
                                partida.setJugador(pais);
                            }
                        }

                        getContentPane().removeAll();
                        add(new VentanaJuego(partida, nombreJugador));
                        revalidate();
                        repaint();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this,
                            "Error al cargar partidas: " + ex.getMessage());
                }
            }
        });

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