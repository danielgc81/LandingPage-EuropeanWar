import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentanaSeleccionPais extends JPanel {
    private JTextField txtNombre;
    private JComboBox<String> cbPaises;
    private JLabel lblBandera;
    private JTextArea txtHabilidades;
    private Map<String, ImageIcon> banderas;
    private Map<String, String> descripcionesHabilidades;
    private Image backgroundImage;

    public VentanaSeleccionPais() {

        try {
            backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("Recursos/fondoseleccion.jpg"))
                    .getImage();
            if (backgroundImage == null) {
                System.out.println("Error: No se pudo cargar la imagen de fondo 'fondoseleccion.jpg'");
            } else {
                System.out.println("Imagen de fondo 'fondoseleccion.jpg' cargada correctamente");
            }
        } catch (Exception e) {
            System.out.println("Excepción al cargar la imagen de fondo: " + e.getMessage());
            e.printStackTrace();
            backgroundImage = null;
        }

        setLayout(new BorderLayout());

        // Mapa para asociar países con las rutas de sus banderas
        banderas = new HashMap<>();
        descripcionesHabilidades = new HashMap<>();
        String[] paises = { "Francia", "Espana", "Portugal", "ReinoUnido", "Polonia", "Italia", "Alemania",
                "Yugoslavia" };
        for (String pais : paises) {
            String rutaImagen = "Recursos/" + pais.toLowerCase() + ".png";
            java.net.URL imgURL = getClass().getClassLoader().getResource(rutaImagen);
            ImageIcon icono;
            if (imgURL != null) {
                icono = new ImageIcon(imgURL);
                Image imagenEscalada = icono.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                icono = new ImageIcon(imagenEscalada);
                System.out.println("Imagen cargada correctamente para " + pais + ": " + rutaImagen);
            } else {
                System.out.println("Error: No se pudo cargar la imagen para " + pais + " en " + rutaImagen);
                icono = new ImageIcon();
            }
            banderas.put(pais, icono);

            switch (pais) {
                case "Francia":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Diplomacia Eficaz\nAumenta las defensas en 20% durante 1 turno.\n\n" +
                                    "Habilidad Pasiva: Legión Extranjera\nCada 3 turnos, aumenta las defensas en 5%.");
                    break;
                case "Espana":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Defensa Ibérica\nAumenta las defensas en 30% durante 1 turno.\n\n" +
                                    "Habilidad Pasiva: Reconquista\nCada 5 turnos, cura 10 de vida.");
                    break;
                case "Portugal":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Alianza Atlántica\nAumenta el daño de los misiles en 50% durante 1 turno.\n\n"
                                    +
                                    "Habilidad Pasiva: Navegación Avanzada\nCada 3 turnos, aumenta el daño de los misiles en 10%.");
                    break;
                case "ReinoUnido":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Dominio de los Mares\nAumenta el daño de los misiles en 40% durante 1 turno.\n\n"
                                    +
                                    "Habilidad Pasiva: Radar Británico\nCada 3 turnos, aumenta las defensas en 10%.");
                    break;
                case "Polonia":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Resistencia Estoica\nAumenta las defensas en 25% durante 1 turno.\n\n"
                                    +
                                    "Habilidad Pasiva: Solidaridad Nacional\nCada 5 turnos, cura 15 de vida.");
                    break;
                case "Italia":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Influencia Fascista\nAumenta el daño de los misiles en 30% durante 1 turno.\n\n"
                                    +
                                    "Habilidad Pasiva: Industria Bélica\nCada 3 turnos, aumenta el daño de los misiles en 15%.");
                    break;
                case "Alemania":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Blitzkrieg\nAumenta el daño de los misiles en 50% durante 1 turno.\n\n"
                                    +
                                    "Habilidad Pasiva: Disciplina Militar\nCada 3 turnos, aumenta las defensas en 15%.");
                    break;
                case "Yugoslavia":
                    descripcionesHabilidades.put(pais,
                            "Habilidad Especial: Resistencia Partisana\nAumenta las defensas en 40% durante 1 turno.\n\n"
                                    +
                                    "Habilidad Pasiva: Guerrilla Organizada\nCada 5 turnos, cura 5 de vida.");
                    break;
            }
        }

        // Panel principal con un layout organizado
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Fuente Fraktur usada en el menú principal
        Font frakturFont = new Font("Old English Text MT", Font.BOLD, 20);

        // Etiqueta y campo para el nombre del jugador
        JLabel lblNombre = new JLabel("Nombre del Jugador:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(frakturFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        txtNombre.setFont(frakturFont);
        txtNombre.setPreferredSize(new Dimension(300, 40));
        txtNombre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        txtNombre.setBackground(Color.BLACK);
        txtNombre.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtNombre, gbc);

        // Etiqueta y combo box para seleccionar el país
        JLabel lblPais = new JLabel("Selecciona un País:");
        lblPais.setForeground(Color.WHITE);
        lblPais.setFont(frakturFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(lblPais, gbc);

        cbPaises = new JComboBox<>(paises);
        cbPaises.setFont(new Font("Arial", Font.PLAIN, 16));
        cbPaises.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(cbPaises, gbc);

        // Label para mostrar la bandera del país seleccionado
        lblBandera = new JLabel();
        lblBandera.setPreferredSize(new Dimension(200, 100));
        lblBandera.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        lblBandera.setIcon(banderas.get(paises[0]));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lblBandera, gbc);

        // Área de texto para mostrar las habilidades del país seleccionado
        txtHabilidades = new JTextArea();
        txtHabilidades.setEditable(false);
        txtHabilidades.setOpaque(false);
        txtHabilidades.setForeground(Color.WHITE);
        txtHabilidades.setFont(new Font("Arial", Font.PLAIN, 14));
        txtHabilidades.setText(descripcionesHabilidades.get(paises[0]));
        txtHabilidades.setPreferredSize(new Dimension(300, 80));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtHabilidades, gbc);

        // Actualizar la bandera y las habilidades cuando se seleccione un país
        cbPaises.addActionListener(e -> {
            String paisSeleccionado = (String) cbPaises.getSelectedItem();
            ImageIcon icono = banderas.get(paisSeleccionado);
            lblBandera.setIcon(icono);
            txtHabilidades.setText(descripcionesHabilidades.get(paisSeleccionado));
            lblBandera.revalidate();
            lblBandera.repaint();
            txtHabilidades.revalidate();
            txtHabilidades.repaint();
            System.out.println("Bandera y habilidades actualizadas para: " + paisSeleccionado);
        });

        // Botón de confirmar
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(frakturFont);
        btnConfirmar.setBackground(Color.WHITE);
        btnConfirmar.setForeground(Color.BLACK);
        btnConfirmar.addActionListener(e -> {
            System.out.println("Botón Confirmar presionado");
            String nombreJugador = txtNombre.getText();
            String paisSeleccionado = (String) cbPaises.getSelectedItem();
            if (nombreJugador.isEmpty()) {
                System.out.println("Nombre vacío, mostrando mensaje de error");
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre.");
                return;
            }

            System.out.println("Creando nueva partida...");
            Partida partida = new Partida();
            List<Pais> paisesList = new ArrayList<>();

            try {
                paisesList.add(new Francia("Francia", 120, 60, 0.2));
                paisesList.add(new Espana("Espana", 110, 50, 0.35));
                paisesList.add(new Portugal("Portugal", 100, 55, 0.25));
                paisesList.add(new ReinoUnido("ReinoUnido", 130, 70, 0.15));
                paisesList.add(new Polonia("Polonia", 140, 40, 0.3));
                paisesList.add(new Italia("Italia", 100, 65, 0.1));
                paisesList.add(new Alemania("Alemania", 150, 80, 0.05));
                paisesList.add(new Yugoslavia("Yugoslavia", 90, 45, 0.4));
                System.out.println("Países creados y añadidos a la lista");
            } catch (Exception ex) {
                System.out.println("Error al crear los países: " + ex.getMessage());
                ex.printStackTrace();
                return;
            }

            for (Pais pais : paisesList) {
                partida.agregarPais(pais);
                if (pais.getNombre().equals(paisSeleccionado)) {
                    partida.setJugador(pais);
                    System.out.println("Jugador seleccionado: " + pais.getNombre());
                }
            }

            System.out.println("Cambiando a VentanaJuego...");
            JFrame actualFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (actualFrame == null) {
                System.out.println("Error: No se pudo obtener el JFrame");
                return;
            }

            try {
                actualFrame.getContentPane().removeAll();
                actualFrame.add(new VentanaJuego(partida, nombreJugador));
                actualFrame.revalidate();
                actualFrame.repaint();
                System.out.println("Transición a VentanaJuego completada");
            } catch (Exception ex) {
                System.out.println("Error al cambiar a VentanaJuego: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnConfirmar, gbc);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}