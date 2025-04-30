import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentanaJuego extends JPanel {
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
        System.out.println("Inicializando VentanaJuego...");
        this.partida = partida;
        this.nombreJugador = nombreJugador;
        this.combate = new Combate();
        this.maquina = new Maquina();
        this.misilesAtaque = 0;
        this.misilesDefensa = 0;

        // Cargar fondo con depuración
        try {
            gameBackground = new ImageIcon(getClass().getClassLoader().getResource("Recursos/fondojuego.jpg")).getImage();
            if (gameBackground == null) {
                System.out.println("Error: No se pudo cargar la imagen de fondo en VentanaJuego");
            } else {
                System.out.println("Imagen de fondo cargada correctamente en VentanaJuego");
            }
        } catch (Exception e) {
            System.out.println("Excepción al cargar la imagen de fondo en VentanaJuego: " + e.getMessage());
            e.printStackTrace();
            gameBackground = null;
        }

        // Cargar banderas
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
                System.out.println("Imagen de bandera cargada para " + pais + ": " + rutaImagen);
            } else {
                System.out.println("Error: No se pudo cargar la imagen de bandera para " + pais + " en " + rutaImagen);
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
            System.out.println("Bandera asignada al jugador: " + nombreJugadorPais);
        } else {
            System.out.println("Error: No se encontró la bandera para el jugador: " + nombreJugadorPais);
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
                System.out.println("Bandera asignada al enemigo inicial: " + primerEnemigo);
            } else {
                System.out.println("Error: No se encontró la bandera para el enemigo inicial: " + primerEnemigo);
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
                    System.out.println("Bandera actualizada para el enemigo: " + enemigoSeleccionado);
                } else {
                    System.out.println("Error: No se encontró la bandera para el enemigo: " + enemigoSeleccionado);
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
            String input = JOptionPane.showInputDialog(this, "Misiles para defensa (máx. " + (50 - misilesAtaque) + "):");
            try {
                int misiles = Integer.parseInt(input);
                if (misiles >= 0 && misiles + misilesAtaque <= 50) {
                    misilesDefensa = misiles;
                    btnDefensa.setText("Defensa (" + misilesDefensa + ")");
                    btnAtaque.setEnabled(true);
                    if (misilesAtaque + misilesDefensa == 50) {
                        realizarAtaque();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingresa un número válido (máx. " + (50 - misilesAtaque) + ").");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingresa un número válido.");
            }
        });

        btnAtaque = new JButton("Ataque");
        btnAtaque.setBackground(Color.RED);
        btnAtaque.setForeground(Color.WHITE);
        btnAtaque.setPreferredSize(new Dimension(150, 50));
        btnAtaque.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Misiles para ataque (máx. " + (50 - misilesDefensa) + "):");
            try {
                int misiles = Integer.parseInt(input);
                if (misiles >= 0 && misiles + misilesDefensa <= 50) {
                    misilesAtaque = misiles;
                    btnAtaque.setText("Ataque (" + misilesAtaque + ")");
                    btnDefensa.setEnabled(true);
                    if (misilesAtaque + misilesDefensa == 50) {
                        realizarAtaque();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingresa un número válido (máx. " + (50 - misilesDefensa) + ").");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingresa un número válido.");
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

        System.out.println("VentanaJuego inicializada correctamente");
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

        List<String> logsMaquina = new ArrayList<>();
        maquina.jugarTurno(partida.getPaises(), combate, logsMaquina);
        for (String log : logsMaquina) {
            partida.agregarLog(log);
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

        partida.siguienteRonda();
        lblRonda.setText("Ronda: " + partida.getRonda());
        misilesAtaque = 0;
        misilesDefensa = 0;
        btnAtaque.setText("Ataque");
        btnDefensa.setText("Defensa");
        btnAtaque.setEnabled(true);
        btnDefensa.setEnabled(true);

        if (partida.hayGanador()) {
            mostrarGanador();
        }
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