package vista;

import javax.swing.SwingConstants;
import modelo.Usuario;
import javax.swing.*;
import java.awt.*;

/**
 * @author Alexander Romero Ramírez
 */
public class FrmBienvenida extends JFrame {

    public FrmBienvenida(Usuario usuario) {
        initComponents(usuario);
        configurarVentana();
    }

    private void initComponents(Usuario u) {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(235, 245, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Foto del usuario (recuperada del BLOB)
        JLabel lblFoto = new JLabel("Foto",SwingConstants.CENTER);
        lblFoto.setPreferredSize(new Dimension(120, 120));
        lblFoto.setBorder(BorderFactory.createLineBorder(new Color(46, 117, 182), 2));
        lblFoto.setBackground(Color.WHITE);
        lblFoto.setOpaque(true);

        if (u.getFoto() != null && u.getFoto().length > 0) {
            ImageIcon icon = new ImageIcon(u.getFoto());
            Image scalada = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(scalada));
        } else {
            lblFoto.setText("Sin foto");
        }

        // Panel de texto
        JPanel panelTexto = new JPanel();
        panelTexto.setOpaque(false);
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));

        JLabel lblBienvenida = new JLabel("¡Bienvenido al Sistema!");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenida.setForeground(new Color(31, 78, 121));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = new JLabel(u.getNombre() + " " + u.getApellido());
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNombre.setForeground(new Color(80, 80, 80));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUsuario = new JLabel("Usuario: " + u.getUsuario());
        lblUsuario.setFont(new Font("Arial", Font.ITALIC, 14));
        lblUsuario.setForeground(new Color(120, 120, 120));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTexto.add(Box.createVerticalGlue());
        panelTexto.add(lblBienvenida);
        panelTexto.add(Box.createVerticalStrut(10));
        panelTexto.add(lblNombre);
        panelTexto.add(Box.createVerticalStrut(5));
        panelTexto.add(lblUsuario);
        panelTexto.add(Box.createVerticalGlue());

        // Botón salir
        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 13));
        btnSalir.setBackground(new Color(197, 80, 63));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnSalir.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            this.dispose();
        });

        panel.add(lblFoto, BorderLayout.WEST);
        panel.add(panelTexto, BorderLayout.CENTER);
        panel.add(btnSalir, BorderLayout.SOUTH);

        add(panel);
    }

    private void configurarVentana() {
        setTitle("Bienvenida");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}