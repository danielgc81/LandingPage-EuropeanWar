import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentanaJuego extends JPanel {
    public static Partida currentPartida;
    public static String currentNombreJugador;
    private Partida partida;
    private String nombreJugador;
    private Combate combate;
    private Maquina maquina;
    private JLabel lblRonda;
    private JLabel lblVidaJugador;
    private JLabel lblVidaEnemigo;
    private JComboBox<String> cbEnemigos;
    private JTextArea txtLog;
    private JButton btnDefensa;
    private JButton btnAtaque;
    private JButton btnHabilidad;
    private int misilesAtaque;
    private int misilesDefensa;
    private Image gameBackground;
    private Map<String, ImageIcon> banderas;

    public VentanaJuego(Partida partida, String nombreJugador) {
        currentPartida = partida;
        currentNombreJugador = nombreJugador;
        this.partida = partida;
        this.nombreJugador = nombreJugador;
        this.combate = new Combate();
        this.maquina = new Maquina();
        this.misilesAtaque = 0;
        this.misilesDefensa = 0;

        try {
            gameBackground = new ImageIcon(getClass().getClassLoader().getResource("Recursos/fondojuego.jpg")).getImage();
        } catch (Exception e) {
            gameBackground = null;
        }

        banderas = new HashMap<>();
        String[] paises = {"Francia", "Espana", "Portugal", "ReinoUnido", "Polonia", "Italia", "Alemania", "Yugoslavia"};
        for (String pais : paises) {
            String rutaImagen = "Recursos/" + pais.toLowerCase() + ".png";
            java.net.URL imgURL = getClass().getClassLoader().getResource(rutaImagen);
            ImageIcon icono;
            if (imgURL != null) {
                icono = new ImageIcon(imgURL);
                Image imagenEscalada = icono.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                icono = new ImageIcon(imagenEscalada);
            } else {
                icono = new ImageIcon();
            }
            banderas.put(pais, icono);
        }

        setLayout(new BorderLayout());

        Font frakturFontLarge = new Font("Old English Text MT", Font.BOLD, 30);
        Font frakturFontLog = new Font("Old English Text MT", Font.BOLD, 24);
        Font frakturFontVida = new Font("Old English Text MT", Font.BOLD, 40);

        lblRonda = new JLabel("Ronda: " + partida.getRonda(), SwingConstants.CENTER);
        lblRonda.setForeground(Color.BLACK);
        lblRonda.setFont(frakturFontLarge);
        add(lblRonda, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2));
        panelCentral.setOpaque(false);

        JPanel panelJugador = new JPanel(new BorderLayout());
        panelJugador.setOpaque(false);
        JLabel lblNombreJugador = new JLabel(partida.getJugador().getNombre(), SwingConstants.CENTER);
        lblNombreJugador.setForeground(Color.BLACK);
        lblNombreJugador.setFont(frakturFontLarge);
        panelJugador.add(lblNombreJugador, BorderLayout.NORTH);

        JLabel lblBanderaJugador = new JLabel();
        lblBanderaJugador.setHorizontalAlignment(SwingConstants.CENTER);
        String nombreJugadorPais = partida.getJugador().getNombre();
        ImageIcon banderaJugador = banderas.get(nombreJugadorPais);
        if (banderaJugador != null) {
            lblBanderaJugador.setIcon(banderaJugador);
        }
        panelJugador.add(lblBanderaJugador, BorderLayout.CENTER);

        JPanel imagenPanelJugador = new JPanel(new BorderLayout());
        imagenPanelJugador.setOpaque(false);
        JLabel lblImagenJugador = new JLabel(partida.getJugador().getImagen());
        lblImagenJugador.setHorizontalAlignment(SwingConstants.CENTER);
        imagenPanelJugador.add(lblImagenJugador, BorderLayout.CENTER);
        panelJugador.add(imagenPanelJugador, BorderLayout.SOUTH);

        lblVidaJugador = new JLabel("Vida: " + partida.getJugador().getVida());
        lblVidaJugador.setForeground(Color.WHITE);
        lblVidaJugador.setHorizontalAlignment(SwingConstants.CENTER);
        lblVidaJugador.setFont(frakturFontVida);
        panelJugador.add(lblVidaJugador, BorderLayout.SOUTH);
        panelCentral.add(panelJugador);

        JPanel panelEnemigos = new JPanel(new BorderLayout());
        panelEnemigos.setOpaque(false);
        cbEnemigos = new JComboBox<>();
        actualizarEnemigos();
        panelEnemigos.add(cbEnemigos, BorderLayout.NORTH);
        lblVidaEnemigo = new JLabel("Vida: ");
        lblVidaEnemigo.setForeground(Color.WHITE);
        lblVidaEnemigo.setHorizontalAlignment(SwingConstants.CENTER);
        lblVidaEnemigo.setFont(frakturFontVida);
        panelEnemigos.add(lblVidaEnemigo, BorderLayout.SOUTH);

        JLabel lblBanderaEnemigo = new JLabel();
        lblBanderaEnemigo.setHorizontalAlignment(SwingConstants.CENTER);
        if (cbEnemigos.getItemCount() > 0) {
            String primerEnemigo = cbEnemigos.getItemAt(0);
            ImageIcon banderaEnemigo = banderas.get(primerEnemigo);
            if (banderaEnemigo != null) {
                lblBanderaEnemigo.setIcon(banderaEnemigo);
            }
        }
        panelEnemigos.add(lblBanderaEnemigo, BorderLayout.CENTER);

        cbEnemigos.addActionListener(e -> {
            actualizarVidaEnemigo();
            String enemigoSeleccionado = (String) cbEnemigos.getSelectedItem();
            if (enemigoSeleccionado != null) {
                ImageIcon banderaEnemigo = banderas.get(enemigoSeleccionado);
                if (banderaEnemigo != null) {
                    lblBanderaEnemigo.setIcon(banderaEnemigo);
                }
            }
        });

        panelCentral.add(panelEnemigos);
        add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setOpaque(false);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBotones.setOpaque(false);
        btnDefensa = new JButton("Defensa");
        btnDefensa.setBackground(Color.BLUE);
        btnDefensa.setForeground(Color.WHITE);
        btnDefensa.setPreferredSize(new Dimension(150, 50));
        btnDefensa.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Misiles para defensa (máx. 25, 1 misil = 2 puntos):");
            try {
                int misiles = Integer.parseInt(input);
                if (misiles >= 0 && misiles <= 25) {
                    misilesDefensa = misiles;
                    misilesAtaque = 50 - (2 * misilesDefensa);
                    btnDefensa.setText("Defensa (" + misilesDefensa + ")");
                    btnAtaque.setText("Ataque (" + misilesAtaque + ")");
                    realizarAtaque();
                } else {
                    JOptionPane.showMessageDialog(this, "Pon un número válido (0 a 25).");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Pon un número válido.");
            }
        });

        btnAtaque = new JButton("Ataque");
        btnAtaque.setBackground(Color.RED);
        btnAtaque.setForeground(Color.WHITE);
        btnAtaque.setPreferredSize(new Dimension(150, 50));
        btnAtaque.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Misiles para ataque (máx. 50, 1 misil = 1 punto):");
            try {
                int misiles = Integer.parseInt(input);
                if (misiles >= 0 && misiles <= 50) {
                    misilesAtaque = misiles;
                    misilesDefensa = (50 - misilesAtaque) / 2;
                    btnAtaque.setText("Ataque (" + misilesAtaque + ")");
                    btnDefensa.setText("Defensa (" + misilesDefensa + ")");
                    realizarAtaque();
                } else {
                    JOptionPane.showMessageDialog(this, "Pon un número válido (0 a 50).");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Pon un número válido.");
            }
        });

        btnHabilidad = new JButton("Habilidad Especial");
        btnHabilidad.setPreferredSize(new Dimension(150, 50));
        btnHabilidad.addActionListener(e -> {
            if (partida.getJugador().getHabilidadEspecial().getCooldown() == 0) {
                Pais enemigo = getEnemigoSeleccionado();
                if (enemigo != null) {
                    partida.getJugador().getHabilidadEspecial().activar(partida.getJugador(), enemigo);
                    JOptionPane.showMessageDialog(this, "¡Habilidad especial de " + partida.getJugador().getNombre() + " activada contra " + enemigo.getNombre() + "!");
                    partida.agregarLog(partida.getJugador().getNombre() + " usó su habilidad especial contra " + enemigo.getNombre());
                    actualizarVidaEnemigo();
                } else {
                    JOptionPane.showMessageDialog(this, "Selecciona un enemigo.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Habilidad en cooldown. Faltan " + partida.getJugador().getHabilidadEspecial().getCooldown() + " rondas.");
            }
        });

        panelBotones.add(btnDefensa);
        panelBotones.add(btnAtaque);
        panelBotones.add(btnHabilidad);
        panelInferior.add(panelBotones, BorderLayout.NORTH);

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setOpaque(false);
        txtLog.setForeground(Color.BLACK);
        txtLog.setFont(frakturFontLog);
        panelInferior.add(new JScrollPane(txtLog), BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void actualizarEnemigos() {
        cbEnemigos.removeAllItems();
        for (Pais pais : partida.getPaises()) {
            if (pais != partida.getJugador() && pais.getVida() > 0) {
                cbEnemigos.addItem(pais.getNombre());
            }
        }
    }

    private void actualizarVidaEnemigo() {
        Pais enemigo = getEnemigoSeleccionado();
        if (enemigo != null) {
            lblVidaEnemigo.setText("Vida: " + enemigo.getVida());
        }
    }

    private Pais getEnemigoSeleccionado() {
        String nombreEnemigo = (String) cbEnemigos.getSelectedItem();
        for (Pais pais : partida.getPaises()) {
            if (pais.getNombre().equals(nombreEnemigo)) {
                return pais;
            }
        }
        return null;
    }

    private void realizarAtaque() {
        Pais enemigo = getEnemigoSeleccionado();
        if (enemigo == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un enemigo.");
            return;
        }

        partida.getJugador().setMisilesAtaque(misilesAtaque);
        partida.getJugador().setMisilesDefensa(misilesDefensa);
        int vidaEnemigoAntes = enemigo.getVida();
        combate.atacar(partida.getJugador(), enemigo, misilesAtaque, misilesDefensa);
        int danoCausado = vidaEnemigoAntes - enemigo.getVida();
        partida.agregarLog(partida.getJugador().getNombre() + " atacó a " + enemigo.getNombre() + " con " + misilesAtaque + " misiles, causando " + danoCausado + " de daño.");
        partida.marcarMisilesGastados(partida.getJugador());

        if (partida.getJugador().getVida() <= 0) {
            mostrarPerdiste();
            return;
        }

        List<String> logsMaquina = new ArrayList<>();
        maquina.jugarTurno(partida.getPaises(), combate, logsMaquina, partida);
        for (String log : logsMaquina) {
            partida.agregarLog(log);
        }

        if (partida.getJugador().getVida() <= 0) {
            mostrarPerdiste();
            return;
        }

        for (Pais pais : partida.getPaises()) {
            if (pais.getVida() > 0) {
                pais.aplicarHabilidadPasiva();
            }
        }

        for (Pais pais : partida.getPaises()) {
            if (pais.getVida() > 0) {
                pais.reducirDuracionBonos();
            }
        }

        lblVidaJugador.setText("Vida: " + partida.getJugador().getVida());
        actualizarEnemigos();
        actualizarVidaEnemigo();
        txtLog.setText(partida.getLogRondaAnterior());

        if (partida.hayGanador()) {
            mostrarGanador();
            return;
        }

        if (partida.todosMisilesGastados()) {
            partida.siguienteRonda();
            lblRonda.setText("Ronda: " + partida.getRonda());
        }

        misilesAtaque = 0;
        misilesDefensa = 0;
        btnAtaque.setText("Ataque");
        btnDefensa.setText("Defensa");
        btnAtaque.setEnabled(true);
        btnDefensa.setEnabled(true);
    }

    private void mostrarPerdiste() {
        JFrame ventanaPerdiste = new JFrame("¡Perdiste!");
        ventanaPerdiste.setSize(400, 300);
        ventanaPerdiste.setLocationRelativeTo(null);

        JFrame actualFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        actualFrame.setVisible(false);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel lblTexto = new JLabel("¡Has perdido, " + nombreJugador + "!", SwingConstants.CENTER);
        panel.add(lblTexto, BorderLayout.NORTH);

        JLabel lblBandera = new JLabel();
        lblBandera.setHorizontalAlignment(SwingConstants.CENTER);
        lblBandera.setIcon(banderas.get(partida.getJugador().getNombre()));
        panel.add(lblBandera, BorderLayout.CENTER);

        ventanaPerdiste.add(panel);
        ventanaPerdiste.setVisible(true);

        Timer timer = new Timer(20000, e -> {
            int opcion = JOptionPane.showConfirmDialog(ventanaPerdiste, "¿Quieres jugar una nueva partida?", "Fin del Juego", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                ventanaPerdiste.dispose();
                actualFrame.getContentPane().removeAll();
                actualFrame.add(new VentanaPrincipal());
                actualFrame.revalidate();
                actualFrame.repaint();
                actualFrame.setVisible(true);
            } else {
                System.exit(0);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void mostrarGanador() {
        Pais ganador = partida.getGanador();
        JFrame ventanaGanador = new JFrame("¡Ganador!");
        ventanaGanador.setSize(400, 300);
        ventanaGanador.setLocationRelativeTo(null);

        JFrame actualFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        actualFrame.setVisible(false);

        JPanel panel = new JPanel(new BorderLayout());
        String textoGanador = ganador == partida.getJugador() ? ganador.getNombre() + " (" + nombreJugador + ")" : ganador.getNombre();
        JLabel lblTexto = new JLabel("Ganador: " + textoGanador, SwingConstants.CENTER);
        panel.add(lblTexto, BorderLayout.NORTH);

        JLabel lblBanderaGanador = new JLabel();
        lblBanderaGanador.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanderaGanador.setIcon(banderas.get(ganador.getNombre()));
        panel.add(lblBanderaGanador, BorderLayout.CENTER);

        JLabel lblImagen = new JLabel(ganador.getImagen());
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblImagen, BorderLayout.SOUTH);

        ventanaGanador.add(panel);
        ventanaGanador.setVisible(true);

        Timer timer = new Timer(20000, e -> {
            int opcion = JOptionPane.showConfirmDialog(ventanaGanador, "¿Quieres jugar una nueva partida?", "Fin del Juego", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                ventanaGanador.dispose();
                actualFrame.getContentPane().removeAll();
                actualFrame.add(new VentanaPrincipal());
                actualFrame.revalidate();
                actualFrame.repaint();
                actualFrame.setVisible(true);
            } else {
                System.exit(0);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameBackground != null) {
            g.drawImage(gameBackground, 0, 0, 1920, 1080, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}