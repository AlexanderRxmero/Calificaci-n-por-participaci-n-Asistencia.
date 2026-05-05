package vista;

import controlador.LoginController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Alexander Romero Ramírez
 */
public class FrmLogin extends JFrame
{
    // --- Componentes ---
    private JLabel lblTitulo, lblUsuario, lblContrasena;
    private JTextField txtUsuario;
    private JPasswordField pswContrasena;
    private JButton btnLogin, btnRegistrar;
    private JPanel panelPrincipal;

    public FrmLogin()
    {
        initComponents();
        configurarVentana();
    }

    private void initComponents()
    {
        // Configuración del Panel Principal
        panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(240, 248, 255));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(31, 78, 121));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(lblTitulo, gbc);

        // Usuario
        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        panelPrincipal.add(lblUsuario, gbc);

        gbc.gridx = 1;
        txtUsuario = new JTextField(20);
        txtUsuario.setPreferredSize(new Dimension(200, 30));
        panelPrincipal.add(txtUsuario, gbc);

        // Contraseña
        gbc.gridy = 2; gbc.gridx = 0;
        lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        panelPrincipal.add(lblContrasena, gbc);

        gbc.gridx = 1;
        pswContrasena = new JPasswordField(20);
        pswContrasena.setPreferredSize(new Dimension(200, 30));
        panelPrincipal.add(pswContrasena, gbc);

        // Botón Login
        gbc.gridy = 3; gbc.gridx = 0;
        btnLogin = new JButton("Ingresar");
        btnLogin.setBackground(new Color(46, 117, 182));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 13));
        btnLogin.setPreferredSize(new Dimension(120, 35));
        panelPrincipal.add(btnLogin, gbc);

        // Botón Registrar
        gbc.gridx = 1;
        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 13));
        btnRegistrar.setPreferredSize(new Dimension(120, 35));
        panelPrincipal.add(btnRegistrar, gbc);

        add(panelPrincipal);

        // --- Eventos ---
        btnLogin.addActionListener((ActionEvent e) ->
        {
            String user = txtUsuario.getText().trim();
            String pass = new String(pswContrasena.getPassword()).trim();
            new LoginController().procesarLogin(user, pass, this);
        });

        btnRegistrar.addActionListener((ActionEvent e) ->
        {
            new FrmRegistro().setVisible(true);
            this.dispose();
        });
    }

    private void configurarVentana()
    {
        setTitle("Login — App Sistema");
        setSize(420, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}