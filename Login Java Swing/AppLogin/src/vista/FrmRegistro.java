package vista;

import controlador.RegistroController;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;

/**
 * @author Alexander Romero Ramírez
 */
public class FrmRegistro extends JFrame
{
    // --- Componentes ---
    private JTextField txtNombre, txtApellido, txtEmail, txtUsuario;
    private JPasswordField pswContrasena, pswConfirmar;
    private JLabel lblFotoPreview;
    private JButton btnSelFoto, btnGuardar, btnCancelar;
    private byte[] fotoBytes; // Almacena la foto seleccionada

    public FrmRegistro()
    {
        initComponents();
        configurarVentana();
    }

    private void initComponents()
    {
        // Configuración del Panel Principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 250, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(16, 30, 16, 30));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        // Título
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        JLabel titulo = new JLabel("Registro de Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(31, 78, 121));
        panel.add(titulo, g);

        // Campos de Texto
        String[] labels =
        {"Nombre:", "Apellido:", "Email:", "Usuario:", "Contraseña:", "Confirmar:"};
        JComponent[] fields =
        {
            txtNombre = new JTextField(18),
            txtApellido = new JTextField(18),
            txtEmail = new JTextField(18),
            txtUsuario = new JTextField(18),
            pswContrasena = new JPasswordField(18),
            pswConfirmar = new JPasswordField(18)
        };
        g.gridwidth = 1;
        for (int i = 0; i < labels.length; i++) {
            g.gridx = 0; g.gridy = i + 1;
            panel.add(new JLabel(labels[i]), g);
            g.gridx = 1;
            fields[i].setPreferredSize(new Dimension(190, 28));
            panel.add(fields[i], g);
        }

        // Sección foto
        g.gridx = 0; g.gridy = 7;
        panel.add(new JLabel("Foto:"), g);

        lblFotoPreview = new JLabel("Sin imagen", SwingConstants.CENTER);
        lblFotoPreview.setPreferredSize(new Dimension(80, 80));
        lblFotoPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblFotoPreview.setBackground(Color.WHITE);
        lblFotoPreview.setOpaque(true);
        g.gridx = 1; panel.add(lblFotoPreview, g);

        g.gridy = 8; g.gridx = 1;
        btnSelFoto = new JButton("Seleccionar Foto");
        panel.add(btnSelFoto, g);

        // Botones
        g.gridy = 9; g.gridx = 0;
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(55, 86, 35));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(btnGuardar, g);

        g.gridx = 1;
        btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar, g);

        add(panel);

        // --- Eventos ---
        btnSelFoto.addActionListener(e -> seleccionarFoto());

        btnGuardar.addActionListener(e -> {
            new RegistroController().procesarRegistro(
                txtNombre.getText(),
                txtApellido.getText(),
                txtEmail.getText(),
                txtUsuario.getText(),
                new String(pswContrasena.getPassword()),
                new String(pswConfirmar.getPassword()),
                fotoBytes, this
            );
        });

        btnCancelar.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            this.dispose();
        });
    }

    // Abre explorador de archivos y carga la imagen
    private void seleccionarFoto() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes (jpg, png, gif)", "jpg", "jpeg", "png", "gif"));

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            try {
                fotoBytes = Files.readAllBytes(archivo.toPath());
                ImageIcon icon = new ImageIcon(
                    new ImageIcon(fotoBytes).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)
                );
                lblFotoPreview.setIcon(icon);
                lblFotoPreview.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen.");
            }
        }
    }

    private void configurarVentana() {
        setTitle("Registro de Usuario");
        setSize(420, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}